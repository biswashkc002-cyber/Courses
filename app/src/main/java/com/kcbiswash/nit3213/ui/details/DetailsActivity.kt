package com.kcbiswash.nit3213.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.gson.JsonParser
import com.kcbiswash.nit3213.databinding.ActivityDetailsBinding

class DetailsActivity : ComponentActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val json = intent.getStringExtra("entity_json").orEmpty()
        val obj = JsonParser.parseString(json).asJsonObject

        val titleEntry = obj.entrySet().firstOrNull { it.key != "description" }
        val title = titleEntry?.let { entry ->
            val value = if (entry.value.isJsonPrimitive && entry.value.asJsonPrimitive.isString) {
                entry.value.asString
            } else {
                entry.value.toString()
            }
            "${entry.key}: $value"
        } ?: "Details"

        val all = obj.entrySet().joinToString("\n") { e ->
            val value = if (e.value.isJsonPrimitive && e.value.asJsonPrimitive.isString) {
                e.value.asString
            } else {
                e.value.toString()
            }
            "${e.key}: $value"
        }

        binding.tvTitle.text = title
        binding.tvAll.text = all
    }
}
