package com.alexsullivan.invisioninterviewapp.BlogData

import io.reactivex.Observable

/**
 * A repository for fetching and consuming blog posts. Could be through the database or through
 * the network, or a memory cache.
 */
interface BlogPostRepository {
    fun fetchLatestBlogPosts(): Observable<InvisionBlogPost>
}