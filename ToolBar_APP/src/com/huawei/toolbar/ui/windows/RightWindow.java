package com.huawei.toolbar.ui.windows;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.huawei.toolbar.R;

public class RightWindow extends BaseWindow implements OnTouchListener
{
    private Button mRightBtn;
    
    private int moveX, moveY;
    
    private int mStartX, mStartY;
    
    public RightWindow(Handler handler)
    {
        super(handler);
        // TODO Auto-generated constructor stub
        mRightBtn = (Button) mWindow.findViewById(R.id.right_btn);
        mRightBtn.setOnTouchListener(this);
    }
    
    @Override
    public void onClick(View v)
    {
        
    }
    
    @Override
    protected int windowLayout()
    {
        return R.layout.right_view;
    }
    
    @Override
    protected int paramsType()
    {
        return WINDOW_FILL;
    }
    
    @Override
    protected int animationLayoutId()
    {
        return 0;
    }
    
    @Override
    public void create()
    {
        super.create();
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        Boolean isMoved = false;
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mStartX = (int) event.getRawX();
                break;
            
            case MotionEvent.ACTION_MOVE:
                moveX = (int) event.getRawX() - mStartX;
                break;
            
            case MotionEvent.ACTION_UP:
                if (Math.abs(moveX) > 10)
                {
                    isMoved = true;
                }
                moveX = 0;
                
                break;
            
            default:
                break;
        }
        return isMoved;
    }
    
}
