package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;
import com.huawei.toolbar.ui.view.WaterView;

public class WarnWindow extends BaseWindow
{
    private Button mCloseBtn;
    
    private Button mShopBtn;
    
    private Button mDraw;
    
    private Button mWaterBtn;
    
    private WaterView mWaterView;
    
    public WarnWindow(Handler handler)
    {
        super(handler);
        
        mCloseBtn = (Button) mWindow.findViewById(R.id.close_btn);
        mCloseBtn.setOnClickListener(this);
        mShopBtn = (Button) mWindow.findViewById(R.id.shop_btn);
        mShopBtn.setOnClickListener(this);
        mDraw = (Button) mWindow.findViewById(R.id.lucky_btn);
        mDraw.setOnClickListener(this);
        mWaterBtn = (Button) mWindow.findViewById(R.id.water_btn);
        mWaterBtn.setOnClickListener(this);
        mWaterView = (WaterView) mWindow.findViewById(R.id.water);
    }
    
    @Override
    public void onClick(View v)
    {
        if (mCloseBtn == v)
        {
            AnimationUp(GlobleConstants.OprationType.CLOSE, true);
        }
        if (mShopBtn == v)
        {
            AnimationUp(GlobleConstants.WindowType.SHOP, false);
        }
        if (mDraw == v)
        {
            int i = (int) (Math.random() * 2);
            if (i == 0)
            {
                AnimationUp(GlobleConstants.WindowType.LUCKY, false);
            }
            else
            {
                AnimationUp(GlobleConstants.WindowType.UNLUCKY, false);
            }
        }
        if (mWaterBtn == v)
        {
            mWaterView.setProgress((int) (Math.random() * 100));
        }
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.warn_play_view;
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
        return WINDOW_SMALL;
    }
    
    @Override
    protected int animationLayoutId()
    {
        return R.id.layout_body;
    }
}
