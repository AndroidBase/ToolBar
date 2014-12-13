package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;
import com.huawei.toolbar.ui.view.WaterView;

public class BeforePlayWindow extends BaseWindow
{
    private Button mCloseBtn;
    
    private Button mShopBtn;
    
    private Button mWaterBtn;
    
    private WaterView mWaterView;
    
    public BeforePlayWindow(Handler handler)
    {
        super(handler);
        
        mCloseBtn = (Button) mWindow.findViewById(R.id.close_btn);
        mCloseBtn.setOnClickListener(this);
        mShopBtn = (Button) mWindow.findViewById(R.id.shop_btn);
        mShopBtn.setOnClickListener(this);
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
        if (mWaterBtn == v)
        {
            mWaterView.setProgress((int) (Math.random() * 100));
        }
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.before_play_view;
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
