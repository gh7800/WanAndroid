package cn.shineiot.knowledge.knowledgefragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.knowledge.R;

/**
 * @author GF63
 */
@Route(path = "/fragment/knowledgeFragment")
public class KnowledgeFragment extends BaseMvpFragment {
    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
