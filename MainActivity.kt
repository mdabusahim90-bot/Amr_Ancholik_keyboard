package com.amarancholik.keyboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import android.view.Gravity

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 36, 28, 28)
            setBackgroundColor(Color.rgb(247, 248, 250))
        }
        val title = TextView(this).apply {
            text = "আমার আঞ্চলিক"
            textSize = 28f
            setTextColor(Color.rgb(25, 55, 45))
            gravity = Gravity.CENTER
        }
        val intro = TextView(this).apply {
            text = "বাংলা ও ইংরেজি টাইপিংয়ের প্রথম সংস্করণ"
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(0, 14, 0, 24)
        }
        val enable = Button(this).apply {
            text = "১. কিবোর্ড চালু করুন"
            setOnClickListener { startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)) }
        }
        val choose = Button(this).apply {
            text = "২. আমার আঞ্চলিক কিবোর্ড নির্বাচন করুন"
            setOnClickListener {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showInputMethodPicker()
            }
        }
        val note = TextView(this).apply {
            text = "ব্যবহার: প্রথমে কিবোর্ড চালু করুন। এরপর যেকোনো লেখার জায়গায় গিয়ে কিবোর্ড আইকন/ইনপুট পিকার থেকে ‘আমার আঞ্চলিক’ নির্বাচন করুন।\n\nপরবর্তী আপডেটে জেলা নির্বাচন, যাচাইকৃত আঞ্চলিক শব্দ, ভয়েস টাইপিং ও ইমোজি যোগ করা হবে।"
            textSize = 15f
            setTextColor(Color.DKGRAY)
            setPadding(4, 22, 4, 0)
        }
        root.addView(title)
        root.addView(intro)
        root.addView(enable)
        root.addView(choose)
        root.addView(note)
        setContentView(root)
    }
}
