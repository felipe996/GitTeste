package br.com.git.domain.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import br.com.git.domain.entity.items

/**
 * Created by Felipe on 26/01/2018.
 */
@Dao
interface GitDAO {
    @Insert
    fun insert(items:List<items>)

    @Query("SELECT COUNT(*) FROM items")
    fun findQtd(): Int

    @Query("SELECT COUNT(*) FROM items where login = :arg0 ")
    fun findQtdRepository(login:String): Int



    @Query("SELECT * FROM items where repository != 'S'")
    fun findAll(): MutableList<items>

    @Query("SELECT * FROM items where id = :arg0")
    fun findItem(id:Int):items

    @Query("SELECT * FROM items where login = :arg0 and repository = 'S'")
    fun findRepository(login:String): MutableList<items>

    @Update
    fun update(item:items)
}