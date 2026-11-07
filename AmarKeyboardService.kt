package com.amarancholik.keyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class AmarKeyboardService : InputMethodService() {
    private var englishMode = false
    private val bengaliRows = listOf(
        listOf("ক","খ","গ","ঘ","ঙ","চ","ছ","জ","ঝ","ঞ"),
        listOf("ট","ঠ","ড","ঢ","ণ","ত","থ","দ","ধ","ন"),
        listOf("প","ফ","ব","ভ","ম","য","র","ল","শ","ষ"),
        listOf("স","হ","ড়","ঢ়","য়","ং","ঃ","ঁ","ৎ")
    )
    private val englishRows = listOf(
        listOf("q","w","e","r","t","y","u","i","o","p"),
        listOf("a","s","d","f","g","h","j","k","l"),
        listOf("z","x","c","v","b","n","m")
    )

    override fun onCreateInputView(): View {
        return buildKeyboard()
    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        // Password and other sensitive fields are handled locally; this MVP has no network/AI transmission.
    }

    private fun buildKeyboard(): View {
        val outer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(5, 7, 5, 7)
            setBackgroundColor(Color.rgb(225, 230, 235))
        }

        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val brand = TextView(this).apply {
            text = "আমার আঞ্চলিক"
            textSize = 15f
            setTypeface(null, Typeface.BOLD)
            setTextColor(Color.rgb(25, 65, 50))
            setPadding(8, 8, 8, 8)
        }
        val mode = smallButton(if (englishMode) "বাংলা" else "EN") {
            englishMode = !englishMode
            setInputView(buildKeyboard())
        }
        val emoji = smallButton("😊") { commit("😊") }
        val voice = smallButton("🎙") {
            // Voice input will be added in a later release.
            commit("")
        }
        header.addView(brand, LinearLayout.LayoutParams(0, -2, 1f))
        header.addView(mode)
        header.addView(emoji)
        header.addView(voice)
        outer.addView(header)

        val suggestion = TextView(this).apply {
            text = "আঞ্চলিক শব্দের সাজেশন পরবর্তী আপডেটে"
            textSize = 12f
            gravity = Gravity.CENTER
            setTextColor(Color.DKGRAY)
            setPadding(4, 5, 4, 9)
        }
        outer.addView(suggestion)

        val rows = if (englishMode) englishRows else bengaliRows
        rows.forEach { row ->
            val line = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
            }
            row.forEach { key ->
                val b = keyButton(key) { commit(key) }
                line.addView(b, LinearLayout.LayoutParams(0, dp(44), 1f).apply {
                    setMargins(dp(2), dp(2), dp(2), dp(2))
                })
            }
            outer.addView(line)
        }

        val actions = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        actions.addView(keyButton("?123") { commit("?123") }, weighted())
        actions.addView(keyButton("⌫") { backspace() }, weighted())
        actions.addView(keyButton("বাংলা/EN") {
            englishMode = !englishMode
            setInputView(buildKeyboard())
        }, weighted())
        actions.addView(keyButton("স্পেস") { commit(" ") }, weighted(1.8f))
        actions.addView(keyButton("↵") { enter() }, weighted())
        outer.addView(actions)
        return outer
    }

    private fun weighted(weight: Float = 1f) =
        LinearLayout.LayoutParams(0, dp(46), weight).apply {
            setMargins(dp(2), dp(2), dp(2), dp(2))
        }

    private fun keyButton(label: String, action: () -> Unit) = Button(this).apply {
        text = label
        textSize = if (label.length > 2) 12f else 18f
        isAllCaps = false
        setTextColor(Color.rgb(35, 40, 45))
        setBackgroundColor(Color.WHITE)
        setPadding(0, 0, 0, 0)
        setOnClickListener { action() }
    }

    private fun smallButton(label: String, action: () -> Unit) = Button(this).apply {
        text = label
        textSize = 12f
        isAllCaps = false
        setPadding(3, 0, 3, 0)
        setOnClickListener { action() }
    }

    private fun commit(value: String) {
        currentInputConnection?.commitText(value, 1)
    }

    private fun backspace() {
        currentInputConnection?.deleteSurroundingText(1, 0)
    }

    private fun enter() {
        val ic = currentInputConnection ?: return
        val action = currentInputEditorInfo?.imeOptions?.and(EditorInfo.IME_MASK_ACTION)
        if (action != null && action != EditorInfo.IME_ACTION_NONE && action != EditorInfo.IME_ACTION_UNSPECIFIED) {
            ic.performEditorAction(action)
        } else {
            ic.commitText("\n", 1)
        }
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
