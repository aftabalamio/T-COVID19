package com.mdaftabalam.covid19tracker.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mdaftabalam.covid19tracker.R
import com.mdaftabalam.covid19tracker.adapter.IndiaAdapter
import com.mdaftabalam.covid19tracker.model.IndiaModel
import kotlinx.android.synthetic.main.fragment_global.*
import kotlinx.android.synthetic.main.fragment_india.*
import org.json.JSONException
import org.json.JSONObject

class IndiaFragment : Fragment() {

    private val indiaModel = ArrayList<IndiaModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_india, container, false)
        getIndiaList()
        return view
    }

    override fun onResume() {
        super.onResume()
        getIndiaList()
    }

    private fun getIndiaList() {
        val progressDialog = ProgressDialog.show(activity, "", "Please wait...")
        val allAPI = "https://api.covid19india.org/data.json"
        //Log.d("live_update", updateUrl)
        val stringRequest = StringRequest(
            Request.Method.GET, allAPI, Response.Listener { response ->
            Log.d("response", response)
            try {
                progressDialog.dismiss()
                /*Clear model data*/
                indiaModel.clear()
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.getJSONArray("statewise")
                for (i in 0 until jsonArray.length()) {
                    val objectIndia = jsonArray.getJSONObject(i)
                    val mosque = IndiaModel(
                        objectIndia.getString("statecode"),
                        objectIndia.getString("state"),
                        objectIndia.getString("lastupdatedtime"),
                        objectIndia.getString("confirmed"),
                        objectIndia.getString("recovered"),
                        objectIndia.getString("deaths"),
                        objectIndia.getString("active")
                    )
                    indiaModel.add(mosque)
                    val linearLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    recycler_india.layoutManager = linearLayoutManager
                    recycler_india.adapter = IndiaAdapter(indiaModel)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                progressDialog.dismiss()
                Toast.makeText(activity, "500", Toast.LENGTH_SHORT).show()
            }
        },
            Response.ErrorListener { _ ->
                progressDialog.dismiss()
                Toast.makeText(activity, "400", Toast.LENGTH_SHORT).show()
            })
        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(stringRequest)
    }
}
