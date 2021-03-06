package com.devmikroblog.controllers

import com.devmikroblog.model.Result
import com.devmikroblog.model.User
import com.devmikroblog.services.interfaces.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import java.security.Principal

/**
 * Created by dominik on 08.04.16.
 */
open class BaseController{
    @Autowired
    protected lateinit var userService: IUserService;

    protected fun isOwner(postId:Int, userId : Int) : Result<Boolean> {
        return userService.isOwner(postId, userId)
    }

    protected fun getUser(principal: Principal) : Result<User?>{
        val user = userService.getUserBuUserName(principal.name)
        return user
    }
}