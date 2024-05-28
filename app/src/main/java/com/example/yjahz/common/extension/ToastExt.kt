package com.example.yjahz.common.extension

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(message: String)  = activity?.showToast(message)
fun Activity.showToast(message: String) = makeToast(message)
fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

