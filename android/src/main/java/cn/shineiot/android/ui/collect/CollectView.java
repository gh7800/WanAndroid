package cn.shineiot.android.ui.collect;

import java.util.List;

import cn.shineiot.base.bean.Collect;
import cn.shineiot.base.module.BaseView;

public interface CollectView extends BaseView {
	void successData(List<Collect.DatasBean> collectList);
	void successUnCollet(int position,boolean isCollect);
}
