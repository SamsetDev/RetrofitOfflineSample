package com.samset.retrooffline.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.samset.retrooffline.R
import com.samset.retrooffline.ui.model.BasicResponse
import com.samset.retrooffline.network.ApiService
import kotlinx.android.synthetic.main.progress_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val id = intent.getStringArrayExtra("ID") as String
        getRepositoryDetails(id)

    }


    private fun getRepositoryDetails(id: String) {
        progress.setVisibility(View.VISIBLE)
        val call = apiService.reposForuser("SamsetDev")
        call.enqueue(object : Callback<List<BasicResponse>> {
            override fun onResponse(call: Call<List<BasicResponse>>, response: Response<List<BasicResponse>>) {
                val repos = response.body()
                Log.e("TAG", " response " + repos!!.size)

            }

            override fun onFailure(call: Call<List<BasicResponse>>, t: Throwable) {
                Toast.makeText(this@DetailsActivity, " Throw Exception " + t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }


}
