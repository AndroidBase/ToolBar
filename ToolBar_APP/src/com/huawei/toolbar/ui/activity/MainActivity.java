package com.huawei.toolbar.ui.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.MyWindowManager;
import com.huawei.toolbar.R;
import com.huawei.toolbar.ToolbarService;

/**
 * 启动页面
 * @author wWX191016
 *
 */
public class MainActivity extends Activity implements OnClickListener
{
    private Button mStartService;
    
    private Button mStopService;
    
    private Button mBeforeView;
    
    private Button mAfterView;
    
    private Button mWarnView;
    
    private LinearLayout mWindowControlLayout;
    
    private MyWindowManager mViewManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        mStartService = (Button) findViewById(R.id.start_service);
        mStartService.setOnClickListener(this);
        mStopService = (Button) findViewById(R.id.stop_service);
        mStopService.setOnClickListener(this);
        mBeforeView = (Button) findViewById(R.id.before_btn);
        mBeforeView.setOnClickListener(this);
        mAfterView = (Button) findViewById(R.id.after_btn);
        mAfterView.setOnClickListener(this);
        mWarnView = (Button) findViewById(R.id.warn_btn);
        mWarnView.setOnClickListener(this);
        mWindowControlLayout = (LinearLayout) findViewById(R.id.window_layout);
        
        mViewManager = MyWindowManager.getInstance();
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.start_service:
                if (!isServiceRun())
                {
                    Intent startToolbarService =
                        new Intent(this, ToolbarService.class);
                    startService(startToolbarService);
                }
                
                mWindowControlLayout.setVisibility(View.VISIBLE);
                break;
            
            case R.id.stop_service:
                if (isServiceRun())
                {
                    Intent stopToolbarService =
                        new Intent(this, ToolbarService.class);
                    stopService(stopToolbarService);
                }
                
                mWindowControlLayout.setVisibility(View.GONE);
                break;
            
            case R.id.before_btn:
                mViewManager.sendEmptyMessage(GlobleConstants.WindowType.BEFORE_PLAY);
                break;
            
            case R.id.after_btn:
                mViewManager.sendEmptyMessage(GlobleConstants.WindowType.AFTER_PLAY);
                break;
            
            case R.id.warn_btn:
                mViewManager.sendEmptyMessage(GlobleConstants.WindowType.WARN);
                break;
            
            default:
                break;
        }
    }
    
    /**
     * [一句话功能简述]<BR>
     * [功能详细描述]
     * @return
     */
    public boolean isServiceRun()
    {
        ActivityManager myManager =
            (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<RunningServiceInfo> runningService =
            (ArrayList<RunningServiceInfo>) myManager.getRunningServices(1000);
        for (int i = 0; i < runningService.size(); i++)
        {
            if (runningService.get(i).service.getClassName()
                .toString()
                .equals("com.huawei.toolbar.ToolbarService"))
            {
                return true;
            }
        }
        return false;
    }
}
