package cn.shineiot.navigation.navigationfragment;

import java.util.List;

import cn.shineiot.base.module.BaseView;
import cn.shineiot.navigation.bean.Navigation;

public interface NavigationView extends BaseView {
	void successData(List<Navigation> list);
}
