package com.example.hw1_m6

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hw1_m6.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var random = Random()

    private var list = arrayListOf("https://s9.travelask.ru/uploads/post/000/024/015/main_image/full-e1e0fe282e9394e1187ee39e1e315827.jpg",
    "data:image/jpeg;base64,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",
    "https://cdn.nur.kz/images/1120x630/c7086ccd9e1b8fc2.jpeg",
    "data:image/jpeg;base64,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",
    "https://begeton.com/files/articles/121/3/13/CHc6uBurOxiZn8iLt8jErJJDslpymPUP.jpeg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addBtn.setOnClickListener {
            list.add(binding.edt.text.toString())
            Log.e("ololo", list.get(list.size -1))
        }
        binding.randomBtn.setOnClickListener {
            binding.image.loadPhoto(list.get(random.nextInt(list.size)))
            Log.e("ololo", (list.get(random.nextInt(list.size))))
        }
    }

    private fun ImageView.loadPhoto(image: String) {
        Glide.with(this@MainActivity).load(image).into(this)
    }
}