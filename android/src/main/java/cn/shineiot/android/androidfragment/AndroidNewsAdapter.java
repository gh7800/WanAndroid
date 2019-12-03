package cn.shineiot.android.androidfragment;

import android.text.TextUtils;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.android.R;
import cn.shineiot.android.bean.AndroidNews;


/**
 * @author GF63
 */
public class AndroidNewsAdapter extends BaseQuickAdapter<AndroidNews.News, BaseViewHolder> {

	public AndroidNewsAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(BaseViewHolder helper, AndroidNews.News item) {
		String title = delHtmlTags(item.getTitle());
		helper.setText(R.id.new_chapterName, item.getChapterName()).setText(R.id.new_title, title).setText(R.id.new_niceDate, item.getNiceDate()).setText(R.id.new_author, TextUtils.isEmpty(item.getShareUser()) ? item.getAuthor() : item.getShareUser()).addOnClickListener(R.id.new_checkBox);

		CheckBox checkBox = helper.getView(R.id.new_checkBox);
		if (item.isCollect()) {
			checkBox.setChecked(true);
		} else {
			checkBox.setChecked(false);
		}
	}
	public static String delHtmlTags(String htmlStr) {
		//定义script的正则表达式，去除js可以防止注入
		String scriptRegex="<script[^>]*?>[\\s\\S]*?<\\/script>";
		//定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
		String styleRegex="<style[^>]*?>[\\s\\S]*?<\\/style>";
		//定义HTML标签的正则表达式，去除标签，只提取文字内容
		String htmlRegex="<[^>]+>";
		//定义空格,回车,换行符,制表符
		String spaceRegex = "\\s*|\t|\r|\n";

		// 过滤script标签
		htmlStr = htmlStr.replaceAll(scriptRegex, "");
		// 过滤style标签
		htmlStr = htmlStr.replaceAll(styleRegex, "");
		// 过滤html标签
		htmlStr = htmlStr.replaceAll(htmlRegex, "");
		// 过滤空格等
		htmlStr = htmlStr.replaceAll(spaceRegex, "");
		return htmlStr.trim(); // 返回文本字符串
	}
}
