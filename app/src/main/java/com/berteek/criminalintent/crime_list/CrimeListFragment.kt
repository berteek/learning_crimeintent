package com.berteek.criminalintent.crime_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.berteek.criminalintent.R
import com.berteek.criminalintent.databinding.FragmentCrimeListBinding
import com.google.android.material.snackbar.Snackbar

class CrimeListFragment : Fragment() {

    private var _binding: FragmentCrimeListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val viewModel: CrimeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "Total crimes: ${viewModel.crimes.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeListBinding.inflate(layoutInflater, container, false)

        binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        val onCallPoliceListener : (View) -> Unit = { _ ->
            Snackbar.make(
                binding.root,
                R.string.calling_police_message,
                Snackbar.LENGTH_SHORT
            ).show()
        }
        binding.crimeRecyclerView.adapter = CrimeListAdapter(viewModel.crimes, onCallPoliceListener)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "CrimeListFragment"
    }
}