package com.huawei.toolbar;

import android.app.Application;

/**
 * 应用application
 * @author wWX191016
 *
 */
public class ToolbarApplication extends Application
{
    private static ToolbarApplication instance;
    
    public static ToolbarApplication getInstance()
    {
        return instance;
    }
    
    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }
}
