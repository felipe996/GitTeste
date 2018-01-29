package br.com.git

import br.com.git.domain.entity.items
import br.com.git.domain.service.GitService
import br.com.git.extensions.convertDate
import br.com.git.extensions.sizePage
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun checkExistLanguage() {
        val item:items = items()
        item.language = "Java"
        assertTrue(item.checklanguage())
    }
    @Test
    fun checkNotExistLanguage() {
        val item:items = items()
        assertFalse(item.checklanguage())
    }

    @Test
    fun checkExisHomePage() {
        val item:items = items()
        item.homepage = "www.teste.com.br"
        assertTrue(item.checkHomepage())
    }
    @Test
    fun checkNotExisHomePage() {
        val item:items = items()
        assertFalse(item.checkHomepage())
    }

    @Test
    fun checkSizePage() {
        val item:items = items()
        val list:MutableList<items> = ArrayList<items>()
        for (i in 0..4){
            list.add(item)
        }
        assertTrue(sizePage(list) > 0)
    }
    @Test
    fun dateValidate() {
        val data = "2009-09-29T18:41:28Z"
        assertTrue(convertDate(data) != "")
    }
    @Test
    fun dateNotValidate() {
        val data = "2009-09-29T18:41"
        assertTrue(convertDate(data) == "")
    }
}
