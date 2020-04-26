package com.mdaftabalam.covid19tracker.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mdaftabalam.covid19tracker.R
import com.mdaftabalam.covid19tracker.adapter.GlobalAdapter
import com.mdaftabalam.covid19tracker.model.GlobalModel
import kotlinx.android.synthetic.main.fragment_global.*
import org.json.JSONArray
import org.json.JSONException

class GlobalFragment : Fragment() {

    private val globalModel = ArrayList<GlobalModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_global, container, false)
        getGlobalList()
        return view
    }

    override fun onResume() {
        super.onResume()
        getGlobalList()
    }

    private fun getGlobalList() {
        val progressDialog = ProgressDialog.show(activity, "", "Please wait...")
        val allAPI = "https://corona.lmao.ninja/v2/countries"
        //Log.d("live_update", updateUrl)
        val stringRequest = StringRequest(Request.Method.GET, allAPI, Response.Listener { response ->
                Log.d("response", response)
                try {
                    progressDialog.dismiss()
                    /*Clear model data*/
                    globalModel.clear()
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val objectGlobal = jsonArray.getJSONObject(i)
                        val jsonObject = objectGlobal.getJSONObject("countryInfo")
                        val mosque = GlobalModel(
                            jsonObject.getString("flag"),
                            objectGlobal.getString("country"),
                            objectGlobal.getString("cases"),
                            objectGlobal.getString("deaths"),
                            objectGlobal.getString("active")
                        )
                        globalModel.add(mosque)
                        val linearLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false
                        )
                        recycler_global.layoutManager = linearLayoutManager
                        recycler_global.adapter = GlobalAdapter(globalModel)
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
