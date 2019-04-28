package br.com.tsouto.testsample.data

import br.com.tsouto.testsample.Person

class PersonRepository(private val personDataSource: PersonDataSource) {
    fun getPerson(): Person = personDataSource.fetchPerson()
}