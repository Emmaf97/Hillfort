package org.wit.hillfortapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.toast
import org.wit.hillfortapp.MainApp
import org.wit.hillfortapp.R


class SplashScreenActivity : AppCompatActivity() {
    //
//    lateinit var app: MainApp
//
    //Button btnstart;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//val background = object : Thread(){
//    override fun run(){
//        try{
//            Thread.sleep(5000)
//
//            val intent = Intent(baseContext, MainApp::class.java)
//            startActivity(inten)
//        }
//    }
//}
        btnstart.setOnClickListener {
            val intent = Intent(this, HillfortListActivity::class.java)
            startActivity(intent)
        }
    }
}


//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId) {
//            R.id.item_greeting -> {
//
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//}

