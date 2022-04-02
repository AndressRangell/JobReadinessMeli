package andres.rangel.jobreadinessmeli

import andres.rangel.jobreadinessmeli.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.setOnQueryTextListener(this)

        viewModel.itemList.observe(this) {
            initRecyclerView(it)
        }

    }

    private fun initRecyclerView(items: List<Item>) {
        adapter = ItemAdapter(items) { item ->
            val intentDetail = Intent(this, ProductDetailsActivity::class.java)
            intentDetail.putExtra("item", Gson().toJson(item))
            startActivity(intentDetail)
        }
        binding.rvItems.adapter = adapter
    }

    private fun hideKeyBoard() {
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            viewModel.getCategory(query.lowercase())
            hideKeyBoard()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}