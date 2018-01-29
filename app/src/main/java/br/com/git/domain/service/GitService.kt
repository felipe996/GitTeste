package br.com.git.domain.service

import br.com.git.domain.dao.GitDAO
import br.com.git.domain.entity.git
import br.com.git.domain.entity.items
import br.com.git.manager.HttpHelper
import br.com.livroandroid.carros.extensions.fromJson


/**
 * Created by Felipe on 26/01/2018.
 */


object GitService{
    private val BASE_URL = " https://api.github.com/search/repositories?q=android&"

    fun getGit(page:Int): git {
        val json = HttpHelper.get("${BASE_URL}per_page=5&page=$page")
        val git = fromJson<git>(json)
        return git
    }
    fun getRepository(url:String): MutableList<items> {
        val json = HttpHelper.get("$url?access_token=d1468846f3b0089ca4539e8b0d397aca632933ad")
        val git = fromJson<MutableList<items>>(json)
        return git
    }


}