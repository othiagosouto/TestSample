package br.com.tsouto.testsample.widget

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.tsouto.testsample.Person
import br.com.tsouto.testsample.R
import br.com.tsouto.testsample.TestActivity
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.stopKoin

@RunWith(AndroidJUnit4::class)
class HeaderViewTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun setPerson_shouldUpdatePersonInfo() {
        val scenario: ActivityScenario<TestActivity> = ActivityScenario.launch(TestActivity::class.java)
        scenario.onActivity { activity ->
            val view = HeaderView(activity)
            activity.setContentView(view)
            view.setPerson(Person("Thiago Souto", 28))
        }

        onView(withId(R.id.tv_name)).check(matches(withText("Thiago Souto")))
        onView(withId(R.id.tv_age)).check(matches(withText("28 anos")))
        scenario.close()
    }


    @Test
    fun setPerson_shouldCallListenerAfterSubmitClick() {
        val scenario: ActivityScenario<TestActivity> = ActivityScenario.launch(TestActivity::class.java)
        val mockedFunction: () -> Unit = mockk(relaxed = true)
        scenario.onActivity { activity ->
            val view = HeaderView(activity)
            activity.setContentView(view)
            view.setPerson(Person("Thiago Souto", 28))
            view.setButtonClick(mockedFunction)
        }

        onView(withId(R.id.btn_submit)).perform(click())
        verify { mockedFunction() }
        scenario.close()
    }
}