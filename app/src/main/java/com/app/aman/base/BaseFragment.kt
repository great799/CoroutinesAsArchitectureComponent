package com.app.aman.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.aman.utils.AppViewModelScope
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {
    private lateinit var binding: ViewDataBinding
    private lateinit var viewModel: ViewModel
    abstract fun getLayoutId(): Int
    abstract fun getViewModelClass(): Class<out ViewModel>
    abstract fun getViewModelFactory(): ViewModelProvider.Factory?
    abstract fun getViewModelScope(): AppViewModelScope
    private var activity: BaseActivity? = null

    protected fun getViewModel(): ViewModel {
        return viewModel
    }

    protected fun getBinding(): ViewDataBinding {
        return binding
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as BaseActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        viewModel = when (getViewModelScope()) {
            AppViewModelScope.FRAGEMNT -> {
                ViewModelProviders.of(this, getViewModelFactory()).get(getViewModelClass())
            }
            AppViewModelScope.ACTIVITY -> {
                ViewModelProviders.of(activity!!, getViewModelFactory()).get(getViewModelClass())
            }
        }
        return binding.root
    }

    protected fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        activity?.addFragment(fragment, addToBackStack, tag)
    }

    protected fun replaceFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        activity?.replaceFragment(fragment, addToBackStack, tag)
    }

    protected fun showLoading() {
        activity?.showLoading()
    }

    protected fun hideLoading() {
        activity?.hideLoading()
    }

    protected fun showToast(message: String, length: Int) {
        activity?.showToast(message, length)
    }

    protected fun handleApiError(error: String?) {
        error?.let { showToast(it, Snackbar.LENGTH_LONG) }
    }
}