package andres.rangel.jobreadinessmeli.ui.viewmodel

import andres.rangel.jobreadinessmeli.data.model.Item
import andres.rangel.jobreadinessmeli.data.model.ItemId
import andres.rangel.jobreadinessmeli.data.network.MeliApi
import andres.rangel.jobreadinessmeli.R
import andres.rangel.jobreadinessmeli.databinding.ActivityMainBinding
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel() : ViewModel() {

    val itemList = MutableLiveData<List<Item>>()
    private lateinit var binding: ActivityMainBinding
    private val className = "SearchViewModel"

    fun getCategory(query: String, binding: ActivityMainBinding) {
        this.binding = binding
        CoroutineScope(Dispatchers.IO).launch {
            val call = MeliApi.retrofitService.getCategory(query)
            launch {
                if (call.isSuccessful) {
                    Log.i(className, "successful request to get product category")
                    val categoryId = call.body()?.get(0)?.categoryId ?: ""
                    getHighlights(categoryId)
                }
            }
        }
    }

    private fun getHighlights(categoryId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = MeliApi.retrofitService.getHighlights("highlights/MLM/category/$categoryId")
            launch {
                if (call.isSuccessful) {
                    Log.i(className, "successful request to get top product id")
                    val idList = call.body() ?: ItemId()
                    // filters only elements of type ITEM
                    val ids = idList.content.filter { it.type == "ITEM" }.map { it.id }
                    if (ids.isNotEmpty()) {
                        getItems(ids.toString().replace(Regex("(^\\[|\\]\$)"), ""))
                    } else {
                        Snackbar.make(
                            binding.root,
                            binding.root.context.getString(R.string.result_not_found),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        binding.root.context.getString(R.string.result_not_found),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getItems(idList: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = MeliApi.retrofitService.getItems(idList)
            launch {
                if (call.isSuccessful) {
                    Log.i(className, "successful request for product details")
                    itemList.postValue(call.body())
                }
            }
        }
    }

}