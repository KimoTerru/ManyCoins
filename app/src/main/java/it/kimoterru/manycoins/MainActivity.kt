package it.kimoterru.manycoins

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.kimoterru.manycoins.adapters.CoinAdapter
import it.kimoterru.manycoins.databinding.ActivityMainBinding
import it.kimoterru.manycoins.network.Status
import it.kimoterru.manycoins.network.models.CoinResponse
import it.kimoterru.manycoins.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var viewModel: MyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        initViews()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel!!.getCoinsMain()
    }

    private fun initViews() {
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun initObservers() {
        viewModel?.coinsLiveData?.observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    displayData(it.data)
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun displayData(response: CoinResponse?) {
        binding.recyclerMain.adapter = CoinAdapter(response?.coins ?: listOf())

        if (response?.coins.isNullOrEmpty()) {
            binding.cons.isVisible = false
        }
    }
}