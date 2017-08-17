package com.alexsullivan.invisioninterviewapp.BlogData

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Simple POJO representing an invision blog post.
 */
@Root(name = "item")
class InvisionBlogPost(@Element(name = "link") var link: String?,
                       @Element(name = "title") var title: String?) {
    constructor(): this(null, null) {

    }
}