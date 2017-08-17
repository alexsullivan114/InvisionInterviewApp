package com.alexsullivan.invisioninterviewapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alexsullivan.invisioninterviewapp.BlogData.InvisionBlogPostRepository
import com.alexsullivan.invisioninterviewapp.BlogData.InvisionBlogService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class MainActivity : AppCompatActivity() {

    val presenter = buildPresenter()
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        disposable = presenter.getBlogPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    private fun buildPresenter(): BlogPostPresenter {
        val service = Retrofit.Builder()
            .baseUrl(InvisionBlogService.baseUrl)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(InvisionBlogService::class.java)
        val blogPostRepository = InvisionBlogPostRepository(service)
        return BlogPostPresenterImpl(blogPostRepository)
    }
}
