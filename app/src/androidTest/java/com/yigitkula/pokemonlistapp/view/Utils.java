package com.yigitkula.pokemonlistapp.view;

import static android.provider.Settings.System.getString;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiObject2;

import com.yigitkula.pokemonlistapp.R;

public class Utils {
    public static void sleep(long value){
        try{
            Thread.sleep(value);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

}
