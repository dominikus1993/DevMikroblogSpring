package com.devmikroblog.services.interfaces

import com.devmikroblog.model.Post
import com.devmikroblog.model.Result
import com.devmikroblog.model.User
import org.hibernate.sql.Update
import org.springframework.stereotype.Service
import java.util.function.Predicate

/**
 * Created by dominik on 22.03.16.
 */
interface IPostService {
    fun getAll(): Result<List<Post>?>
    fun getBy(predicate: Predicate<Post>):Result<Post?>
    fun getPostsBy(predicate: Predicate<Post>): Result<List<Post>?>
    fun getById(id:Int):Result<Post?>
    fun create(post: Post):Result<Boolean>
    fun update(post: Post, user: User):Result<Boolean>
    fun delete(postId: Int, user: User): Result<Boolean>
}