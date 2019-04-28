package br.com.tsouto.testsample.data.remote

import br.com.tsouto.testsample.Person
import br.com.tsouto.testsample.data.PersonDataSource

class RemoteDataSource : PersonDataSource {
    override fun fetchPerson(): Person = Person("Remote", 30)
}