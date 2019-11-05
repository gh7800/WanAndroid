package cn.shineiot.knowledge.knowledgefragment.children;

import java.util.List;

import cn.shineiot.base.module.BaseView;
import cn.shineiot.knowledge.bean.KnowledgeDetail;

public interface KnowledgeDetailView extends BaseView {
	void successData(List<KnowledgeDetail.Children> list,int page);
}
