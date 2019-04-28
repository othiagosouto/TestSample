package br.com.tsouto.testsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.tsouto.testsample.data.PersonRepository
import br.com.tsouto.testsample.widget.HeaderView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val repository: PersonRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val headerView = findViewById<HeaderView>(R.id.header_view)

        headerView.setPerson(repository.getPerson())

    }
}
