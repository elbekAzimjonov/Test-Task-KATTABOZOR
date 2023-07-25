package com.elbek.kattabozor.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.elbek.domain.models.Offer
import com.elbek.domain.models.Product
import com.elbek.kattabozor.databinding.ListItemBinding

class ProductAdapter(
    private val mProduct: Product
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val item: ListItemBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun onBind(product: Offer, position: Int) {
            item.bandName.text = product.brand

            Glide.with(itemView)
                .load(mProduct.offers[position].image.url)
                .transform(CenterCrop())
                .into(item.productImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = mProduct.offers.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(mProduct.offers[position], position)

    }
}