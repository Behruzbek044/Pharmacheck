package com.example.pharmachek.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pharmachek.R
import com.example.pharmachek.databinding.RecyclerViewDrugsItemBinding
import com.example.pharmachek.model.response.search_drug.SearchDrugResponseData

class HomeDrugsAdapter : RecyclerView.Adapter<HomeDrugsAdapter.HomeDrugsViewHolder>() {

    private var drugs: List<SearchDrugResponseData> = emptyList()
    private var onFavoriteButtonClick: ((SearchDrugResponseData, Int) -> Unit)? = null
    private var onItemClick: ((SearchDrugResponseData, Int) -> Unit)? = null

    inner class HomeDrugsViewHolder(private val binding: RecyclerViewDrugsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: SearchDrugResponseData, position: Int) {
            with(binding) {
                setupViews(data, position)
                setupListeners(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDrugsViewHolder {
        val view =
            RecyclerViewDrugsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeDrugsViewHolder(view)
    }

    override fun getItemCount(): Int = drugs.size

    override fun onBindViewHolder(holder: HomeDrugsViewHolder, position: Int) =
        holder.onBind(drugs[position], position)

    private fun RecyclerViewDrugsItemBinding.setupListeners(
        data: SearchDrugResponseData,
        position: Int
    ) {
        iconFavorite.setOnClickListener {
            onFavoriteButtonClick?.invoke(data, position)
        }
        root.setOnClickListener {
            onItemClick?.invoke(data, position)
        }
    }

    private fun RecyclerViewDrugsItemBinding.setupViews(
        data: SearchDrugResponseData,
        position: Int
    ) {
        Glide
            .with(drugImage.context)
            .load(data.medicamentInfo.imageUrls.first().replace("'", ""))
            .fitCenter()
            .into(drugImage)
        drugName.text = data.medicamentInfo.nameEn
        drugName.isSelected = true
        drugFarm.text = drugFarm.context.getString(
            R.string.drug_farm,
            data.medicamentInfo.producer,
            data.medicamentInfo.countryOfOrigin
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(drugs: List<SearchDrugResponseData>) {
        this.drugs = drugs
        notifyDataSetChanged()
    }

    fun setOnFavoriteButtonClickListener(block: (SearchDrugResponseData, Int) -> Unit) {
        onFavoriteButtonClick = block
    }

    fun setOnItemClickListener(block: (SearchDrugResponseData, Int) -> Unit) {
        onItemClick = block
    }
}