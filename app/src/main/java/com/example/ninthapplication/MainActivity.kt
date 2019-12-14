package com.example.ninthapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_record_list.*
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(),
    DatePickerFragment.OnDateSelectedListener,
    TimeBeginPickerFragment.OnTimeBeginSelectedListener,
    TimeFinishPickerFragment.OnTimeFinishSelectedListener {

    private lateinit var realm: Realm

    override fun onDateSelected(year: Int, month: Int, date: Int) {
        val c = Calendar.getInstance()
        c.set(year, month, date)
        date_begin.text = DateFormat.format("yyyy/MM/dd", c)
        val pref = getSharedPreferences("date_begin", Context.MODE_PRIVATE)
        pref.edit {
            putInt("year", year)
            putInt("month", month)
            putInt("date", date)
        }
    }

    override fun onTimeBeginSelected(hourOfDay: Int, minute: Int) {
        time_begin.text = "%1$02d:%2$02d".format(hourOfDay, minute)
        val pref = getSharedPreferences("time_begin", Context.MODE_PRIVATE)
        pref.edit {
            putInt("hourOfDay", hourOfDay)
            putInt("minute", minute)
        }
    }

    override fun onTimeFinishSelected(hourOfDay: Int, minute: Int) {
        time_finish.text = "%1$02d:%2$02d".format(hourOfDay, minute)
        val pref = getSharedPreferences("time_finish", Context.MODE_PRIVATE)
        pref.edit {
            putInt("hourOfDay", hourOfDay)
            putInt("minute", minute)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realm = Realm.getDefaultInstance()
        recordList.layoutManager = LinearLayoutManager(this)
        val records = realm.where<Record>().findAll()
        val adapter = MainAdapter(records)
        recordList.adapter = adapter


        date_begin.setOnClickListener {
            val dialog = DatePickerFragment()
            dialog.show(supportFragmentManager, "date_dialog")
        }

        time_begin.setOnClickListener {
            val dialog = TimeBeginPickerFragment()
            dialog.show(supportFragmentManager, "time_begin_dialog")
        }

        time_finish.setOnClickListener {
            val dialog = TimeFinishPickerFragment()
            dialog.show(supportFragmentManager, "time_finish_dialog")
        }

        saveButton.setOnClickListener {
            realm.executeTransaction { db: Realm ->
                val maxId = db.where<Record>().max("id")
                val nextId = (maxId?.toLong() ?: 0L) + 1
                val record = db.createObject<Record>(nextId)
                val date = date_begin.text.toString().toDate("yyyy/MM/dd")
                if (date != null) record.date = date
            }
        }

        listButton.setOnClickListener {
            val intent = Intent(this, RecordListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun String.toDate(pattern: String = "yyyy/MM/dd HH:mm"): Date? {
        return try {
            SimpleDateFormat(pattern).parse(this)
        } catch (e: IllegalArgumentException) {
            return null
        } catch (e: ParseException) {
            return null
        }
    }
}