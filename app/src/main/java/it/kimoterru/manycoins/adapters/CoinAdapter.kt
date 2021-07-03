package it.kimoterru.manycoins.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import it.kimoterru.manycoins.R
import it.kimoterru.manycoins.network.models.Coin
import java.math.RoundingMode

class CoinAdapter(
    private val data: List<Coin>
) : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon_item)
        val name: TextView = itemView.findViewById(R.id.name_item)
        val symbol: TextView = itemView.findViewById(R.id.symbol_item)
        val price: TextView = itemView.findViewById(R.id.price_item)
        val priceChangeCneH: TextView = itemView.findViewById(R.id.price_one_h_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.name
        holder.price.text =
            item.price.toBigDecimal().setScale(3, RoundingMode.UP).toDouble().toString()
        holder.symbol.text = item.symbol
        holder.priceChangeCneH.text = item.priceChange1H.toString()
        Glide.with(holder.icon).load(item.icon).into(holder.icon)
    }

    override fun getItemCount() = data.size

}