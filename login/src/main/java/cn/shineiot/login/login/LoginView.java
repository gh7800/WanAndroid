package cn.shineiot.login.login;

import cn.shineiot.base.module.BaseView;
import cn.shineiot.base.bean.User;

public interface LoginView extends BaseView {
    void SuccessData(User user);
}
