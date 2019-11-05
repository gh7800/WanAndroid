package cn.shineiot.knowledge.knowledgefragment;

import java.util.List;

import cn.shineiot.base.module.BaseView;
import cn.shineiot.knowledge.bean.Knowledge;

/**
 * @author GF63
 */
public interface KnowledgeView extends BaseView {
	void succeessData(List<Knowledge> list);
}
