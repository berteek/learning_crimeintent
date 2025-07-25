package com.berteek.criminalintent.crime_list

import androidx.lifecycle.ViewModel
import com.berteek.criminalintent.Crime
import java.util.*

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    init {
        for (i in 0 until 100) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = Date(),
                isSolved = i % 2 == 0,
                requiresPolice = i % 3 == 0
            )
            crimes.add(crime)
        }
    }
}