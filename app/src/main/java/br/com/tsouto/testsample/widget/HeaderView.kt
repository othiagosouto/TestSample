package br.com.tsouto.testsample.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import br.com.tsouto.testsample.Person
import br.com.tsouto.testsample.R

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val nameTextView: TextView
    private val ageTextView: TextView
    private val submitButton: Button

    init {
        val view = LinearLayout.inflate(context, R.layout.header_view, this)
        nameTextView = view.findViewById(R.id.tv_name)
        ageTextView = view.findViewById(R.id.tv_age)
        submitButton = view.findViewById(R.id.btn_submit)
    }

    fun setPerson(person: Person) {
        nameTextView.text = person.name
        ageTextView.text = context.getString(R.string.age_pattern, person.age)
    }

    fun setButtonClick(click: () -> Unit) {
        submitButton.setOnClickListener {
            click()
        }
    }
}