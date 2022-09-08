package com.yigitkula.pokemonlistapp.view;

import static android.provider.Settings.System.getString;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.util.HumanReadables;
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
    public static ViewAssertion isNotDisplayed() {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noView) {
                if (view != null && isDisplayed().matches(view)) {
                    throw new AssertionError("View is present in the hierarchy and Displayed: "
                            + HumanReadables.describe(view));
                }
            }
        };
    }
 /*   public static void waitActivityShown(Class<?> mClass) {
        int timeout = 10000;
        while (timeout >= 0) {
            try {
                Thread.sleep(100);
                timeout -= 100;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (getForegroundPackage().getClassName().equals(mClass.getCanonicalName())) {
                break;
            }
        }
    }*/
}
