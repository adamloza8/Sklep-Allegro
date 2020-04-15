package pl.sklepallegro.sklepallegro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private var offerList: MutableList<Offer> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readFromAsset()

        val adapter = CustomAdapter(offerList)

        rcv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rcv.adapter = adapter

        adapter.setOnItemClickListener(object : CustomAdapter.ClickListener {
            override fun onClick(offer: Offer, aView: View) {

                val intent = Intent(this@MainActivity, DetailsActivity::class.java).apply {
                    putExtra("offer", offer)
                }
                startActivity(intent)
            }
        })
    }
    private fun setItems(items: List<Offer>){
        this.offerList.clear()
        this.offerList.addAll(items.filter { it.price?.amount!!.toDouble() in 50.0..1000.0 }.sortedBy { it.price?.amount!!.toDouble() })

        refreshList()
    }
    private fun refreshList(){
        runOnUiThread {
            rcv.adapter!!.notifyDataSetChanged()
        }
    }
    private fun readFromAsset()  {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-987cdf-allegromobileinterntest.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        apiService.listOffers().enqueue {

            onResponse = {
                if(it.body() != null) {
                    setItems(it.body()!!.offers)
                }
            }
            onFailure = {

            }
        }
    }
}

