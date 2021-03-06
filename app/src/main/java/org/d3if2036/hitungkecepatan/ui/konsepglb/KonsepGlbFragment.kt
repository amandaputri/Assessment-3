package org.d3if2036.hitungkecepatan.ui.konsepglb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2036.hitungkecepatan.R
import org.d3if2036.hitungkecepatan.databinding.TampilanListBinding
import org.d3if2036.hitungkecepatan.network.ApiStatus
import org.d3if2036.hitungkecepatan.ui.KonsepGlb

class KonsepGlbFragment: Fragment() {

    private val viewModel: KonsepGlbViewModel by lazy {
        ViewModelProvider(this).get(KonsepGlbViewModel::class.java)
    }

    private lateinit var myAdapter: KonsepGlbAdapter
    private lateinit var binding: TampilanListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TampilanListBinding.inflate(layoutInflater, container, false)
        myAdapter = KonsepGlbAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        return binding.root
    }

//    private fun getData(): List<KonsepGlb>{
//        return listOf(
//            KonsepGlb("coba", R.drawable.gambar1),
//            KonsepGlb("coba2", R.drawable.gambar1),
//            KonsepGlb("coba3", R.drawable.gambar1),
//            KonsepGlb("coba4", R.drawable.gambar1),
//        )
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner,{
            myAdapter.updateData(it)
        })

        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })

        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}