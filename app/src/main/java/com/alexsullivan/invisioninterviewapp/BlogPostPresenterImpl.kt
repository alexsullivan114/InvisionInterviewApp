package com.alexsullivan.invisioninterviewapp

import com.alexsullivan.invisioninterviewapp.BlogData.BlogPostRepository
import com.alexsullivan.invisioninterviewapp.BlogData.InvisionBlogPost
import io.reactivex.Observable

class BlogPostPresenterImpl(val blogPostRepository: BlogPostRepository): BlogPostPresenter {

    override fun getBlogPosts() = blogPostRepository.fetchLatestBlogPosts()
}

interface BlogPostPresenter {
    fun getBlogPosts(): Observable<InvisionBlogPost>
}