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
public class WindowParamsSmall extends WindowManager.LayoutParams
{
    public WindowParamsSmall()
    {
        Context context = ToolbarApplication.getInstance();
        WindowManager manager =
            (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenH = manager.getDefaultDisplay().getHeight();
        
        type = LayoutParams.TYPE_SYSTEM_ALERT;
        format = PixelFormat.RGBA_8888;
        flags =
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        gravity = Gravity.TOP;
        y = screenH / 4;
    }
}
