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
import com.yigitkula.pokemonlistapp.databinding.FragmentDetailBinding
import com.yigitkula.pokemonlistapp.databinding.PopupWindowBinding
import com.yigitkula.pokemonlistapp.model.Pokemon
import com.yigitkula.pokemonlistapp.view.DetailFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.popup_window.view.*
import java.lang.Exception

class Window(  // declaring required variables
    private val context: Context,
    private val pokemon: Pokemon
) {
    private lateinit var dataBinding: FragmentDetailBinding
    private val mView: View
    private var mParams: WindowManager.LayoutParams? = null
    private val mWindowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val layoutInflater: LayoutInflater

    fun open() {
        try {
            // check if the view is already 
            // inflated or present in the window 
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
            // remove the view from the window
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).removeView(mView)
            // invalidate the view
            mView.invalidate()
            // remove all views
            (mView.parent as ViewGroup).removeAllViews()

            // the above steps are necessary when you are adding and removing
            // the view simultaneously, it might give some exceptions
        } catch (e: Exception) {
            Log.d("Error2", e.toString())
        }
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // set the layout parameters of the window
            mParams = WindowManager.LayoutParams( // Shrink the window to wrap the content rather 
                // than filling the screen
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,  // Display it on top of other application windows
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,  // Don't let it grab the input focus
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  // Make the underlying application window visible
                // through any transparent parts
                PixelFormat.TRANSLUCENT
            )
        }
        else {
            mParams = WindowManager.LayoutParams( // Shrink the window to wrap the content rather
                // than filling the screen
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,  // Display it on top of other application windows
                WindowManager.LayoutParams.TYPE_PHONE,  // Don't let it grab the input focus
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  // Make the underlying application window visible
                // through any transparent parts
                PixelFormat.TRANSLUCENT
            )
        }

        val binding = PopupWindowBinding.inflate(ContextCompat.getSystemService(context,
            LayoutInflater::class.java)!!)

        binding.pokemon = pokemon

        // set onClickListener on the remove button, which removes
        // the view from the window

        binding.closeWin.setOnClickListener {
            close()
        }

        mView = binding.root

        // getting a LayoutInflater
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Define the position of the
        // window within the screen
        mParams!!.gravity = Gravity.CENTER

    }
}