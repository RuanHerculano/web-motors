package com.pedrex.webmotors.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pedrex.webmotors.R
import com.pedrex.webmotors.databinding.ActivityMainBinding
import com.pedrex.webmotors.presentation.model.ItemModel
import com.pedrex.webmotors.presentation.screens.adapters.ItemAdapter
import com.pedrex.webmotors.presentation.screens.fragments.DetailItemFragment
import com.pedrex.webmotors.presentation.screens.states.CarStates
import com.pedrex.webmotors.presentation.screens.viewmodel.CartViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var adapterItems: ItemAdapter
    private lateinit var viewModel: CartViewModel
    private lateinit var binding: ActivityMainBinding
    private var isLoading: Boolean = false
    private val listCars: ArrayList<ItemModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        bindViews()

        viewModel = CartViewModel()

        bindAdapter(viewModel, binding)

        bindObserverViewModel()

        viewModel.input.populateList()

    }

    private fun bindViews() {
        binding.apply {
            includeToolbar.apply {
                tvTitle.text = "Carros"
            }
            includeList.also {
                it.isLoading = true
                it.items.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                        if (!isLoading) {
                            if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listCars.size - 1) {
                                //bottom of list!
                                loadMore()
                                isLoading = true
                            }
                        }
                    }
                })
            }

        }
    }

    private fun bindObserverViewModel() {
        viewModel.output.also {
            it.selectCarLiveData.observe(this@MainActivity, Observer { item ->
                showDetail(item)
            })
            it.populateCarsLiveData.observe(this@MainActivity, Observer { state ->
                handlerState(state)
            })
        }
    }

    private fun loadMore() {
        viewModel.input.populateList()
    }

    private fun handlerState(state: CarStates) {
        binding.includeList.isLoading = false
        isLoading = false
        when (state) {
            is CarStates.Error -> {
                showError(state.message)
            }
            is CarStates.LoadCars -> {
                populateList(state.cars)
            }
        }
    }

    private fun showError(message: String) {
        this.let {
            val snack = Snackbar.make(
                it.findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_INDEFINITE
            )
            snack.setAction("Ok") {
                snack.dismiss()
            }
            snack.show()
        }
    }

    private fun bindAdapter(
        viewModel: CartViewModel,
        binding: ActivityMainBinding
    ) {
        adapterItems = ItemAdapter(listCars, viewModel)
        binding.includeList.items.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterItems
        }

    }

    private fun populateList(list: List<ItemModel>) {
        listCars.addAll(list)
        adapterItems.notifyDataSetChanged()
    }

    private fun showDetail(car: ItemModel) {
        val fragment = DetailItemFragment.newInstance(car)
        fragment.show(supportFragmentManager, "")
    }
}
