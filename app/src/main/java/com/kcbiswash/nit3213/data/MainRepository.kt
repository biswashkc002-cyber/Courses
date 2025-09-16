package com.kcbiswash.nit3213.data

import com.kcbiswash.nit3213.network.ApiService
import com.kcbiswash.nit3213.network.DashboardResponse
import com.kcbiswash.nit3213.network.LoginRequest
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun login(campus: String, username: String, password: String): Result<String> =
        try {
            val res = api.login(campus, LoginRequest(username, password))
            Result.Success(res.keypass)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Login failed")
        }

    suspend fun dashboard(keypass: String): Result<DashboardResponse> =
        try {
            Result.Success(api.dashboard(keypass))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Failed to load dashboard")
        }
}
