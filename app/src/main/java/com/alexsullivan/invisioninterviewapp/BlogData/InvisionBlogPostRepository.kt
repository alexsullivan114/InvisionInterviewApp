package com.alexsullivan.invisioninterviewapp.BlogData

import io.reactivex.Observable

class InvisionBlogPostRepository(val blogService: InvisionBlogService): BlogPostRepository {

    override fun fetchLatestBlogPosts(): Observable<InvisionBlogPost> {
        return blogService
            .latestBlogPosts()
            .map(this::convertStringToBlogPost)
    }

    private fun convertStringToBlogPost(string: String): InvisionBlogPost {
        TODO("not implemented")
    }
}