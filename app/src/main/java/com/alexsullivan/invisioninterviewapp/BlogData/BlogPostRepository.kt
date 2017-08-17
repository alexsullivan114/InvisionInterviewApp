package com.alexsullivan.invisioninterviewapp.BlogData

import io.reactivex.Observable

interface BlogPostRepository {
    fun fetchLatestBlogPosts(): Observable<InvisionBlogPost>
}