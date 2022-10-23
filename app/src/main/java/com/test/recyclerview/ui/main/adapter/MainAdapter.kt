package com.test.recyclerview.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.recyclerview.data.models.Content
import com.test.recyclerview.databinding.ItemLayoutBinding

class MainAdapter(
    private val contents: ArrayList<Content>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): DataViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)

                return DataViewHolder(binding)
            }
        }

        fun bind(content: Content) {
            binding.mainItemTitle.text = content.video.channelName
            binding.mainItemImageThumb.load(content.video.thumbnails[0].url)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder.from(parent)

    override fun getItemCount(): Int = contents.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val video = contents[position]
        holder.bind(video)

    }

    fun addData(list: List<Content>) {
        contents.addAll(list)
    }
}