package com.samset.retrooffline.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.samset.retrooffline.R
import com.samset.retrooffline.ui.model.BasicResponse
import com.samset.retrooffline.network.ApiService
import com.samset.retrooffline.network.RetrofitManager
import com.samset.retrooffline.ui.adapter.MyAdapter
import com.samset.retrooffline.ui.listeners.OnItemClickListeners
import com.samset.retrooffline.utils.Utils.USERNAME
import com.samset.retrooffline.utils.custom.ResponseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.progress_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubListRxJavaActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var myAdapter: MyAdapter
    private lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val manager = RetrofitManager(this)
        apiService = manager.apiService


        rxrecyclerview.layoutManager = LinearLayoutManager(this)
        compositeDisposable = CompositeDisposable()
        getRepositoryDetails()

    }


    private fun getRepositoryDetails() {
        progress.setVisibility(View.VISIBLE)

        apiService.getRepository(USERNAME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ResponseObserver<Response<List<BasicResponse>>>(compositeDisposable) {
                    override fun onNetworkError(e: Throwable) {
                        progress.setVisibility(View.GONE)

                    }

                    override fun onServerError(e: Throwable, code: Int) {
                        progress.setVisibility(View.GONE)
                    }

                    override fun onNext(response: Response<List<BasicResponse>>) {
                        progress.setVisibility(View.GONE)
                        response.body()?.let { setupAdapter(it) }

                    }
                })
    }

    private fun setupAdapter(data: List<BasicResponse>) {
        myAdapter = MyAdapter(this, data)
        rxrecyclerview.setAdapter(myAdapter)
        myAdapter.notifyDataSetChanged()
        myAdapter.setListeners { view, position -> Log.e("TAG", " selected id " + data[position].id) }


    }


    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}
