package com.app.aman.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.aman.R
import com.app.aman.network.model.StockDetail
import kotlinx.android.synthetic.main.item_stock.view.*

class StockListAdapter(private val stocks: List<StockDetail>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_stock, null)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(stocks[position])
    }

    override fun getItemCount(): Int {
        return stocks.size
    }

    inner class ItemViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(stock: StockDetail) {
            item.txtStock.text = "$stock"
        }
    }
}