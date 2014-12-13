package com.huawei.toolbar.util;

import android.app.Activity;
import android.content.SharedPreferences;

import com.huawei.toolbar.ToolbarApplication;

/**
 * SharedPreferences管理器
 * @author wWX191016
 */
public class PresDataUtil
{
    private static SharedPreferences mPreferences;
    
    private static SharedPreferences.Editor mEditor;
    
    private static final String PREFS = "toolbar";
    
    public PresDataUtil()
    {
        
    }
    
    private static void init()
    {
        mPreferences =
            ToolbarApplication.getInstance().getSharedPreferences(PREFS,
                Activity.MODE_PRIVATE);
    }
    
    public static void save(String key, int value)
    {
        init();
        mEditor = mPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }
    
    public static void save(String key, String value)
    {
        init();
        mEditor = mPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }
    
    public static int getInt(String string)
    {
        init();
        return mPreferences.getInt(string, 0);
    }
    
    public static String getString(String string)
    {
        init();
        return mPreferences.getString(string, "");
    }
}
