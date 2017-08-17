package com.alexsullivan.invisioninterviewapp.BlogData

import io.reactivex.Observable

class InvisionBlogPostRepository(val blogService: InvisionBlogService): BlogPostRepository {

    override fun fetchLatestBlogPosts(): Observable<InvisionBlogPost> {
//        return blogService
//            .latestBlogPosts()
        // TODO: Debug XML retrofit processing. For now just return some pre-canned responses.
        return Observable.fromIterable(buildFakeBlogPosts())
    }

    private fun buildFakeBlogPosts(): List<InvisionBlogPost> {
        val blogPostOne = InvisionBlogPost("http://engineering.invisionapp.com/post/working-as-a-remote-intern/", "6 Unexpected Things I Learned from Working as a Remote Intern")
        val blogPostTwo = InvisionBlogPost("http://engineering.invisionapp.com/post/twin-cities-networking-event/", "Twin Cities Networking Event")
        val blogPostThree = InvisionBlogPost("http://engineering.invisionapp.com/post/how-we-learned-go-in-a-day/", "How we learned go in a day")

        return mutableListOf(blogPostOne, blogPostTwo, blogPostThree)
    }
}