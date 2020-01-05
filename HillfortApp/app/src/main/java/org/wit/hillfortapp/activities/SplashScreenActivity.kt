package org.wit.hillfortapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.wit.hillfortapp.R
import org.wit.hillfortapp.views.hillfortlist.HillfortListView


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
            val intent = Intent(this, HillfortListView::class.java)
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

