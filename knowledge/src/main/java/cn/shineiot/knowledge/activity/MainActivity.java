package cn.shineiot.knowledge.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.knowledge.R;
import cn.shineiot.knowledge.knowledgefragment.KnowledgeFragment;

/**
 * @author GF63
 */
public class MainActivity extends BaseMvpActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        KnowledgeFragment navigationFragment = new KnowledgeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.knowledge_FrameLayout,navigationFragment).commit();

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
