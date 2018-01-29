package br.com.git.adapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.git.R
import br.com.git.domain.entity.items
import kotlinx.android.synthetic.main.adapter_gits.view.*

class GitAdapter(
        var list: MutableList<items>,
        val onClick: (items) -> Unit) :
        RecyclerView.Adapter<GitAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return this.list.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_gits, parent, false)
        val holder = ViewHolder(view)
        return holder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val view = holder.itemView
        with(view) {
            tv_fullname.text = item.full_name
            tv_gitid.text = item.name
            setOnClickListener { onClick(item) }
        }
    }
    fun addItem(items: items,position: Int){
        list.add(items)
        notifyItemInserted(position)
    }

    fun updateList(items: MutableList<items>) {
        this.list = items
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}
