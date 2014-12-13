package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;

public class FeedbackWindow extends BaseWindow
{
    private Button mBackBtn;
    
    private Button mCloseBtn;
    
    private Button mFeedbackBtn;
    
    private Button mPullUpBtn;
    
    private EditText mFeedbackText;
    
    public FeedbackWindow(Handler handler)
    {
        super(handler);
        
        mBackBtn = (Button) mWindow.findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(this);
        mCloseBtn = (Button) mWindow.findViewById(R.id.close_btn);
        mCloseBtn.setOnClickListener(this);
        mFeedbackBtn = (Button) mWindow.findViewById(R.id.btn_feedback);
        mFeedbackBtn.setOnClickListener(this);
        mPullUpBtn = (Button) mWindow.findViewById(R.id.pull_btn);
        mPullUpBtn.setOnClickListener(this);
        mFeedbackText = (EditText) mWindow.findViewById(R.id.edit_feedback);
        
        // 使反馈EditText接受触屏事件
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
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
        if (mFeedbackBtn == v)
        {
            AnimationUp(GlobleConstants.WindowType.FEEDBACK_SUCCESS, false);
        }
        if (mPullUpBtn == v)
        {
            AnimationUp(GlobleConstants.OprationType.BACK, false);
        }
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.feedback_view;
    }
    
    @Override
    public void create()
    {
        super.create();
        AnimationDown();
        mFeedbackText.setText("");
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
