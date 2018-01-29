package br.com.git.adapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.git.R
import br.com.git.domain.entity.items
import kotlinx.android.synthetic.main.adapter_repository.view.*

class RepositoryAdapter(
        var list: MutableList<items>,
        val onClick: (items) -> Unit) :
        RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    override fun getItemCount(): Int {
        return this.list.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_repository, parent, false)
        val holder = RepositoryViewHolder(view)
        return holder
    }
    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = list[position]
        val view = holder.itemView
        with(view) {
            tv_fullname.text = item.full_name

            if (item.checklanguage()){

                tv_language.text = item.language

            }else{

                tv_language.visibility = View.GONE

            }
            setOnClickListener { onClick(item) }
        }
    }

    class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}
