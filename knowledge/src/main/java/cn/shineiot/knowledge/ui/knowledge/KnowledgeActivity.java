package cn.shineiot.knowledge.ui.knowledge;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.utils.StatusBarUtil;
import cn.shineiot.knowledge.R;
import cn.shineiot.knowledge.R2;
import cn.shineiot.knowledge.knowledgefragment.children.KnowledgeDetailFragment;

/**
 * @author GF63
 * 知识体系详情
 * tablayout+viewpager+fragment
 */
@Route(path = ARouterPath.KNOWLEDGE_ACTIVITY)
public class KnowledgeActivity extends BaseMvpActivity {
	@BindView(R2.id.knowledgeTabLayout)
	TabLayout tabLayout;
	@BindView(R2.id.knowledgeViewPager)
	ViewPager viewPager;
	@BindView(R2.id.knowledgeToolBar)
	Toolbar toolbar;
	@BindView(R2.id.knowledgeAppBarLayout)
	AppBarLayout appBarLayout;
	@BindView(R2.id.knowledgeCollapsingToolbarLayout)
	CollapsingToolbarLayout collapsingToolbarLayout;
	@BindView(R2.id.knowledgeDetailNum)
	TextView textView;

	private KnowledgePagerAdapter pagerAdapter;
	private FragmentManager fragmentManager;
	private List<Fragment> fragmentList = new ArrayList<>();

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_knowledge;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		String title = getIntent().getStringExtra("title");
		ArrayList<Integer> list = getIntent().getIntegerArrayListExtra("cid");
		ArrayList<String> tabNameList = getIntent().getStringArrayListExtra("tabName");
		for (int i = 0,size = list.size(); i < size; i++) {
			int cid = list.get(i);
			KnowledgeDetailFragment fragment = KnowledgeDetailFragment.newInstace(cid);
			fragmentList.add(fragment);
		}

//		toolbar.setTitle(title);
		collapsingToolbarLayout.setTitle(title);
		textView.setText("共"+list.size()+"个子分类");
		toolbar.setNavigationIcon(getResources().getDrawable(cn.shineiot.base.R.drawable.icon_back));
		toolbar.setNavigationOnClickListener(view -> onBackPressed());

		fragmentManager = getSupportFragmentManager();
		pagerAdapter = new KnowledgePagerAdapter(fragmentManager);
		pagerAdapter.setData(fragmentList,tabNameList);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);
		tabLayout.setupWithViewPager(viewPager);

		viewPager.addOnPageChangeListener(pageChangeListener);

	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int i, float v, int i1) {
		}

		@Override
		public void onPageSelected(int i) {

		}

		@Override
		public void onPageScrollStateChanged(int i) {

		}
	};
}
