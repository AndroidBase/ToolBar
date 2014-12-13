package com.huawei.toolbar;

/**
 * 全局常量
 * @author wWX191016
 *
 */
public class GlobleConstants
{
    /**
     * 窗口类型
     * @author wWX191016
     *
     */
    public interface WindowType
    {
        int BASIC = 0x10000;
        
        /**
         * 迷你悬浮窗
         */
        int MINI = BASIC + 1;
        
        /**
         * 悬浮窗主页
         */
        int MAIN = BASIC + 2;
        
        /**
         * 商店页面
         */
        int SHOP = BASIC + 3;
        
        /**
         * 消息页面
         */
        int MESSAGE = BASIC + 4;
        
        /**
         * 业务介绍页面
         */
        int ABOUT = BASIC + 5;
        
        /**
         * 套餐订购页面
         */
        int BUY = BASIC + 6;
        
        /**
         * 套餐订购成功页面
         */
        int BUY_SUCCESS = BASIC + 7;
        
        /**
         * 反馈页面
         */
        int FEEDBACK = BASIC + 8;
        
        /**
         * 反馈成功页面
         */
        int FEEDBACK_SUCCESS = BASIC + 9;
        
        /**
         * 播放前页面
         */
        int BEFORE_PLAY = BASIC + 10;
        
        /**
         * 播放后页面
         */
        int AFTER_PLAY = BASIC + 11;
        
        /**
         * 流量报警页面
         */
        int WARN = BASIC + 12;
        
        /**
         * 中奖页面
         */
        int LUCKY = BASIC + 13;
        
        /**
         * 未中奖页面
         */
        int UNLUCKY = BASIC + 14;
    }
    
    /**
     * [一句话功能简述]activity 类型<BR>
     * [功能详细描述]
     * @author wWX191016
     * @version [RCS Client V100R001C03, 2014-9-10] 
     */
    public interface ActivityType
    {
        int BASIC = 0x20000;
        
        /**
         * 二级主activity页面
         */
        int SUB_MAIN = BASIC + 1;
    }
    
    /**
     * 操作类型
     * @author wWX191016
     *
     */
    public interface OprationType
    {
        
        int BASIC = 0x30000;
        
        /**
         * 返回
         */
        int BACK = BASIC + 1;
        
        /**
         * 关闭
         */
        int CLOSE = BASIC + 2;
        
        /**
         * 关闭所有窗口
         */
        int CLOSEALL = BASIC + 3;
        
        /**
         * 刷新窗口
         */
        int REFRESH = BASIC + 4;
    }
    
    /**
     * [一句话功能简述]网络请求常量<BR>
     * [功能详细描述]
     * @author wWX191016
     * @version [RCS Client V100R001C03, 2014-9-25] 
     */
    public interface Http
    {
        /**
         * 网络请求超时时间
         */
        int TIMEOUT = 10000;
    }
    
    public interface SharedPreferencesString
    {
        String MINI_X = "mini_x";
        
        String MINI_Y = "mini_y";
    }
}