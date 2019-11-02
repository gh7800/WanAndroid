package cn.shineiot.android.androidfragment;

import android.text.TextUtils;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import cn.shineiot.android.R;
import cn.shineiot.android.bean.AndroidNews;
import cn.shineiot.base.utils.LogUtil;


/**
 * @author GF63
 */
public class AndroidNewsAdapter extends BaseQuickAdapter<AndroidNews.News, BaseViewHolder> {

	public AndroidNewsAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(BaseViewHolder helper, AndroidNews.News item) {
		helper.setText(R.id.new_chapterName, item.getChapterName()).setText(R.id.new_title, item.getTitle()).setText(R.id.new_niceDate, item.getNiceDate()).setText(R.id.new_author, TextUtils.isEmpty(item.getShareUser()) ? item.getAuthor() : item.getShareUser()).addOnClickListener(R.id.new_checkBox);

		CheckBox checkBox = helper.getView(R.id.new_checkBox);
		if (item.isCollect()) {
			checkBox.setChecked(true);
		} else {
			checkBox.setChecked(false);
		}
	}

}
