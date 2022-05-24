package com.example.yes.model.userEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class userDataClass (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name = "user_name")
    var first_name:String,
    var last_name:String,
    var username:String,
    var user_password:String
)