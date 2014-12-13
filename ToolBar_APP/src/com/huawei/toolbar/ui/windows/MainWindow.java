package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;
import com.huawei.toolbar.ui.view.WaterView;

public class MainWindow extends BaseWindow
{
    private Button mCloseBtn;
    
    private Button mShowBtn;
    
    private Button mShopBtn;
    
    private Button mMessageBtn;
    
    private Button mAboutBtn;
    
    private ImageView mUpImage;
    
    private ImageView mDownImage;
    
    private Button mWaterBtn;
    
    private WaterView mWaterView;
    
    private RelativeLayout mFlowLayout;
    
    /**
     * 记录点击展开流量展示的次数
     */
    private int btn_clickNum;
    
    public MainWindow(Handler handler)
    {
        super(handler);
        
        mCloseBtn = (Button) mWindow.findViewById(R.id.close_btn);
        mCloseBtn.setOnClickListener(this);
        mShowBtn = (Button) mWindow.findViewById(R.id.showbtn);
        mShowBtn.setOnClickListener(this);
        mShopBtn = (Button) mWindow.findViewById(R.id.btn_store);
        mShopBtn.setOnClickListener(this);
        mMessageBtn = (Button) mWindow.findViewById(R.id.btn_message);
        mMessageBtn.setOnClickListener(this);
        mAboutBtn = (Button) mWindow.findViewById(R.id.btn_about);
        mAboutBtn.setOnClickListener(this);
        mUpImage = (ImageView) mWindow.findViewById(R.id.up_img);
        mDownImage = (ImageView) mWindow.findViewById(R.id.down_img);
        mFlowLayout = (RelativeLayout) mWindow.findViewById(R.id.layout_view);
        mWaterBtn = (Button) mWindow.findViewById(R.id.water_btn);
        mWaterBtn.setOnClickListener(this);
        mWaterView = (WaterView) mWindow.findViewById(R.id.water);
    }
    
    @Override
    public void create()
    {
        super.create();
        btn_clickNum = 0;
        mFlowLayout.setVisibility(View.INVISIBLE);
        AnimationDown();
    }
    
    @Override
    public void onClick(View v)
    {
        if (mCloseBtn == v)
        {
            AnimationUp(GlobleConstants.OprationType.CLOSE, true);
        }
        if (mShowBtn == v)
        {
            mFlowLayout.setVisibility(View.VISIBLE);
            btn_clickNum++;
            if (btn_clickNum % 2 == 1)
            {
                Animation animationDown =
                    AnimationUtils.loadAnimation(mContext, R.anim.scale_down);
                animationDown.setFillAfter(true);
                mFlowLayout.clearAnimation();
                mFlowLayout.startAnimation(animationDown);
                mDownImage.setVisibility(View.GONE);
                mUpImage.setVisibility(View.VISIBLE);
                mMessageBtn.setClickable(false);
                mAboutBtn.setClickable(false);
                mShopBtn.setClickable(false);
                
            }
            else if (btn_clickNum % 2 == 0)
            {
                Animation animationUp =
                    AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
                animationUp.setFillAfter(true);
                mFlowLayout.clearAnimation();
                mFlowLayout.startAnimation(animationUp);
                mDownImage.setVisibility(View.VISIBLE);
                mUpImage.setVisibility(View.GONE);
                mMessageBtn.setClickable(true);
                mAboutBtn.setClickable(true);
                mShopBtn.setClickable(true);
            }
        }
        if (mShopBtn == v)
        {
            AnimationUp(GlobleConstants.WindowType.SHOP, false);
        }
        if (mMessageBtn == v)
        {
            AnimationUp(GlobleConstants.WindowType.MESSAGE, false);
        }
        if (mAboutBtn == v)
        {
            AnimationUp(GlobleConstants.WindowType.ABOUT, false);
        }
        if (mWaterBtn == v)
        {
            mWaterView.setProgress((int) (Math.random() * 100));
        }
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.manager;
    }
    
    @Override
    protected int paramsType()
    {
        return WINDOW_SMALL;
    }
    
    @Override
    protected int animationLayoutId()
    {
        return R.id.layout_body;
    }
}
