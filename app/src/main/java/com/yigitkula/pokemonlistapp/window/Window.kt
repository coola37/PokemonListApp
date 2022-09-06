package com.yigitkula.pokemonlistapp.window

import android.content.Context
import android.os.Build
import android.graphics.PixelFormat
import android.sax.RootElement
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.yigitkula.pokemonlistapp.R

import com.yigitkula.pokemonlistapp.databinding.PopupWindowBinding
import com.yigitkula.pokemonlistapp.model.Pokemon
import com.yigitkula.pokemonlistapp.view.DetailFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.popup_window.view.*
import java.lang.Exception

class Window(
    private val context: Context,
    private val pokemon: Pokemon
) {
    private val mView: View
    private var mParams: WindowManager.LayoutParams? = null
    private val mWindowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val layoutInflater: LayoutInflater

    fun open() {
        try {
            if (mView.windowToken == null) {
                if (mView.parent == null) {
                    mWindowManager.addView(mView, mParams)
                }
            }
        } catch (e: Exception) {
            Log.d("Error1", e.toString())
        }
    }

    fun close() {
        try {
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).removeView(mView)
            mView.invalidate()
            (mView.parent as ViewGroup).removeAllViews()

        } catch (e: Exception) {
            Log.d("Error2", e.toString())
        }
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mParams = WindowManager.LayoutParams(
               500,
                500,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
        }
        else {
            mParams = WindowManager.LayoutParams(
                500,
                500,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
        }

        val binding = PopupWindowBinding.inflate(ContextCompat.getSystemService(context,
            LayoutInflater::class.java)!!)

        binding.pokemon = pokemon


        binding.closeWin.setOnClickListener {
            close()
        }

        mView = binding.root

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        mParams!!.gravity = Gravity.CENTER

    }
}