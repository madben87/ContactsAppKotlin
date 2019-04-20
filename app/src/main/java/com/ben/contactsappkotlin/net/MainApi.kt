package com.ben.contactsappkotlin.net

import com.ben.contactsappkotlin.data.Person
import io.reactivex.Observable
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET



interface MainApi {

    @GET("test.json")
    fun loadData(): Observable<List<Person>>

    object Factory {

        fun create(): MainApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://test.php-cd.attractgroup.com/")
                .build()

            return retrofit.create(MainApi::class.java)
        }
    }
}