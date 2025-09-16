package com.kcbiswash.nit3213.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kcbiswash.nit3213.data.Result
import com.kcbiswash.nit3213.databinding.ActivityDashboardBinding
import com.kcbiswash.nit3213.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    private val adapter = EntityAdapter(mutableListOf()) { entity ->
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtra("entity_json", entity.toString())
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        val keypass = intent.getStringExtra("keypass").orEmpty()
        viewModel.load(keypass)

        lifecycleScope.launch {
            viewModel.state.collectLatest { result ->
                when (result) {
                    is Result.Loading -> binding.tvStatus.text = "Loading…"
                    is Result.Error -> binding.tvStatus.text = "Error: ${result.message}"
                    is Result.Success -> {
                        binding.tvStatus.text = "Total: ${result.data.entityTotal}"
                        adapter.submit(result.data.entities)
                    }
                }
            }
        }
    }
}
