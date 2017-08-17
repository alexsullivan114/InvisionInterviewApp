package com.alexsullivan.invisioninterviewapp.BlogPostList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.alexsullivan.invisioninterviewapp.BlogData.InvisionBlogPost
import com.alexsullivan.invisioninterviewapp.BlogData.InvisionBlogPostRepository
import com.alexsullivan.invisioninterviewapp.BlogData.InvisionBlogService
import com.alexsullivan.invisioninterviewapp.BlogPostViewing.BlogPostWebActivity
import com.alexsullivan.invisioninterviewapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity(), BlogPostClickListener {

    val presenter = buildPresenter()
    var disposable: Disposable? = null
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        disposable = presenter.getBlogPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toList()
            .subscribe({ posts ->
                recyclerView.adapter = BlogPostListAdapter(posts, this)
            }, {
                print("Aww no it didnt work")
            })
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    override fun blogPostClicked(blogPost: InvisionBlogPost) {
        if (blogPost.link == null) {
            // Should show some type of error here.
        }
        blogPost.link?.let {
            val intent = BlogPostWebActivity.buildIntent(it, this)
            startActivity(intent)
        }
    }

    private fun buildPresenter(): BlogPostPresenter {
        val service = Retrofit.Builder()
            .baseUrl(InvisionBlogService.baseUrl)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(InvisionBlogService::class.java)
        val blogPostRepository = InvisionBlogPostRepository(service)
        return BlogPostPresenterImpl(blogPostRepository)
    }
}
