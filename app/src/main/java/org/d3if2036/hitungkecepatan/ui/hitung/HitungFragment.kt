package org.d3if2036.hitungkecepatan.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if2036.hitungkecepatan.R
import org.d3if2036.hitungkecepatan.databinding.FragmentHitungBinding
import org.d3if2036.hitungkecepatan.db.KecepatanDb
import org.d3if2036.hitungkecepatan.model.HasilKecepatan

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding
    private val viewModel: HitungViewModel by lazy {
        val db = KecepatanDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungKec() }
        binding.RumusButton.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_rumusFragment
            )
        }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasilKecepatan().observe(requireActivity(), {showResult(it)})

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment)
                return true
            }

            R.id.menu_konsep -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_konsepGlbFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hitungKec() {
        val jarak = binding.jarakInp.text.toString()
        if (TextUtils.isEmpty(jarak)) {
            Toast.makeText(context, R.string.jarak_invalid, Toast.LENGTH_LONG).show()
                return
        }

        val waktu = binding.waktuInp.text.toString()
        if (TextUtils.isEmpty(waktu)) {
            Toast.makeText(context, R.string.waktu_invalid, Toast.LENGTH_LONG).show()
                return
        }

        viewModel.hitungKec(
            jarak.toFloat(),
            waktu.toFloat()
        )
    }

    private fun showResult(result: HasilKecepatan?){
        if (result == null) return
        binding.total.text = getString(R.string.hasilKecepatan, result.kecepatan)
        binding.buttonGroup.visibility = View.VISIBLE
    }

    private fun shareData(){
        val message = getString(R.string.bagikantemp,
            binding.jarakInp.text,
            binding.waktuInp.text,
            binding.total.text)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}