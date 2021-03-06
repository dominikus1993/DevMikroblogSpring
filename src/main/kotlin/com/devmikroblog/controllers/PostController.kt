package com.devmikroblog.controllers

import com.devmikroblog.model.*
import com.devmikroblog.services.interfaces.IPostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.function.Predicate

/**
 * Created by dominik on 22.03.16.
 */

@RestController
@RequestMapping("/api/post")
class PostController : BaseController{
    private val service:IPostService


    @Autowired
    constructor(service: IPostService) : super(){
        this.service = service
    }

    @RequestMapping("getAll")
    fun getAll(): Result<List<Post>?> {
        return service.getAll();
    }

    @RequestMapping("get/{id}")
    fun getById(@PathVariable id:Int):Result<Post?>{
        return service.getBy(Predicate { x -> x.id == id });
    }

    @RequestMapping(value = "create", method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody postToCreation:PostToCreation, principal: Principal):Result<Post?>{
        val post = PostToCreation.toPost(postToCreation)
        post.author = getUser(principal).value as User

        val createResult = service.create(post);

        if(createResult.isSuccess && createResult.value){
            return service.getById(post.id)
        }
        else{
            return Result.ErrorWhenNoData(null)
        }
    }

    @RequestMapping(value = "delete/{postId}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable postId:Int, principal: Principal): Result<Boolean>{
        val user = getUser(principal)
        if (user.isSuccess){
            return service.delete(postId, user.value as User)
        }
        return Result(false)
    }

    @RequestMapping(value = "update", method = arrayOf(RequestMethod.PUT))
    fun update(@RequestBody postToUpdate: PostToUpdate, principal: Principal): Result<Boolean>{
        val user = getUser(principal)
        if (user.isSuccess){
            val post = PostToUpdate.toPost(postToUpdate)
            return service.update(post, user.value as User)
        }
        return Result(false)
    }
}