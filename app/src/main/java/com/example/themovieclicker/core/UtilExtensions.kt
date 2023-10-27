package com.example.themovieclicker.core

import android.content.Context
import android.util.Log


enum class LogTags{
    NETWORK_LOG,
    LOCAL_LOG,
    UI_LOG,
    DOMAIN_LOG
}

fun Context.log(tag:String,log:String){
    Log.i(tag,log)
}