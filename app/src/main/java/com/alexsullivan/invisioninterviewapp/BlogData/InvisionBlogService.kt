package com.alexsullivan.invisioninterviewapp.BlogData

import io.reactivex.Observable
import retrofit2.http.GET

// TODO: Normally I'd put this in a separate module, but build problems necessitated avoiding that
// step for now.
interface InvisionBlogService {
    companion object {
        val baseUrl = "http://engineering.invisionapp.com/"
    }

    @GET("index.xml")
    fun latestBlogPosts(): Observable<String>
}