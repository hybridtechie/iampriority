package com.hybridtechie.iampriority.util;

import android.app.Activity;
import android.graphics.Color;
import android.view.Window;
import android.view.WindowManager;


public class Tools {

    /**
     * Making notification bar transparent
     */
    public static void setSystemBarTransparent(Activity act) {
        Window window = act.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
