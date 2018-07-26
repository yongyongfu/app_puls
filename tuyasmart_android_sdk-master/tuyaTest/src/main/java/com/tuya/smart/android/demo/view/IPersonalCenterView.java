package com.tuya.smart.android.demo.view;

import com.tuya.smart.android.mvp.view.IView;

/**
 * Created by letian on 15/6/18.
 */
public interface IPersonalCenterView extends IView {
    /**
     * 设置用户名
     *
     * @param userName
     */
    void setUserName(String userName);

    /**
     * 设置昵称
     *
     * @param nickName
     */
    void setNickName(String nickName);


}
