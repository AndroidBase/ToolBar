package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.huawei.toolbar.GlobleConstants;
import com.huawei.toolbar.R;

public class BuySuccessWindow extends BaseWindow
{
    private Button mCloseBtn;
    
    public BuySuccessWindow(Handler handler)
    {
        super(handler);
        
        mCloseBtn = (Button) mWindow.findViewById(R.id.buy_success_close);
        mCloseBtn.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v)
    {
        if (mCloseBtn == v)
        {
            AnimationUp(GlobleConstants.OprationType.CLOSE, false);
        }
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.buy_success_view;
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
