package br.com.tsouto.testsample

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.tsouto.testsample.data.PersonDataSource
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.loadKoinModules

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setup() {
        val remoteSourceMock: PersonDataSource = mockk()
        every { remoteSourceMock.fetchPerson() } returns Person("Mock", 25)
        loadKoinModules(
            module(override = true) {
                single { remoteSourceMock }
            }
        )
    }

    @After
    fun tearDown() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun onCreate_shouldUpdateHeaderInfoWithInfoProvidedByRepository() {
        val scenario: ActivityScenario<MainActivity> = ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.tv_name))
            .check(ViewAssertions.matches(ViewMatchers.withText("Mock")))
        Espresso.onView(ViewMatchers.withId(R.id.tv_age))
            .check(ViewAssertions.matches(ViewMatchers.withText("25 anos")))

        scenario.close()
    }

}