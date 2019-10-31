package cn.shineiot.android.androidfragment;

import java.util.List;

import cn.shineiot.android.bean.AndroidNews;
import cn.shineiot.android.bean.Banner;
import cn.shineiot.base.module.BaseView;

public interface AndroidView extends BaseView {
	void successBannerData(List<Banner> list);
	void successAndroidNews(List<AndroidNews.News> androidNewsList,int page);
}
