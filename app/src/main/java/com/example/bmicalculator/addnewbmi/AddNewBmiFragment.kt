package com.example.bmicalculator.addnewbmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bmicalculator.EventObserver
import com.example.bmicalculator.databinding.FragmentAddNewBmiBinding
import com.example.bmicalculator.utils.setupSnackbar
import com.google.android.material.snackbar.Snackbar

class AddNewBmiFragment : Fragment() {

    private lateinit var binding: FragmentAddNewBmiBinding
    private val viewModel by viewModels<AddNewBmiViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewBmiBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackBar()
        setupNavigation()
    }

    private fun setupSnackBar() {
        view?.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

    private fun setupNavigation() {
        viewModel.calculateBmiEvent.observe(viewLifecycleOwner, EventObserver {
            val action =
                AddNewBmiFragmentDirections.actionAddNewBmiFragmentToResultFragment(viewModel.bmi)
            findNavController().navigate(action)
        })
    }
}
