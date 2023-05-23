package com.rgsc.iot.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var testtext: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    val TAG: String = "XC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testtext = findViewById(R.id.testtxt)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn1.setOnClickListener(View.OnClickListener {
            Log.e(TAG, "onCreate: ")
            runBlocking {//外协程
//协程环境  因为是从click吊起来的所以是main线程
                launch(Dispatchers.IO) {//内协程
                    Log.e(TAG, "lanuch:${Thread.currentThread().name}")
                    repeat(10) {
                        Thread.sleep(100)
                        Log.e(TAG, "计数: ${it}")
                    }
                }
            }
        })
        btn2.setOnClickListener(View.OnClickListener {
            runBlocking {
                launch {
                    var job = async(Dispatchers.IO) {

                    }
                    job.await();
                }
            }

        })
        btn3.setOnClickListener(View.OnClickListener {

        })
        var xiecheng = xiecheng<Int>()

    }
    //        简化写法
}

fun displayMethed() = runBlocking {
    launch {
        async(Dispatchers.IO) {
//异步线程
        }.await()//外面是主线程
    }
}


inline fun <reified T> xiecheng(): Unit {
    runBlocking {
        launch {
            var job = async {

            }
            job.await()
        }
    }
}
}