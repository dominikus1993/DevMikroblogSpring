package com.devmikroblog.repositories.interfaces

import com.devmikroblog.model.Post
import java.util.function.Predicate

/**
 * Created by dominik on 22.03.16.
 */
interface ICrudable<TEntity> {
    fun read():List<TEntity>?;
    fun read(predicate: Predicate<TEntity>):TEntity?;
    fun create(post: TEntity?):Boolean;
    fun update(post: TEntity?):Boolean;
    fun delete(post: TEntity?):Boolean;
}