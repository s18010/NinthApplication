package com.example.ninthapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


open class Record : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var date: Date = Date()
    var timeBegin: Int = 0
    var timeFinish: Int = 0
}