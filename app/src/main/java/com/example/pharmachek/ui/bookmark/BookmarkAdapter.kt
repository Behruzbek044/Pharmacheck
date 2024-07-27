package com.example.pharmachek.ui.bookmark

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pharmachek.database.ParmacheckEntity
import com.example.pharmachek.databinding.BookmarkRecyclerViewItemBinding

class BookmarkAdapter : ListAdapter<ParmacheckEntity, BookmarkAdapter.BookmarkViewHolder>(
    object : DiffUtil.ItemCallback<ParmacheckEntity>() {
        override fun areItemsTheSame(
            oldItem: ParmacheckEntity,
            newItem: ParmacheckEntity
        ): Boolean {
            return oldItem.barcode == newItem.barcode
        }

        override fun areContentsTheSame(
            oldItem: ParmacheckEntity,
            newItem: ParmacheckEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var onFavoriteIconClick: ((ParmacheckEntity, Int) -> Unit)? = null
    private var onItemClick: ((ParmacheckEntity) -> Unit)? = null

    inner class BookmarkViewHolder(private val binding: BookmarkRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ParmacheckEntity, position: Int) {
            with(binding) {
                setupViews(data)
                setupListeners(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = BookmarkRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.onBind(getItem(position), position)
    }

    @SuppressLint("SetTextI18n")
    private fun BookmarkRecyclerViewItemBinding.setupViews(data: ParmacheckEntity) {
        Glide
            .with(drugImage)
            .load(data.imageUrls.replace("'", ""))
            .into(drugImage)
        drugName.text = data.nameEn
        drugFarm.text = "${data.producer}/${data.countryOfOrigin}"
    }

    private fun BookmarkRecyclerViewItemBinding.setupListeners(
        data: ParmacheckEntity,
        position: Int
    ) {
        iconFavorite.setOnClickListener {
            onFavoriteIconClick?.invoke(data, position)
        }
        root.setOnClickListener {
            onItemClick?.invoke(data)
        }
    }

    fun setOnFavoriteIconClickListener(block: (ParmacheckEntity, Int) -> Unit) {
        onFavoriteIconClick = block
    }

    fun setOnItemClickListener(block: (ParmacheckEntity) -> Unit) {
        onItemClick = block
    }
}

