package br.com.git.activity

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.git.R
import br.com.git.adapter.RepositoryAdapter
import br.com.git.domain.dao.DatabaseManager
import br.com.git.domain.dao.GitDAO
import br.com.git.domain.entity.items
import br.com.git.domain.service.GitService
import br.com.git.extensions.convertDate
import br.com.git.extensions.loadUrl
import br.com.git.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_detail.*

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.uiThread
import java.util.ArrayList


class DetailActivity : BaseActivity() {
    var daoGit: GitDAO? = null
    var adapter: RepositoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        setupToolbar(R.id.toolbar, "Detalhe", true)

        daoGit = DatabaseManager.getGitDAO()

        val item = intent.getSerializableExtra("item") as items

        img.loadUrl(item.owner!!.avatar_url, progress)


        tv_fullname.text = item.full_name!!

        tv_name.text = item.name

        tv_description.text = item.description

        tv_create.text = convertDate(item.created_at!!)

        tv_update.text = convertDate(item.updated_at!!)


        if (item.checklanguage()){

            tv_language.text = item.language

        }else{

            tv_language.visibility = View.GONE

            tv_description_languages.visibility = View.GONE


        }
        if (item.checkHomepage()){

            tv_homepage.text = item.homepage

        }else{

            tv_homepage.visibility = View.GONE

            tv_description_homepage.visibility = View.GONE


        }


        val dialog = indeterminateProgressDialog("Buscando Repositórios", "Aguarde")

        dialog.setCancelable(false)


        doAsync {

            val value = daoGit!!.findQtdRepository(item.owner!!.login)
            if ( value> 1){

                val repository = daoGit!!.findRepository(item.owner!!.login)

                uiThread {


                    chargeRecycler(repository)

                    dialog.dismiss()
                }
            }else{

                val repository = GitService.getRepository(item.owner!!.repos_url)

                repository.removeAt(0)

                daoGit!!.insert(checkRepository(repository))

                uiThread {

                    chargeRecycler(repository)

                    dialog.dismiss()

                }
            }

        }




    }

    private fun chargeRecycler(items:MutableList<items>) {

        adapter = RepositoryAdapter(items) {  }

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = adapter
    }

    private fun checkRepository(list:MutableList<items>):MutableList<items>{
        val temp = ArrayList<items>()
        for (item in list){
            val value = daoGit!!.findItem(item.id)
            if (value == null){
                item.repository = "S"
                temp.add(item)
            }
        }
        return temp

    }

}
