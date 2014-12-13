package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;
import com.huawei.toolbar.util.PresDataUtil;

public class MiniWindow extends BaseWindow implements OnTouchListener
{
    private Button mMiniBtn;
    
    private int mStartX, mStartY;
    
    private int mParamsX, mParamsY;
    
    private int moveX, moveY;
    
    public MiniWindow(Handler handler)
    {
        super(handler);
        
        mMiniBtn = (Button) mWindow.findViewById(R.id.mini_btn);
        mMiniBtn.setOnTouchListener(this);
        mMiniBtn.setOnClickListener(this);
        
        mParams.x = screenW;
        mParams.y = screenH / 3;
        PresDataUtil.save(GlobleConstants.SharedPreferencesString.MINI_X,
            mParams.x);
        PresDataUtil.save(GlobleConstants.SharedPreferencesString.MINI_Y,
            mParams.y);
    }
    
    @Override
    public void create()
    {
        super.create();
        if (mParams.x > screenW / 2)
        {
            AnimationRight();
        }
        else
        {
            AnimationLeft();
        }
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        Boolean isMoved = false;
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                // 动画过程中点击时，取消动画
                mMiniBtn.clearAnimation();
                // 点击悬浮窗时使之恢复至动画前状态
                mMiniBtn.invalidate();
                mMiniBtn.bringToFront();
                mStartX = (int) event.getRawX();
                mStartY = (int) event.getRawY();
                mParamsX = mParams.x;
                mParamsY = mParams.y;
                break;
            
            case MotionEvent.ACTION_MOVE:
                moveX = (int) event.getRawX() - mStartX;
                moveY = (int) event.getRawY() - mStartY;
                mParams.x = mParamsX + moveX;
                mParams.y = mParamsY + moveY;
                mManager.updateViewLayout(mWindow, mParams);
                break;
            
            case MotionEvent.ACTION_UP:
                if (Math.abs(moveX) > 10 || Math.abs(moveY) > 10)
                {
                    isMoved = true;
                }
                if (mParams.x > screenW / 2)
                {
                    mParams.x = screenW - mMiniBtn.getWidth();
                    mManager.updateViewLayout(mWindow, mParams);
                    AnimationRight();
                }
                else
                {
                    mParams.x = 0;
                    mManager.updateViewLayout(mWindow, mParams);
                    AnimationLeft();
                }
                moveX = 0;
                moveY = 0;
                PresDataUtil.save(GlobleConstants.SharedPreferencesString.MINI_X,
                    mParams.x);
                PresDataUtil.save(GlobleConstants.SharedPreferencesString.MINI_Y,
                    mParams.y);
                break;
            
            default:
                break;
        }
        return isMoved;
    }
    
    @Override
    public void onClick(View v)
    {
        if (mMiniBtn == v)
        {
            mHandler.sendEmptyMessage(GlobleConstants.WindowType.MAIN);
        }
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.mini_view;
    }
    
    /**
     * MiniWindow 依靠在右边的动画
     */
    private void AnimationRight()
    {
        Animation animation =
            AnimationUtils.loadAnimation(mContext, R.anim.hide_right);
        animation.setFillAfter(true);
        mMiniBtn.clearAnimation();
        mMiniBtn.startAnimation(animation);
    }
    
    /**
     * MiniWindow 依靠在左边的动画
     */
    private void AnimationLeft()
    {
        Animation animation =
            AnimationUtils.loadAnimation(mContext, R.anim.hide_left);
        animation.setFillAfter(true);
        mMiniBtn.clearAnimation();
        mMiniBtn.startAnimation(animation);
    }
    
    @Override
    protected int paramsType()
    {
        return WINDOW_MINI;
    }
    
    @Override
    protected int animationLayoutId()
    {
        return 0;
    }
}
