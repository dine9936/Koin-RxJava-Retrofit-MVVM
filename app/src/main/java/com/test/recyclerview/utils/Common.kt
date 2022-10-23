package com.test.recyclerview.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Common {

    companion object{

        const val TAG = "TheMeal App"

        fun mySnackBar(rootLayout: View, message: String = "Something went wrong...", time: Int = Snackbar.LENGTH_LONG){
            Snackbar.make(rootLayout, message, time).show()
        }

        fun myToast(context: Context, message: String = "Please wait...", time: Int = Toast.LENGTH_LONG){
            Toast.makeText(context, message, time).show()
        }
    }

}