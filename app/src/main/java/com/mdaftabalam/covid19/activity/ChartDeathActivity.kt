package com.mdaftabalam.covid19.activity

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mdaftabalam.covid19.R
import org.json.JSONException
import org.json.JSONObject

/**
 *Created by Aftab Alam on 30-04-2020.
 **/
class ChartDeathActivity : AppCompatActivity() {

    lateinit var x: ArrayList<Entry>
    lateinit var y: ArrayList<String>
    private lateinit var mChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_death)
        x = ArrayList()
        y = ArrayList()
        mChart = findViewById(R.id.graph_death)
        mChart.setDrawGridBackground(false)
        mChart.setDescription("Deaths")
        mChart.setTouchEnabled(true)
        mChart.isDragEnabled = true
        mChart.setScaleEnabled(true)
        mChart.setPinchZoom(true)
        mChart.xAxis.textSize = 14f
        mChart.axisLeft.textSize = 14f
        mChart.animateY(1000)
        val xl = mChart.xAxis
        xl.setAvoidFirstLastClipping(true)
        xl.position = XAxis.XAxisPosition.BOTTOM
        /*val leftAxis = mChart.axisLeft
        leftAxis.isInverted = true*/
        val rightAxis = mChart.axisRight
        rightAxis.isEnabled = false
        val l: Legend = mChart.getLegend()
        l.form = Legend.LegendForm.LINE
        getDeathList()
    }

    /*override fun onResume() {
        super.onResume()
        getDeathList()
    }*/

    private fun getDeathList() {
        val progressDialog = ProgressDialog.show(this, "", "Please wait...")
        val allAPI = "https://api.covid19india.org/data.json"
        //Log.d("live_update", updateUrl)
        val stringRequest = StringRequest(
            Request.Method.GET, allAPI, Response.Listener { response ->
                //Log.d("response", response)
                try {
                    progressDialog.dismiss()
                    val jsonObject = JSONObject(response)
                    val jsonArray = jsonObject.getJSONArray("cases_time_series")
                    for (i in 0 until jsonArray.length()) {
                        val objectCases = jsonArray.getJSONObject(i)
                        val case = objectCases.getString("dailydeceased")
                        x.add(Entry(case.toInt().toFloat(), i))
                        y.add(objectCases.getString("date"))
                    }
                    val set1 = LineDataSet(x, "Daily Deceased")
                    set1.isHighlightEnabled = true
                    set1.color = Color.RED
                    set1.lineWidth = 1f
                    set1.circleRadius = 3f
                    set1.setCircleColor(Color.YELLOW)
                    //set1.setDrawHighlightIndicators(true)
                    //set1.highLightColor = Color.RED
                    set1.valueTextSize = 10.5f
                    set1.valueTextColor = Color.DKGRAY
                    val data = LineData(y, set1)
                    mChart.data = data
                    mChart.invalidate()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { _ ->
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Check internet connection", Toast.LENGTH_SHORT).show()
            })
        val requestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(stringRequest)
    }
}