package com.example.prolificinteractive.digitallibrary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = LibraryViewAdapter(DataSource.getItems())
        recyclerView.adapter = adapter
        print("Hello World!")
        HTTPClient().run("http://prolific-interview.herokuapp.com/5b9676e767a8480009af4daa/books")
    }
}

class HTTPClient {

    private val client = OkHttpClient()

    @Throws(IOException::class)
    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                print(e)
            }
            override fun onResponse(call: Call, response: Response) {
                println(response.body()?.string())
            }
        })
    }
}