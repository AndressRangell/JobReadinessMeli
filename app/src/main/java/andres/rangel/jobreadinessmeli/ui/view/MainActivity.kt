package andres.rangel.jobreadinessmeli.ui.view

import andres.rangel.jobreadinessmeli.data.model.Item
import andres.rangel.jobreadinessmeli.adapter.ItemAdapter
import andres.rangel.jobreadinessmeli.ui.viewmodel.SearchViewModel
import andres.rangel.jobreadinessmeli.databinding.ActivityMainBinding
import andres.rangel.jobreadinessmeli.utils.getFavorites
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private val viewModel by viewModels<SearchViewModel>()
    private val className = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.setOnQueryTextListener(this)

        viewModel.itemList.observe(this) {
            initRecyclerView(it)
        }

        binding.ivMenu.setOnClickListener {
            AlertToken().showAlertToken(this)
        }

        binding.ivFavorites.setOnClickListener {
            viewModel.getItems(this.getFavorites())
        }

    }

    private fun initRecyclerView(items: List<Item>) {
        adapter = ItemAdapter(items) { item ->
            val intentDetail = Intent(this, ProductDetailsActivity::class.java)
            intentDetail.putExtra("item", Gson().toJson(item))
            startActivity(intentDetail)
        }
        Log.i(className, "Updated product list")
        binding.rvItems.adapter = adapter
    }

    // Close the keyboard when pressing enter
    private fun hideKeyBoard() {
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            viewModel.getCategory(query.lowercase(), binding)
            Log.i(className, "search sent with the query $query")
            hideKeyBoard()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}