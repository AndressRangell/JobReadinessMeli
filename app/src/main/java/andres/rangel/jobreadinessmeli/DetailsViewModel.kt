package andres.rangel.jobreadinessmeli

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(): ViewModel() {

    val description = MutableLiveData<String>()

    fun getDetail(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = MeliApi.retrofitService.getDetail(query)
            launch {
                if(call.isSuccessful){
                    val detail = call.body()?.description ?: ""
                    description.postValue(detail)
                }
            }
        }
    }

}