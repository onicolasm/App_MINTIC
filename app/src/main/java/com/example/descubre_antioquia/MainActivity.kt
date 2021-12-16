package com.example.descubre_antioquia

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://my-json-server.typicode.com/nicolasmozo/App_MINTIC/"

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<MyDataItem>
    //private lateinit var imagesArray: ArrayList<Sites>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var detail: Array<String>
    lateinit var desc: Array<String>
    lateinit var myRecyclerAdapter: RecyclerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        getMyData()


        imageId = arrayOf(
            R.drawable.piedra_1,
            R.drawable.santafe_2,
            R.drawable.jardin_3,
            R.drawable.estadio_4,
            R.drawable.jerico_5
        )
        heading = arrayOf(
            "Piedra del Peñol",
            "Santa Fe de Antioquia",
            "El Jardín",
            "Estadio Atanasio Girardot",
            "Jericó"
        )
        detail = arrayOf(
            "El peñón de Guatapé, o piedra del Peñol, es un monolito de 220 metros de altura, localizado en El Peñol-Guatapé",
            "Puente de Occidente. Kanaloa Parque Acuatico. Plaza Mayor Juan de Corral. Iglesia de Nuestra Señora de Chiquinquirá",
            "Pueblo más Bonito de Antioquia, Ciudad de los muchos cerros y Monumento Nacional",
            "Campo deportivo del Área Metropolitana del Valle de Aburrá",
            "La Atenas del suroeste antioqueño"
        )

        desc = arrayOf(
            getString(R.string.sitio_1),
            getString(R.string.sitio_2),
            getString(R.string.sitio_3),
            getString(R.string.sitio_4),
            getString(R.string.sitio_5)
        )



        //newRecyclerView = findViewById(R.id.recyclerView)
        //newRecyclerView.layoutManager = LinearLayoutManager(this)
        //newRecyclerView.setHasFixedSize(true)

        //newArrayList = arrayListOf<MyDataItem>()
        //getUserdata()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                myRecyclerAdapter = RecyclerAdapter(baseContext,
                    responseBody as ArrayList<MyDataItem>
                )
                myRecyclerAdapter.notifyDataSetChanged()
                recyclerView.adapter = myRecyclerAdapter

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("MainActivity","onFailure:"+t.message)
            }
        })

    }

/*
    private fun getUserdata() {
        for (i in imageId.indices) {
            val sites = MyDataItem(imageId[i])
            newArrayList.add(sites)
        }

        var adapter = RecyclerAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : RecyclerAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@MainActivity, "You clicked on item no. $position",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity, SitesActivity::class.java)
                //intent.putExtra("heading", newArrayList[position].heading)
                //intent.putExtra("imageId", newArrayList[position].titleImage)
                intent.putExtra("desc", desc[position])

                startActivity(intent)


            }
        })

        val buttonClick = findViewById<Button>(R.id.button_ajustes)
        buttonClick.setOnClickListener {
            val intent = Intent(this, ajustesActivity::class.java)
            startActivity(intent)
        }
    }
*/
}