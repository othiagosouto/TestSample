package br.com.tsouto.testsample.data

import br.com.tsouto.testsample.Person

interface PersonDataSource {

    fun fetchPerson(): Person
}