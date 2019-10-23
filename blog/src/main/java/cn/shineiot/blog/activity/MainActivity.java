package cn.shineiot.blog.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.blog.R;
import cn.shineiot.blog.blogfragment.BlogFragment;

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
		BlogFragment navigationFragment = new BlogFragment();
		fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.blog_FrameLayout,navigationFragment).commit();

	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}
}
