package me.vaimon.spacex.ui.launches_list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.vaimon.spacex.R
import me.vaimon.spacex.databinding.ItemLaunchBinding
import me.vaimon.spacex.ui.models.Launch

class LaunchRecyclerViewAdapter(
    private val onItemClickListener: OnItemClickListener? = null
) : RecyclerView.Adapter<LaunchRecyclerViewAdapter.ViewHolder>() {

    private val launches: MutableList<Launch> = mutableListOf()

    private val onClickListener = View.OnClickListener {
        val item = it.tag as Launch
        onItemClickListener?.onLaunchClick(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLaunchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = launches[position]

        with(holder.binding) {
            tvLaunchName.text = item.name
            ivBadge.load(item.patchUrl) {
                crossfade(true)
                placeholder(R.drawable.logo)
            }
        }

        onClickListener.also {
            with(holder.itemView) {
                tag = item
                setOnClickListener(it)
            }
        }
    }

    override fun getItemCount(): Int = launches.size

    fun replaceWithNewLaunches(newLaunchList: List<Launch>) {
        val listDiff = DiffUtil.calculateDiff(LaunchDiffUtil(launches, newLaunchList))
        launches.clear()
        launches.addAll(newLaunchList)
        listDiff.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onLaunchClick(item: Launch)
    }

    class LaunchDiffUtil(
        private val oldList: List<Launch>,
        private val newList: List<Launch>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }
    }
}