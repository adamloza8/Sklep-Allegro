package pl.sklepallegro.sklepallegro

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_item.view.*

class CustomAdapter(val OfferList: List<Offer>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(OfferList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.row_item, parent, false))
    }


    override fun getItemCount(): Int {
        return OfferList.size
    }

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(offer: Offer, aView: View)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            mClickListener.onClick(OfferList[adapterPosition], itemView)
        }

        @SuppressLint("NewApi")
        fun bind(offer: Offer) {
            itemView.name.text = offer.name

            itemView.price.text = Html.fromHtml("cena: <b>" + offer.price?.amount + " " + offer.price?.currency+"</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            Glide.with(itemView.context).load(offer.thumbnailUrl).into(itemView.thumbnail)
        }

    }


}