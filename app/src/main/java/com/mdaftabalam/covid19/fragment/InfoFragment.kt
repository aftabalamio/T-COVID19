package com.mdaftabalam.covid19.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mdaftabalam.covid19.R


class InfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)
        val txtPredict = view.findViewById<TextView>(R.id.txt_prediction)
        txtPredict.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://ddi.sutd.edu.sg/"))
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.setPackage("com.android.chrome")
            try {
                context!!.startActivity(i)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "unable to open chrome", Toast.LENGTH_SHORT).show()
                i.setPackage(null)
                context!!.startActivity(i)
            }
        }
        return view
    }
}
