package cn.shineiot.blog.blogfragment;

import java.util.List;

import cn.shineiot.base.module.BaseView;
import cn.shineiot.blog.bean.WxPublic;
import cn.shineiot.blog.bean.WxPublicData;

/**
 * @author GF63
 */
public interface BlogView extends BaseView {
	void successWxPublic(List<WxPublic> wxPublicList);
	void successWxArticle(WxPublicData wxPublicData);
}
