package pl.sklepallegro.sklepallegro

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val offer:Offer? = intent.getParcelableExtra("offer")
        Glide.with(this).load(offer?.thumbnailUrl).into(image)
        details.text = Html.fromHtml("cena: <b>"+ offer?.price?.amount + " " + offer?.price?.currency +"</b>" + offer?.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = offer?.name

    }


}
