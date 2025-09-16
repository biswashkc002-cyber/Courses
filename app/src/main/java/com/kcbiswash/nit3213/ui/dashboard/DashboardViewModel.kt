package com.kcbiswash.nit3213.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.kcbiswash.nit3213.data.MainRepository
import com.kcbiswash.nit3213.data.Result
import com.kcbiswash.nit3213.network.DashboardResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<Result<DashboardResponse>>(Result.Loading)
    val state: StateFlow<Result<DashboardResponse>> = _state

    fun load(keypass: String) {
        _state.value = Result.Loading
        viewModelScope.launch {
            _state.value = repo.dashboard(keypass)
        }
    }

    companion object {
        fun summaryFor(item: JsonObject): String {
            val entries = item.entrySet()
                .filter { it.key != "description" }
                .joinToString("\n") { "${it.key}: ${it.value.asStringOrRaw()}" }
            return entries.ifEmpty { "(No summary fields)" }
        }

        fun JsonObject.asFullMap(): List<Pair<String, String>> =
            entrySet().map { it.key to it.value.asStringOrRaw() }

        private fun JsonElement.asStringOrRaw(): String =
            if (isJsonPrimitive && asJsonPrimitive.isString) asString else toString()
    }
}
