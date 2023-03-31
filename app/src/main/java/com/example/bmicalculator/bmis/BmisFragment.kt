package com.example.bmicalculator.bmis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bmicalculator.EventObserver
import com.example.bmicalculator.MainActivity
import com.example.bmicalculator.R
import com.example.bmicalculator.databinding.FragmentBmisBinding
import com.example.bmicalculator.models.BMI
import com.google.android.material.bottomsheet.BottomSheetDialog

private const val TAG = "BmisFragment"

class BmisFragment : Fragment() {

    private val viewModel by viewModels<BmisViewModel>()

    private lateinit var binding: FragmentBmisBinding

    override fun onStart() {
        super.onStart()

        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmisBinding.inflate(layoutInflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupListAdapter()
        onFabClick()
        setupDeleteBmiDialog()
    }

    private fun setupDeleteBmiDialog() {
        viewModel.deleteBmiDialog.observe(viewLifecycleOwner, EventObserver { _bmi: BMI ->
            context?.let { _context ->
                val dialog = BottomSheetDialog(_context)
                dialog.setContentView(
                    this.layoutInflater.inflate(
                        R.layout.dialog_bottomsheet_deleteitem,
                        null
                    )
                )
                dialog.findViewById<AppCompatButton>(R.id.btn_cancel)?.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.findViewById<AppCompatButton>(R.id.btn_delete)?.setOnClickListener {
                    viewModel.deleteBmi(_bmi)
                    dialog.dismiss()
                }
                dialog.show()
            }
        })
    }

    private fun onFabClick() {
        binding.fabCalculateNewBmi.setOnClickListener {
            val action = BmisFragmentDirections.actionBmisFragmentToAddNewBmiFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupListAdapter() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            binding.recyclerView.adapter = BmisListAdapter(viewmodel)
        } else {
            Log.w(
                "No init ViewModel",
                "ViewModel not initialized when attempting to set up adapter."
            )
        }
    }
}
