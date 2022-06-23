package org.d3if2036.hitungkecepatan.ui.konsepglb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if2036.hitungkecepatan.databinding.ListKonsepglbBinding
import org.d3if2036.hitungkecepatan.ui.KonsepGlb

class KonsepGlbAdapter: RecyclerView.Adapter<KonsepGlbAdapter.ViewHolder>() {

    private val data = mutableListOf<KonsepGlb>()

    fun updateData(newData: List<KonsepGlb>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListKonsepglbBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(konsepGlb: KonsepGlb) = with(binding){
            konsepTextView.text = konsepGlb.konsepglb
            imageView.setImageResource(konsepGlb.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListKonsepglbBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}