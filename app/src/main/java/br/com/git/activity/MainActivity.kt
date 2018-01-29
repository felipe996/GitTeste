package br.com.git.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import br.com.git.R
import br.com.git.adapter.GitAdapter
import br.com.git.domain.service.GitService
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import br.com.git.domain.dao.GitDAO
import br.com.git.domain.entity.items
import br.com.git.domain.dao.DatabaseManager
import br.com.git.extensions.setupToolbar
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity
import android.support.v7.widget.RecyclerView
import br.com.git.extensions.sizePage


class MainActivity : BaseActivity() {

    var page = 1
    var items: MutableList<items> = ArrayList<items>()
    var daoGit: GitDAO? = null
    var adapter: GitAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupToolbar(R.id.toolbar, "Github", false)

        daoGit = DatabaseManager.getGitDAO()

        val dialog = indeterminateProgressDialog("Buscando items", "Aguarde")

        dialog.setCancelable(false)

        doAsync {

            val value = daoGit!!.findQtd()

            if (value == 0) {

                val git = GitService.getGit(page)

                daoGit!!.insert(checkRepository(git.items))

                page++

                items.addAll(git.items)

                uiThread {

                    dialog.dismiss()

                    Log.v("local", "json")

                    chargeRecycler()

                }
            } else {

                Log.v("local", "sqlite")

                items = daoGit!!.findAll()



                uiThread {

                    page = sizePage(items)

                    dialog.dismiss()

                    chargeRecycler()


                }
            }

        }
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                filter(p0.toString())

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)

                scrolRecycler()

            }
        })

    }

    private fun scrolRecycler() {
        val llm = recyclerView.layoutManager as LinearLayoutManager
        val adapter = recyclerView.adapter as GitAdapter

        if (items.size == llm.findLastCompletelyVisibleItemPosition() + 1) {


            if (page < 200) {
                doAsync {
                    val git = GitService.getGit(page)

                    daoGit!!.insert(checkRepository(git.items))

                    page++

                    uiThread {

                        items.addAll(git.items)

                        for (item in git.items) {
                            adapter.addItem(item, items.size)
                        }

                    }
                }
            }
        }
    }

    private fun chargeRecycler() {

        adapter = GitAdapter(items) { onClick(it) }

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = adapter
    }

    fun filter(text: String) {

        val temp = ArrayList<items>()

        for (item in items) {

            if (item.full_name!!.contains(text)) {

                temp.add(item)

            }
        }

        if (adapter != null){

            adapter!!.updateList(temp)

        }

    }

     fun onClick(item: items) {
        startActivity<DetailActivity>("item" to item)
    }

    private fun checkRepository(list:MutableList<items>):MutableList<items>{
        val temp = java.util.ArrayList<items>()
        for (item in list){
            val value = daoGit!!.findItem(item.id)
            if (value == null){
                temp.add(item)
            }
        }
        return temp

    }
}
