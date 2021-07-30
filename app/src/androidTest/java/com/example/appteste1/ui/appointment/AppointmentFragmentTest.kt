package com.example.appteste1.ui.appointment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.*
import com.example.appteste1.R
import org.junit.Test

class AppointmentFragmentTest {

    @Test
    fun testDisplayIsCorrect() {
        Log.i("FRAGMENT TEST", "TEST")
        val scenario = launchFragmentInContainer<SaveAppointmentFragment>()

        onView(withId(R.id.pickDateBtn)).check(ViewAssertions.matches(withEffectiveVisibility(VISIBLE)))
    }

}

