package com.dansdev.kotlinmvvm.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.dansdev.kotlinmvvm.domain.analytic.Event
import com.dansdev.kotlinmvvm.presentation.MainViewModel
import com.dansdev.kotlinmvvm.presentation.core.model.AlertMessage
import com.livinglifetechway.k4kotlin.core.androidx.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

abstract class CoreFragment<VB : ViewBinding, VM : CoreViewModel<*>>(@LayoutRes layoutId: Int, private val hasDarkStatusBar: Boolean = false) : Fragment(layoutId) {

    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) binding = onInflateViewBinding(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        sharedViewModel.sendAnalytics(Event.CreateFragment(javaClass.simpleName))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady(view, savedInstanceState)
        setupCoreObservers()
        setupObserveConnection()
        onSetupObservers(viewModel)
    }

    private fun setupCoreObservers() {
        viewModel.onNavigateAction().observe(viewLifecycleOwner) { navDir ->
            navDir?.let { findNavController().navigate(it) }
        }
        viewModel.onShowError().observe(viewLifecycleOwner) { message ->
            when (message) {
                is AlertMessage.ErrorMessage -> toast(message.content(resources).toString())
                is AlertMessage.SuccessMessage -> toast(message.content(resources).toString())
                else -> throw IllegalArgumentException("You try to show new message type, please handle it")
            }
        }
    }

    private fun setupObserveConnection() {
        lifecycleScope.launchWhenCreated {
            sharedViewModel.onConnectedStateUpdate().collect { hasConnection ->
                withContext(Dispatchers.Main) {
                    //handle connection
                }
            }
        }
    }

    abstract val viewModel: VM
    abstract val sharedViewModel: MainViewModel

    abstract fun onInflateViewBinding(view: View): VB
    abstract fun onSetupObservers(viewModel: VM)
    abstract fun onViewReady(view: View, savedInstanceState: Bundle?)
}
