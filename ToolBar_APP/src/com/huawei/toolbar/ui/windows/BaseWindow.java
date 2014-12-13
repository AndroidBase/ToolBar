package com.huawei.toolbar.ui.windows;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;
import com.huawei.toolbar.ToolbarApplication;
import com.huawei.toolbar.ui.params.ToolbarParams;
import com.huawei.toolbar.util.LogUtil;
import com.huawei.toolbar.util.PresDataUtil;

public abstract class BaseWindow implements OnClickListener
{
    protected WindowManager mManager;
    
    protected Context mContext;
    
    protected Handler mHandler;
    
    protected ToolbarParams mParams;
    
    protected int screenH;
    
    protected int screenW;
    
    protected View mWindow;
    
    private View mAnimationLayout;
    
    private Boolean isWindowAdded = false;
    
    private Boolean isAnimationFromMini = false;
    
    public void setIsAnimationFromMini(Boolean isAnimationFromMini)
    {
        this.isAnimationFromMini = isAnimationFromMini;
    }
    
    /**
     * 可拖动的mini悬浮窗属性
     */
    protected static final int WINDOW_MINI = 0;
    
    /**
     * 小悬浮窗属性
     */
    protected static final int WINDOW_SMALL = 1;
    
    /**
     * 全屏悬浮窗属性
     */
    protected static final int WINDOW_FILL = 2;
    
    public BaseWindow(Handler handler)
    {
        mHandler = handler;
        
        mContext = ToolbarApplication.getInstance();
        mManager =
            (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        
        getScreenSize();
        
        mWindow = LayoutInflater.from(mContext).inflate(windowLayout(), null);
        
        mParams = new ToolbarParams();
        
        mAnimationLayout = mWindow.findViewById(animationLayoutId());
    }
    
    /**
     * [一句话功能简述]需要加载的布局<BR>
     * [功能详细描述]
     * @return resource id
     */
    protected abstract int windowLayout();
    
    /**
     * [一句话功能简述]需要加载的params类型<BR>
     * [功能详细描述]
     * @return 类型
     */
    protected abstract int paramsType();
    
    /**
     * [一句话功能简述]需要加载动画的布局,返回0时无动画<BR>
     * [功能详细描述]
     * @return 动画资源id
     */
    protected abstract int animationLayoutId();
    
    /**
     * [一句话功能简述]创建悬浮窗<BR>
     * [功能详细描述]
     */
    public void create()
    {
        if (isWindowAdded)
        {
            return;
        }
        getScreenSize();
        setParams(paramsType());
        mManager.addView(mWindow, mParams);
        isWindowAdded = true;
    }
    
    /**
     * [一句话功能简述]关闭悬浮窗<BR>
     * [功能详细描述]
     */
    public void remove()
    {
        if (!isWindowAdded)
        {
            return;
        }
        mManager.removeView(mWindow);
        isWindowAdded = false;
    }
    
    /**
     * [一句话功能简述]更新悬浮窗<BR>
     * [功能详细描述]
     */
    public void update()
    {
        if (!isWindowAdded)
        {
            return;
        }
        getScreenSize();
        setParams(paramsType());
        mManager.updateViewLayout(mWindow, mParams);
        LogUtil.i("Window", "screenH=" + screenH + ",screenW=" + screenW);
    }
    
    /**
     * [一句话功能简述]window的出现动画<BR>
     * [功能详细描述]
     */
    protected void AnimationDown()
    {
        if (animationLayoutId() != 0)
        {
            Animation animation;
            
            if (isAnimationFromMini)
            {
                animation =
                    new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                        Animation.ABSOLUTE, getAnimationX(),
                        Animation.ABSOLUTE, getAnimationY());
                animation.setDuration(500);
                
                isAnimationFromMini = false;
            }
            else
            {
                animation =
                    AnimationUtils.loadAnimation(mContext, R.anim.window_down);
            }
            
            animation.setFillAfter(true);
            mAnimationLayout.clearAnimation();
            mAnimationLayout.startAnimation(animation);
        }
    }
    
    /**
     * [一句话功能简述]window的关闭动画，动画结束会发送一个消息<BR>
     * [功能详细描述]
     * @param what 发送message的what值
     */
    protected void AnimationUp(final int what, Boolean fromLevel1)
    {
        if (animationLayoutId() != 0)
        {
            Animation animation;
            
            if (fromLevel1)
            {
                animation =
                    new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                        Animation.ABSOLUTE, getAnimationX(),
                        Animation.ABSOLUTE, getAnimationY());
                animation.setDuration(500);
                
            }
            else
            {
                animation =
                    AnimationUtils.loadAnimation(mContext, R.anim.window_up);
            }
            
            animation.setFillAfter(true);
            animation.setAnimationListener(new AnimationListener()
            {
                
                @Override
                public void onAnimationStart(Animation animation)
                {
                    
                }
                
                @Override
                public void onAnimationRepeat(Animation animation)
                {
                    
                }
                
                @Override
                public void onAnimationEnd(Animation animation)
                {
                    mHandler.sendEmptyMessage(what);
                }
            });
            
            mAnimationLayout.clearAnimation();
            mAnimationLayout.startAnimation(animation);
        }
    }
    
    /**
     * [一句话功能简述]获取屏幕高宽<BR>
     * [功能详细描述]
     */
    private void getScreenSize()
    {
        screenH = mManager.getDefaultDisplay().getHeight();
        screenW = mManager.getDefaultDisplay().getWidth();
    }
    
    private void setParams(int type)
    {
        switch (type)
        {
            case WINDOW_MINI:
                mParams.gravity = Gravity.LEFT | Gravity.TOP;
                mParams.width = LayoutParams.WRAP_CONTENT;
                mParams.height = LayoutParams.WRAP_CONTENT;
                if (mParams.y > screenH)
                {
                    mParams.y = screenH / 3;
                }
                
                if (mParams.x > 0)
                {
                    mParams.x = screenW;
                }
                else
                {
                    mParams.x = 0;
                }
                
                break;
            case WINDOW_SMALL:
                mParams.gravity = Gravity.CENTER;
                mParams.width = screenW;
                mParams.height = screenH;
                break;
            
            case WINDOW_FILL:
                mParams.gravity = Gravity.TOP;
                mParams.width = screenW;
                mParams.height = screenH * 11 / 12;
                mParams.y = 0;
                break;
            
            default:
                break;
        }
    }
    
    /**
     * [一句话功能简述]获取屏幕像素密度<BR>
     * [功能详细描述]
     * @return
     */
    private float getDensity()
    {
        DisplayMetrics dm = new DisplayMetrics();
        dm = mContext.getResources().getDisplayMetrics();
        return dm.density;
    }
    
    /**
     * [一句话功能简述]获取从mini窗口展开的动画起始x轴位置<BR>
     * [功能详细描述]
     * @return
     */
    private float getAnimationX()
    {
        int i =
            PresDataUtil.getInt(GlobleConstants.SharedPreferencesString.MINI_X);
        
        // 小窗口宽度dip值
        int smallWindowDipX = 300;
        
        // mini窗口寬度dip值
        int miniWindowDipX = 40;
        
        return (i - (screenW - smallWindowDipX * getDensity() - miniWindowDipX
            * getDensity()) / 2);
    }
    
    /**
     * [一句话功能简述]获取从mini窗口展开的动画起始y轴位置<BR>
     * [功能详细描述]
     * @return
     */
    private float getAnimationY()
    {
        int i =
            PresDataUtil.getInt(GlobleConstants.SharedPreferencesString.MINI_Y);
        
        // 小窗口高度dip值
        int smallWindowDipX = 200;
        
        // mini窗口高度dip值
        int miniWindowDipX = 40;
        
        return (i - (screenH - smallWindowDipX * getDensity() - miniWindowDipX
            * getDensity()) / 2);
    }
}
