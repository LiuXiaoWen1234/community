package com.nowcoder.community.util;

public interface CommunityConstant {

    /**
    激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
    重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
    激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态的登录凭证的超时时间
     */
    int DEFAULT_DEXPIRE_SECONDS = 3600 * 12;  //3600秒*12=12h

    /**
     * 记录状态下的登录凭证的超时时间
     */
    int REMEMBER_DEXPIRE_SECONDS = 3600 * 24 * 100;
}
