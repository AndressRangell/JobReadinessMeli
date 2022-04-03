package andres.rangel.jobreadinessmeli.ui.viewmodel

import andres.rangel.jobreadinessmeli.data.network.MeliApi
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel() : ViewModel() {

    val description = MutableLiveData<String>()
    private val className = "DetailsViewModel"

    fun getDetail(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = MeliApi.retrofitService.getDetail(query)
            launch {
                if (call.isSuccessful) {
                    Log.i(className, "successful request to get product detail")
                    val detail = call.body()?.description ?: ""
                    description.postValue(detail)
                }
            }
        }
    }

}