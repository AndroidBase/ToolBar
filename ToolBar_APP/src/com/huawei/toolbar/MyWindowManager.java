package com.huawei.toolbar;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;

import com.huawei.toolbar.ui.windows.AboutWindow;
import com.huawei.toolbar.ui.windows.AfterPlayWindow;
import com.huawei.toolbar.ui.windows.BaseWindow;
import com.huawei.toolbar.ui.windows.BeforePlayWindow;
import com.huawei.toolbar.ui.windows.BuySuccessWindow;
import com.huawei.toolbar.ui.windows.BuyWindow;
import com.huawei.toolbar.ui.windows.FeedbackSuccessWindow;
import com.huawei.toolbar.ui.windows.FeedbackWindow;
import com.huawei.toolbar.ui.windows.LuckyWindow;
import com.huawei.toolbar.ui.windows.MainWindow;
import com.huawei.toolbar.ui.windows.MessageWindow;
import com.huawei.toolbar.ui.windows.MiniWindow;
import com.huawei.toolbar.ui.windows.ShopWindow;
import com.huawei.toolbar.ui.windows.UnluckyWindow;
import com.huawei.toolbar.ui.windows.WarnWindow;

/**
 * 管理window展示
 * @author wWX191016
 */
public class MyWindowManager extends Handler
{
    private static MyWindowManager instance;
    
    /**
     * 消息传入前的 Window
     */
    private BaseWindow lastWindow = null;
    
    /**
     * 此次需要展示的 Window
     */
    private BaseWindow currentWindow = null;
    
    private List<BaseWindow> windows;
    
    private MiniWindow mMiniWindow;
    
    private MainWindow mMainWindow;
    
    private ShopWindow mShopWindow;
    
    private MessageWindow mMessageWindow;
    
    private AboutWindow mAboutWindow;
    
    private BuyWindow mBuyWindow;
    
    private BuySuccessWindow mBuySuccessWindow;
    
    private FeedbackWindow mFeedbackWindow;
    
    private FeedbackSuccessWindow mFeedbackSuccessWindow;
    
    private BeforePlayWindow mBeforePlayWindow;
    
    private AfterPlayWindow mAfterPlayWindow;
    
    private WarnWindow mWarnWindow;
    
    private LuckyWindow mLuckyWindow;
    
    private UnluckyWindow mUnluckyWindow;
    
    public MyWindowManager()
    {
        instance = this;
        
        windows = new ArrayList<BaseWindow>();
        mMiniWindow = new MiniWindow(this);
        mMainWindow = new MainWindow(this);
        mShopWindow = new ShopWindow(this);
        mMessageWindow = new MessageWindow(this);
        mAboutWindow = new AboutWindow(this);
        mBuySuccessWindow = new BuySuccessWindow(this);
        mBuyWindow = new BuyWindow(this);
        mFeedbackWindow = new FeedbackWindow(this);
        mFeedbackSuccessWindow = new FeedbackSuccessWindow(this);
        mBeforePlayWindow = new BeforePlayWindow(this);
        mAfterPlayWindow = new AfterPlayWindow(this);
        mWarnWindow = new WarnWindow(this);
        mLuckyWindow = new LuckyWindow(this);
        mUnluckyWindow = new UnluckyWindow(this);
    }
    
    public void handleMessage(Message msg)
    {
        super.handleMessage(msg);
        switch (msg.what)
        {
            case GlobleConstants.WindowType.MINI:
                windows.clear();
                windows.add(mMiniWindow);
                break;
            
            case GlobleConstants.WindowType.MAIN:
                
                windows.clear();
                windows.add(mMainWindow);
                break;
            
            case GlobleConstants.WindowType.SHOP:
                
                windows.add(mShopWindow);
                break;
            
            case GlobleConstants.WindowType.MESSAGE:
                
                windows.add(mMessageWindow);
                break;
            
            case GlobleConstants.WindowType.ABOUT:
                
                windows.add(mAboutWindow);
                break;
            
            case GlobleConstants.WindowType.BUY_SUCCESS:
                
                windows.add(mBuySuccessWindow);
                break;
            
            case GlobleConstants.WindowType.BUY:
                
                windows.add(mBuyWindow);
                break;
            
            case GlobleConstants.WindowType.FEEDBACK:
                
                windows.add(mFeedbackWindow);
                break;
            
            case GlobleConstants.WindowType.FEEDBACK_SUCCESS:
                
                windows.add(mFeedbackSuccessWindow);
                break;
            
            case GlobleConstants.WindowType.BEFORE_PLAY:
                
                windows.clear();
                windows.add(mBeforePlayWindow);
                break;
            
            case GlobleConstants.WindowType.AFTER_PLAY:
                
                windows.clear();
                windows.add(mAfterPlayWindow);
                break;
            
            case GlobleConstants.WindowType.WARN:
                
                windows.clear();
                windows.add(mWarnWindow);
                break;
            
            case GlobleConstants.WindowType.LUCKY:
                
                windows.clear();
                windows.add(mLuckyWindow);
                break;
            
            case GlobleConstants.WindowType.UNLUCKY:
                
                windows.clear();
                windows.add(mUnluckyWindow);
                break;
            
            case GlobleConstants.OprationType.BACK:
                if (!windows.isEmpty())
                {
                    windows.remove(windows.size() - 1);
                }
                break;
            
            case GlobleConstants.OprationType.CLOSE:
                windows.clear();
                windows.add(mMiniWindow);
                break;
            
            case GlobleConstants.OprationType.CLOSEALL:
                windows.clear();
                break;
            
            default:
                break;
        }
        
        currentWindow =
            (windows.isEmpty()) ? null : windows.get(windows.size() - 1);
        
        if (currentWindow != lastWindow)
        {
            if (lastWindow != null && currentWindow != null)
            {
                if ((currentWindow.equals(mMainWindow)
                    || currentWindow.equals(mBeforePlayWindow)
                    || currentWindow.equals(mAfterPlayWindow) || currentWindow.equals(mWarnWindow))
                    && (lastWindow.equals(mMiniWindow)))
                {
                    currentWindow.setIsAnimationFromMini(true);
                }
            }
            
            if (lastWindow != null)
            {
                lastWindow.remove();
            }
            if (currentWindow != null)
            {
                currentWindow.create();
            }
            lastWindow = currentWindow;
        }
    }
    
    public static MyWindowManager getInstance()
    {
        if (instance == null)
        {
            instance = new MyWindowManager();
        }
        return instance;
    }
    
    public void updateView()
    {
        if (currentWindow != null)
        {
            currentWindow.update();
        }
    }
}
