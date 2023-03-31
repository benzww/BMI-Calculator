package com.example.bmicalculator.bmis



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bmicalculator.databinding.BmiItemBinding
import com.example.bmicalculator.models.BMI

class BmiViewHolder(val binding: BmiItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: BmisViewModel, bmi: BMI) {
        binding.viewmodel = viewModel
        binding.bmi = bmi
    }

    companion object {
        fun from(parent: ViewGroup?): BmiViewHolder {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val binding = BmiItemBinding.inflate(layoutInflater, parent, false)
            return BmiViewHolder(binding)
        }
    }
}

class BmisListAdapter(private val viewModel: BmisViewModel) :
    ListAdapter<BMI, BmiViewHolder>(BmiDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BmiViewHolder {
        return BmiViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BmiViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }
}

class BmiDiffCallback : DiffUtil.ItemCallback<BMI>() {
    override fun areItemsTheSame(oldItem: BMI, newItem: BMI): Boolean {
        return oldItem.bmiId == newItem.bmiId
    }

    override fun areContentsTheSame(oldItem: BMI, newItem: BMI): Boolean {
        return oldItem == newItem
    }
}