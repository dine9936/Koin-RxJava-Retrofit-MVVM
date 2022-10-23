package com.test.recyclerview.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.test.recyclerview.R
import com.test.recyclerview.data.models.Content
import com.test.recyclerview.databinding.ActivityMainBinding
import com.test.recyclerview.ui.main.adapter.MainAdapter
import com.test.recyclerview.ui.main.viewModel.MainViewModel
import com.test.recyclerview.utils.ApiStatus
import com.test.recyclerview.utils.Common.Companion.mySnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnRefreshListener{

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainSwipe.setOnRefreshListener(this)



        setUpUI()
        setUpObserver()



    }



    private fun setUpObserver() {
        mainViewModel.trendingData.observe(this) {
            when (it) {
                is ApiStatus.Loading -> {
                    binding.mainSwipe.isRefreshing = true
                }

                is ApiStatus.Success -> {
                    val content = it.data.contents
                    renderList(content)
                    binding.mainSwipe.isRefreshing = false
                }

                is ApiStatus.Error -> {
                    binding.mainSwipe.isRefreshing = false
                    mySnackBar(binding.mainRootLayout, it.message.toString())
                }

                is ApiStatus.Exception -> {
                    binding.mainSwipe.isRefreshing = false
                    mySnackBar(binding.mainRootLayout, it.e.toString())
                }

                else -> {
                    binding.mainSwipe.isRefreshing = false
                }
            }
        }
    }

    private fun setUpUI() {
        mainAdapter = MainAdapter(arrayListOf())
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = mainAdapter
        }
    }

    private fun renderList(contents: List<Content>) {
        with(mainAdapter) {
            addData(contents)
            notifyDataSetChanged()
        }
    }

    override fun onRefresh() {
        mainViewModel.onRefresh()
    }


}