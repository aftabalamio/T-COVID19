package com.mdaftabalam.covid19tracker.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mdaftabalam.covid19tracker.R
import kotlinx.android.synthetic.main.fragment_analysis.*
import org.json.JSONException
import org.json.JSONObject

class AnalysisFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_analysis, container, false)
        analysisData()
        return view
    }

    private fun analysisData() {
        val progressDialog = ProgressDialog.show(activity, "", "Please wait...")
        val api = "https://corona.lmao.ninja/v2/all"
        //Log.d("login_api", loginUrl)
        val stringRequest = StringRequest(Request.Method.GET, api, Response.Listener { response ->
            //Log.d("response", response)
            try {
                progressDialog.dismiss()
                val jsonObject = JSONObject(response)
                txtTime.text = jsonObject.getString("updated")
                txtConfirmed.text = jsonObject.getString("cases")
                txtDeaths.text = jsonObject.getString("deaths")
                txtRecovered.text = jsonObject.getString("recovered")
                txtActive.text = jsonObject.getString("active")
                txtTC.text = jsonObject.getString("todayCases")
                txtTD.text = jsonObject.getString("todayDeaths")
                txtTS.text = jsonObject.getString("critical")
                mC.text = jsonObject.getString("casesPerOneMillion")
                mD.text = jsonObject.getString("deathsPerOneMillion")
                mT.text = jsonObject.getString("testsPerOneMillion")
                tT.text = jsonObject.getString("tests")
                txtCountries.text = jsonObject.getString("affectedCountries")
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
