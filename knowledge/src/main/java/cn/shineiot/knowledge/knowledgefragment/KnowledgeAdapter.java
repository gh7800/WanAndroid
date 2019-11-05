package cn.shineiot.knowledge.knowledgefragment;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.knowledge.R;
import cn.shineiot.knowledge.bean.Knowledge;

/**
 * @author GF63
 */
public class KnowledgeAdapter extends BaseQuickAdapter<Knowledge,BaseViewHolder> {
	public KnowledgeAdapter(int layoutResId) {
		super(layoutResId);
	}

	@Override
	protected void convert(@NonNull BaseViewHolder helper, Knowledge item) {
		List<Knowledge.ChildrenBean> childrenBeanList = item.getChildren();
		StringBuffer stringBuffer = new StringBuffer();
		if(!childrenBeanList.isEmpty()){
			LogUtil.e(childrenBeanList.size());
			for (int i = 0,size = childrenBeanList.size(); i < size; i++) {
				if(i == size-1){
					stringBuffer.append(childrenBeanList.get(i).getName());
				}else{
					stringBuffer.append(childrenBeanList.get(i).getName()+",");
				}
			}
		}
		helper.setText(R.id.item_title,item.getName())
		.setText(R.id.item_children,stringBuffer.toString());
	}
}
