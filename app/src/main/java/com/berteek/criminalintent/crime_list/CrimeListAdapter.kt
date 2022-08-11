package com.berteek.criminalintent.crime_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berteek.criminalintent.Crime
import com.berteek.criminalintent.databinding.ListItemMildCrimeBinding
import com.berteek.criminalintent.databinding.ListItemSeriousCrimeBinding

class CrimeListAdapter(
    private val crimes: List<Crime>,
    private val onCallPoliceListener: (View) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MildCrimeHolder(
        private val binding: ListItemMildCrimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.apply {
                crimeTitle.text = crime.title
                crimeDate.text = crime.date.toString()
            }
        }
    }

    class SeriousCrimeHolder(
        private val binding: ListItemSeriousCrimeBinding,
        onCallPoliceListener: (View) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.crimeCallPolice.setOnClickListener(onCallPoliceListener)
        }

        fun bind(crime: Crime) {
            binding.apply {
                crimeTitle.text = crime.title
                crimeDate.text = crime.date.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_MILD_CRIME -> {
                val binding = ListItemMildCrimeBinding.inflate(inflater, parent, false)
                MildCrimeHolder(binding)
            }
            VIEW_TYPE_SERIOUS_CRIME -> {
                val binding = ListItemSeriousCrimeBinding.inflate(inflater, parent, false)
                SeriousCrimeHolder(binding, onCallPoliceListener)
            }
            else -> {
                throw Exception("No such view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_MILD_CRIME)
            (holder as MildCrimeHolder).bind(crimes[position])
        else if (getItemViewType(position) == VIEW_TYPE_SERIOUS_CRIME)
            (holder as SeriousCrimeHolder).bind(crimes[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice)
            VIEW_TYPE_SERIOUS_CRIME
        else
            VIEW_TYPE_MILD_CRIME
    }

    override fun getItemCount(): Int = crimes.size

    companion object {
        private const val VIEW_TYPE_MILD_CRIME = 0
        private const val VIEW_TYPE_SERIOUS_CRIME = 1
    }
}