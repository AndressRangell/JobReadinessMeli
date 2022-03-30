package andres.rangel.jobreadinessmeli

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(): ViewModel() {

    private val className: String = "SearchViewModel"

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> get() = _search

    private val token = "APP_USR-3424956346656258-032917-c0f09b6f583aa3049f6e9dbff507d7b0-316674397"

    fun getCategory(query: String){
        viewModelScope.launch {
            try{
                val call = withContext(Dispatchers.IO) {
                    MeliApi.retrofitService.getCategory(query, token)
                }
                if(call.isSuccessful){
                    val category = call.body()?.get(0)?.categoryName ?: "Undefined"
                    _search.postValue(category)
                }
            }catch (exception: Exception){
                Log.e(className, exception.message.toString())
            }
        }
    }

}