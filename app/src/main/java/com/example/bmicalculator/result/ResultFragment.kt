package com.example.bmicalculator.result



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bmicalculator.EventObserver
import com.example.bmicalculator.databinding.FragmentResultBinding

private const val TAG = "ResultFragment"

class ResultFragment : Fragment() {


    private lateinit var binding: FragmentResultBinding
    private val viewModel by viewModels<ResultViewModel>()
    private val safeArgs: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: SafeArgs: ${safeArgs.bmi}")
        viewModel.getBmi(safeArgs.bmi)

        setupRecalculateBmi()
        setNavigationToBmiFragment()
    }

    private fun setupRecalculateBmi() {
        binding.btnRecalculate.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToAddNewBmiFragment()
            findNavController().navigate(action)
        }
    }

    private fun setNavigationToBmiFragment() {

        viewModel.saveBmiEvent.observe(viewLifecycleOwner, EventObserver {
            val action = ResultFragmentDirections.actionResultFragmentToBmisFragment()
            findNavController().navigate(action)
        })
    }
}