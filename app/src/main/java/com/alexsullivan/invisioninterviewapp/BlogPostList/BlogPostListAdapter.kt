package com.alexsullivan.invisioninterviewapp.BlogPostList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alexsullivan.invisioninterviewapp.BlogData.InvisionBlogPost
import com.alexsullivan.invisioninterviewapp.R

class BlogPostListAdapter(private val blogPosts: List<InvisionBlogPost>,
                          private val blogPostClickListener: BlogPostClickListener): RecyclerView.Adapter<BlogPostListAdapter.BlogPostViewHolder>() {

    override fun onBindViewHolder(holder: BlogPostViewHolder, position: Int) {
        val blogPost = blogPosts[position]
        holder.titleTextView.text = blogPost.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogPostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog_post_list_item, parent, false)
        return BlogPostViewHolder(view)
    }

    override fun getItemCount() = blogPosts.size

    inner class BlogPostViewHolder(val root: View): RecyclerView.ViewHolder(root) {
        val titleTextView = root.findViewById(R.id.title) as TextView
        init {
            root.setOnClickListener {
                blogPostClickListener.blogPostClicked(blogPosts[adapterPosition])
            }
        }
    }
}

interface BlogPostClickListener {
    fun blogPostClicked(blogPost: InvisionBlogPost)
}