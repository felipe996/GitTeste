package br.com.git.extensions

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import br.com.git.domain.entity.items
import java.text.SimpleDateFormat




// Configura a Toolbar
fun AppCompatActivity.setupToolbar(@IdRes id: Int, title: String? = null, upNavigation: Boolean = false): ActionBar {
    val toolbar = findViewById<Toolbar>(id)
    setSupportActionBar(toolbar)
    if (title != null) {
        supportActionBar?.title = title;
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
    Log.d("carros", "Up nav config em $upNavigation $supportActionBar")
    return supportActionBar!!
}

fun sizePage(list:MutableList<items>):Int{
    return (list.size/5) + 1
}
fun convertDate(value:String):String{
    return try {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val patternBr = "dd-MM-yyyy"
        val date = SimpleDateFormat(pattern).parse(value)
        System.out.println(date)
        SimpleDateFormat(patternBr).format(date)
    }catch (e:Exception){
        ""
    }

}