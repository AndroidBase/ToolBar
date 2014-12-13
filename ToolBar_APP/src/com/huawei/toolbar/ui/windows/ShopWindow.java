package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;

public class ShopWindow extends BaseWindow
{
    private Button mBackBtn;
    
    private Button mCloseBtn;
    
    private Button mBuyBtn1, mBuyBtn2, mBuyBtn3;
    
    private Button mPullUpBtn;
    
    private Button mTabBtn1, mTabBtn2, mTabBtn3;
    
    private RelativeLayout mTabLayout1, mTabLayout2, mTabLayout3;
    
    public ShopWindow(Handler handler)
    {
        super(handler);
        
        mBackBtn = (Button) mWindow.findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(this);
        mCloseBtn = (Button) mWindow.findViewById(R.id.close_btn);
        mCloseBtn.setOnClickListener(this);
        mBuyBtn1 = (Button) mWindow.findViewById(R.id.btn_shop1);
        mBuyBtn1.setOnClickListener(this);
        mBuyBtn2 = (Button) mWindow.findViewById(R.id.btn_shop2);
        mBuyBtn2.setOnClickListener(this);
        mBuyBtn3 = (Button) mWindow.findViewById(R.id.btn_shop3);
        mBuyBtn3.setOnClickListener(this);
        mPullUpBtn = (Button) mWindow.findViewById(R.id.pull_btn);
        mPullUpBtn.setOnClickListener(this);
        mTabBtn1 = (Button) mWindow.findViewById(R.id.btn_tab1);
        mTabBtn1.setOnClickListener(this);
        mTabBtn2 = (Button) mWindow.findViewById(R.id.btn_tab2);
        mTabBtn2.setOnClickListener(this);
        mTabBtn3 = (Button) mWindow.findViewById(R.id.btn_tab3);
        mTabBtn3.setOnClickListener(this);
        mTabLayout1 = (RelativeLayout) mWindow.findViewById(R.id.tab_1);
        mTabLayout2 = (RelativeLayout) mWindow.findViewById(R.id.tab_2);
        mTabLayout3 = (RelativeLayout) mWindow.findViewById(R.id.tab_3);
        
        mTabBtn1.setSelected(true);
        mTabBtn2.setSelected(false);
        mTabBtn3.setSelected(false);
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
        if (mBuyBtn1 == v)
        {
            AnimationUp(GlobleConstants.WindowType.BUY, false);
        }
        if (mBuyBtn2 == v)
        {
            AnimationUp(GlobleConstants.WindowType.BUY, false);
        }
        if (mBuyBtn3 == v)
        {
            AnimationUp(GlobleConstants.WindowType.BUY, false);
        }
        if (mPullUpBtn == v)
        {
            AnimationUp(GlobleConstants.OprationType.BACK, false);
        }
        if (mTabBtn1 == v)
        {
            mTabBtn1.setSelected(true);
            mTabBtn2.setSelected(false);
            mTabBtn3.setSelected(false);
            mTabLayout1.setVisibility(View.VISIBLE);
            mTabLayout2.setVisibility(View.INVISIBLE);
            mTabLayout3.setVisibility(View.INVISIBLE);
        }
        if (mTabBtn2 == v)
        {
            mTabBtn2.setSelected(true);
            mTabBtn1.setSelected(false);
            mTabBtn3.setSelected(false);
            mTabLayout2.setVisibility(View.VISIBLE);
            mTabLayout1.setVisibility(View.INVISIBLE);
            mTabLayout3.setVisibility(View.INVISIBLE);
        }
        if (mTabBtn3 == v)
        {
            mTabBtn3.setSelected(true);
            mTabBtn1.setSelected(false);
            mTabBtn2.setSelected(false);
            mTabLayout3.setVisibility(View.VISIBLE);
            mTabLayout1.setVisibility(View.INVISIBLE);
            mTabLayout2.setVisibility(View.INVISIBLE);
        }
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.shop_view;
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
