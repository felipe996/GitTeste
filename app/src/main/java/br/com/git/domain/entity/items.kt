package br.com.git.domain.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

import java.io.Serializable

/**
 * Created by Felipe on 25/01/2018.
 */

@Entity
class items() :Serializable {
    @PrimaryKey(autoGenerate = true)
    var id_interno:Int = 0
    var id: Int =0
    var name: String? = ""
    var full_name: String? = ""
    var description:String ?= ""
    var homepage:String? = ""
    var created_at:String? = ""
    var updated_at:String? = ""
    @Embedded
    var owner:owner? = owner()
    var language:String? = ""
    var repository:String? = ""
    fun checklanguage():Boolean{
        return !language.isNullOrEmpty()
    }
    fun checkHomepage():Boolean{
        return !homepage.isNullOrEmpty()
    }


}
