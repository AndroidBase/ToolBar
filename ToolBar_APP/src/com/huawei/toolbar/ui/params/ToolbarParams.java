package com.huawei.toolbar.ui.params;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class ToolbarParams extends LayoutParams
{
    public ToolbarParams()
    {
        type = LayoutParams.TYPE_SYSTEM_ALERT;
        format = PixelFormat.RGBA_8888;
        flags =
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    }
}
