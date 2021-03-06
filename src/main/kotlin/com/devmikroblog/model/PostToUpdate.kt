package com.devmikroblog.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by domin_000 on 21.05.2016.
 */
class PostToUpdate{
    public var id:Int = 0
        @JsonProperty(access = JsonProperty.Access.READ_WRITE)
        get
        set

    public var message:String  =""
        @JsonProperty(access = JsonProperty.Access.READ_WRITE)
        get
        set
    companion object{
        fun toPost(postToUpdate: PostToUpdate):Post{
            val post = Post()
            post.id = postToUpdate.id;
            post.message = postToUpdate.message
            return post
        }
    }
}