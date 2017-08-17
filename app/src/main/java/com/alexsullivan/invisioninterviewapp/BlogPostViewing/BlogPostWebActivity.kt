package com.alexsullivan.invisioninterviewapp.BlogPostViewing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.alexsullivan.invisioninterviewapp.R

class BlogPostWebActivity : AppCompatActivity() {

    companion object {
        val URL_EXTRA = "urlExtra";

        fun buildIntent(url: String, context: Context): Intent {
            val intent = Intent(context, BlogPostWebActivity::class.java)
            intent.putExtra(URL_EXTRA, url)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_post_web)
        val url = intent.getStringExtra(URL_EXTRA)
        val webView = findViewById(R.id.webview) as WebView
        webView.loadUrl(url)
    }
}
