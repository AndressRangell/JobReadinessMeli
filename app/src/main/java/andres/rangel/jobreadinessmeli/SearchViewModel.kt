package andres.rangel.jobreadinessmeli

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(): ViewModel() {

    val itemList = MutableLiveData<List<Item>>()

    fun getCategory(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = MeliApi.retrofitService.getCategory(query)
            launch {
                if(call.isSuccessful){
                    val categoryId = call.body()?.get(0)?.categoryId ?: ""
                    getHighlights(categoryId)
                }
            }
        }
    }

    private fun getHighlights(categoryId: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = MeliApi.retrofitService.getHighlights("highlights/MLM/category/$categoryId")
            launch {
                if(call.isSuccessful){
                    val idList = call.body() ?: ItemId()
                    val ids = idList.content.map { it.id }
                    getItems(ids.toString().replace(Regex("(^\\[|\\]\$)"), ""))
                }
            }
        }
    }

    private fun getItems(idList: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = MeliApi.retrofitService.getItems(idList)
            launch {
                if(call.isSuccessful){
                    itemList.postValue(call.body())
                }
            }
        }
    }

}