package com.example.doaharian

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doaharian.adapter.DoaAdapter
import com.example.doaharian.databinding.ActivityListDoaBinding
import com.example.doaharian.dataclass.DataDoa
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class ListDoaActivity : AppCompatActivity() {

    companion object{
        private val TAG = ListDoaActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityListDoaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDoaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvListDoa.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvListDoa.addItemDecoration(itemDecoration)


        grtListDoa()
    }


    private fun grtListDoa() {
        binding.progressBar.visibility = View.VISIBLE

        val client = AsyncHttpClient()
        val url = "https://doa-doa-api-ahmadramadhan.fly.dev/api"

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                binding.progressBar.visibility = View.INVISIBLE

                val listDoa = ArrayList<DataDoa>()
                val result = String(responseBody)

                Log.d(TAG, result)

                try {
                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val doa = jsonObject.getString("doa")
                        val ayat = jsonObject.getString("ayat")
                        val latin = jsonObject.getString("latin")
                        val arti = jsonObject.getString("artinya")
                        val doaItem = DataDoa(doa, ayat, latin, arti)
                        listDoa.add(doaItem)
                    }

                    val adapter = DoaAdapter(listDoa)
                    binding.rvListDoa.adapter = adapter

                    adapter.setOnItemClickCallback(object : DoaAdapter.OnItemClickCallback{
                        override fun onItemClicked(data: String) {
                            Toast.makeText(this@ListDoaActivity, data, Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@ListDoaActivity, DetailActivity::class.java)
                            startActivity(intent)
                        }
                    })
                }catch (e: Exception){
                    Toast.makeText(this@ListDoaActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                binding.progressBar.visibility = View.INVISIBLE

                val errorMessage = when(statusCode){
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@ListDoaActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}