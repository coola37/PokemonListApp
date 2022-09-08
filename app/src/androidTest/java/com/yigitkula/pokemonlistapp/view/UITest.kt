package com.yigitkula.pokemonlistapp.view

import android.os.RemoteException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.google.android.apps.common.testing.accessibility.framework.replacements.Point
import com.yigitkula.pokemonlistapp.R
import okhttp3.internal.Util
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.AdditionalMatchers.not


@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UITest {
   @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val coordinates: Array<Point?> = arrayOfNulls<Point>(4)
        coordinates[0] = Point(248, 1520)
        coordinates[1] = Point(248, 929)
        coordinates[2] = Point(796, 1520)
        coordinates[3] = Point(796, 929)
        try {
            if (!uiDevice.isScreenOn) {
                uiDevice.wakeUp()
                uiDevice.swipe(coordinates, 10)
            }
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
        @Test
        fun PK01_listFragmentToDetailFragmentTest() {
            Utils.sleep(3500)
            onView(withText("Bulbasaur")).perform(click())
            Utils.sleep(2000)
            onView(withId(com.yigitkula.pokemonlistapp.R.id.detailFragmentLayout)).check(matches(
                isDisplayed()))
            onView(withText("Weight:")).check(matches(isDisplayed()))
            Utils.sleep(2000)
            onView(withText("Bulbasaur")).check(matches(isDisplayed()))
        }

        @Test
        fun PK02_detailFragmentToPopupWindow(){
            Utils.sleep(3500)

            onView(withText("Squirtle")).perform(click())
            Utils.sleep(2000)

            onView(withId(com.yigitkula.pokemonlistapp.R.id.button)).perform(click())
            Utils.sleep(2000)

            val mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            val ok: UiObject2? = mUiDevice.findObject(By.textContains("CANCEL"))
            Utils.sleep(800)
            ok?.isChecked

        }

        @Test
        fun PK03_popupWindowBackToDetailFragment(){
            Utils.sleep(3500)

            onView(withText("Bulbasaur")).perform(click())
            Utils.sleep(2000)

            onView(withId(com.yigitkula.pokemonlistapp.R.id.button)).perform(click())
            Utils.sleep(2000)

            val mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            val ok: UiObject2? = mUiDevice.findObject(By.textContains("CANCEL"))
            Utils.sleep(800)
            ok?.click()
            Utils.sleep(2000)

            onView(withId(com.yigitkula.pokemonlistapp.R.id.button)).check(matches(isDisplayed()))

            Utils.sleep(4000)
        }

    @Test
    fun PK04_bottomNavigationHomeButtonTest(){
        Utils.sleep(3500)
        onView(withText("Bulbasaur")).perform(click())
        Utils.sleep(2000)
        onView(withId(R.id.button)).check(matches(isDisplayed()))
        Utils.sleep(2000)
        onView(withId(R.id.home)).perform(click())
        Utils.sleep(2000)
        onView(withText("Ivysaur")).check(matches(isDisplayed()))
        onView(withText("Venusaur")).check(matches(isDisplayed()))
        onView(withText("Charmander")).check(matches(isDisplayed()))
        Utils.sleep(2000)
    }

    @Test
    fun PK05_bottomNavigationExitButtonTest(){
        Utils.sleep(3500)
        onView(withId(R.id.exit)).perform(click())
        Utils.sleep(2000)
      //  onView(withText("Exit")).check(doesNotExist());
        Utils.sleep(2000)
    }
    @Test
    fun PK_06bottomNavigationExitButtonTest2(){
        Utils.sleep(2000)
        onView(withText("Ivysaur")).check(matches(isDisplayed()))
        onView(withText("Venusaur")).check(matches(isDisplayed()))
        onView(withText("Charmander")).check(matches(isDisplayed()))
        onView(withId(R.id.exit)).check(matches(isDisplayed()))

    }

}

private fun UiDevice.swipe(coordinates: Array<Point?>, i: Int) {

}
