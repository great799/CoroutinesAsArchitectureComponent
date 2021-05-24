package com.app.aman.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.aman.R
import com.app.aman.base.BaseActivity
import com.app.aman.databinding.ActivityMainBinding
import com.app.aman.network.model.StockDetail
import com.app.aman.repository.MainRepository
import com.app.aman.utils.ApiResponseWrapper
import com.app.aman.view_model.MainActivityViewModel
import com.app.aman.view_model_factory.MainActivityViewModelFactory

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<out ViewModel> {
        return MainActivityViewModel::class.java
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return MainActivityViewModelFactory(MainRepository())
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private val sids = arrayListOf<String>("TCS", "RELI", "HDBK", "INFY", "ITC")

    private fun initBindingAndViewModel() {
        binding = getBinding() as ActivityMainBinding
        viewModel = getViewModel() as MainActivityViewModel
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindingAndViewModel()
        initObservers()
        viewModel.getListOfStocks(sids)
    }

    private fun setAdapter(stocks: List<StockDetail>) {
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = StockListAdapter(stocks)
    }

    private fun initObservers() {

//        viewModel.fetchAndRefreshStocksLiveData(sids).observe(this, Observer {
//            when (it) {
//                is ApiResponseWrapper.Success -> {
//                    setAdapter(it.value.data)
//                }
//            }
//        })

        viewModel.loadingLiveData.observe(this, Observer {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.apiErrorLiveData.observe(this, Observer {
            showToast("$it.code: ${it.errorMessage ?: ""}", Toast.LENGTH_LONG)
        })

        viewModel.networkErrorLiveData.observe(this, Observer {
            showToast(it, Toast.LENGTH_LONG)
        })

        viewModel.apiResponseLiveData.observe(this, Observer {
            setAdapter(it.data)
        })
    }

    override fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
//        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.add(binding.mainFrame.id, fragment, tag)
//        if (addToBackStack) {
//            transaction.addToBackStack(tag)
//        }
//        transaction.commit()
    }

    override fun replaceFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
//        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.replace(binding.mainFrame.id, fragment, tag)
//        if (addToBackStack) {
//            transaction.addToBackStack(tag)
//        }
//        transaction.commit()
    }
}