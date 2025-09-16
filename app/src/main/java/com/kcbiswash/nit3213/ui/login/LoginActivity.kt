package com.kcbiswash.nit3213.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.kcbiswash.nit3213.data.Result
import com.kcbiswash.nit3213.databinding.ActivityLoginBinding
import com.kcbiswash.nit3213.ui.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text?.toString()?.trim().orEmpty()
            val password = binding.etPassword.text?.toString()?.trim().orEmpty()
            val campus = binding.etCampus.text?.toString()?.trim()?.lowercase().orEmpty()
            val campusValue = campus.ifEmpty { "br" }

            viewModel.doLogin(campusValue, username, password)
        }

        lifecycleScope.launch {
            viewModel.state.collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.tvStatus.isVisible = true
                        binding.tvStatus.text = "Logging in…"
                    }
                    is Result.Success -> {
                        binding.tvStatus.isVisible = true
                        binding.tvStatus.text = "Login success"
                        startActivity(
                            Intent(this@LoginActivity, DashboardActivity::class.java).apply {
                                putExtra("keypass", result.data)
                            }
                        )
                    }
                    is Result.Error -> {
                        binding.tvStatus.isVisible = true
                        binding.tvStatus.text = "Login failed: ${result.message}"
                    }
                }
            }
        }
    }
}
