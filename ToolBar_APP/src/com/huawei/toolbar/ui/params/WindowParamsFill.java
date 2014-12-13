package com.huawei.toolbar.ui.params;

import com.huawei.toolbar.ToolbarApplication;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * params通用属性
 * @author wWX191016
 *
 */
public class WindowParamsFill extends WindowManager.LayoutParams
{
    public WindowParamsFill()
    {
        Context context = ToolbarApplication.getInstance();
        WindowManager manager =
            (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenH = manager.getDefaultDisplay().getHeight();
        int screenW = manager.getDefaultDisplay().getWidth();
        
        type = LayoutParams.TYPE_SYSTEM_ALERT;
        format = PixelFormat.RGBA_8888;
        flags =
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        width = screenW;
        height = screenH - 100;
        gravity = Gravity.TOP;
        y = 0;
    }
}
