package com.ben.contactsappkotlin.data

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ben.contactsappkotlin.net.MainApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class PersonViewModel : ViewModel() {

    var persons: MutableLiveData<List<Person>>? = null
    val disposables: CompositeDisposable = CompositeDisposable()

    fun getData() : MutableLiveData<List<Person>> {
        if (persons == null) {
            persons = MutableLiveData()
            loadData()
        }
        return persons as MutableLiveData<List<Person>>
    }

    private fun loadData() {
        val repository = MainApi.Factory.create()
        disposables.add(repository.loadData().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer {
                persons?.value = it
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}