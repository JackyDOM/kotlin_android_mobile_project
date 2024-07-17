package com.example.myfirstapp.Home_Screen

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.myfirstapp.Adapters.CategoryBook1Adapter
import com.example.myfirstapp.Adapters.CategoryBook2Adapter
import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.Services.ApiServiceBanners
import com.example.myfirstapp.Services.ApiServiceCategory1
import com.example.myfirstapp.Services.ApiServiceCategory2
import com.example.myfirstapp.databinding.FragmentHomeBinding
import edu.rupp.firstite.adapter.BannerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    private lateinit var accessToken: String
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var categoryBook1Adapter: CategoryBook1Adapter
    private lateinit var categoryBook2Adapter: CategoryBook2Adapter
    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null
    private var currentIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val retrievedAccessToken: String? = sharedPreferences.getString("access_token", null)

        if (retrievedAccessToken != null && !retrievedAccessToken.isEmpty()) {
            // Log the retrieved access token
            Log.d("AccessToken", "Retrieved Access Token: " + retrievedAccessToken);
            // Store the retrieved access token for later use
            accessToken = retrievedAccessToken
        }else{
            Toast.makeText(requireContext(), "Access token not available or empty", Toast.LENGTH_LONG).show();
        }

        //Display recycleview banner in horizontal
        binding.recycleViewBanner.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            bannerAdapter = BannerAdapter()
            adapter = bannerAdapter

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }

        //Display recycleview categoryBook1 in horizontal
        binding.recycleViewCategory1.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            categoryBook1Adapter = CategoryBook1Adapter()
            adapter = categoryBook1Adapter
        }

        //Display recycleview categoryBook2 in horizontal
        binding.recycleViewCategory2.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            categoryBook2Adapter = CategoryBook2Adapter()
            adapter = categoryBook2Adapter
        }

        // Store the retrieved access token for later use
        accessToken = retrievedAccessToken.toString()

        //Call all function to display
        if (!accessToken.isNullOrEmpty()) {
            loadBannerImage()
            loadCategoryBook1()
            loadCategoryBook2()
        } else {
            Toast.makeText(requireContext(), "Access token not available or empty", Toast.LENGTH_LONG).show();
        }

        return binding.getRoot();
    }

    private fun loadBannerImage(){
        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiServiceBanner = httpClient.create(ApiServiceBanners::class.java)
        val task = apiServiceBanner.loadBannerImage("Bearer $accessToken")
        task.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    bannerAdapter.submitList(response.body())
                    startAutoScroll()
                } else {
                    Toast.makeText(requireContext(), "Failed reload banner", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message ?: "Unknown error", Toast.LENGTH_LONG).show()
            }
        })
    } // Function fetching Banners

    private fun startAutoScroll() {
        runnable = Runnable {
            if (currentIndex == bannerAdapter.itemCount) {
                currentIndex = 0
            }
            binding.recycleViewBanner.smoothScrollToPosition(currentIndex++)
            handler.postDelayed(runnable!!, 3000) // Change delay as needed
        }
        handler.postDelayed(runnable!!, 3000) // Initial delay
    } // Function for auto scrolling

    override fun onDestroyView() {
        super.onDestroyView()
        runnable?.let { handler.removeCallbacks(it) }
    }

    //Display Category General Books
    private fun loadCategoryBook1(){
        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiServiceCategory = httpClient.create(ApiServiceCategory1::class.java)
        val task = apiServiceCategory.loadCategoryBook1("Bearer $accessToken")
        task.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    val categoryBanner1List = response.body() ?: emptyList()
                    val filteredList = categoryBanner1List.filter { it.category.id == 1 }
                    categoryBook1Adapter.submitList(filteredList)
                } else {
                    Toast.makeText(requireContext(), "Failed reload banner", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message ?: "Unknown error", Toast.LENGTH_LONG).show()
            }
        })
    } // Function fetching CategoryBook1

    private fun loadCategoryBook2(){
        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiServiceCategory2 = httpClient.create(ApiServiceCategory2::class.java)
        val task = apiServiceCategory2.loadCategoryBook2("Bearer $accessToken")
        task.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    val categoryBanner1List = response.body() ?: emptyList()
                    val filteredList = categoryBanner1List.filter { it.category.id == 2 }
                    categoryBook2Adapter.submitList(filteredList)
                } else {
                    Toast.makeText(requireContext(), "Failed reload banner", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message ?: "Unknown error", Toast.LENGTH_LONG).show()
            }
        })
    } // Function fetching CategoryBook2
}