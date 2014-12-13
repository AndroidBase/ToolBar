package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;

public class MessageWindow extends BaseWindow
{
    private Button mBackBtn;
    
    private Button mCloseBtn;
    
    private Button mPullUpBtn;
    
    public MessageWindow(Handler handler)
    {
        super(handler);
        
        mBackBtn = (Button) mWindow.findViewById(R.id.back_btn);
        mCloseBtn = (Button) mWindow.findViewById(R.id.close_btn);
        mBackBtn.setOnClickListener(this);
        mCloseBtn.setOnClickListener(this);
        mPullUpBtn = (Button) mWindow.findViewById(R.id.pull_btn);
        mPullUpBtn.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v)
    {
        if (mBackBtn == v)
        {
            AnimationUp(GlobleConstants.OprationType.BACK, false);
        }
        if (mCloseBtn == v)
        {
            AnimationUp(GlobleConstants.OprationType.CLOSE, false);
        }
        if (mPullUpBtn == v)
        {
            AnimationUp(GlobleConstants.OprationType.BACK, false);
        }
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.message_view;
    }
    
    @Override
    public void create()
    {
        super.create();
        AnimationDown();
    }
    
    @Override
    protected int paramsType()
    {
        return WINDOW_FILL;
    }
    
    @Override
    protected int animationLayoutId()
    {
        return R.id.layout_back;
    }
}
