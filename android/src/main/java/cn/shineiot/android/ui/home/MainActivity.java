package cn.shineiot.android.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import cn.shineiot.android.R;
import cn.shineiot.android.androidfragment.AndroidFragment;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;

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
		AndroidFragment navigationFragment = new AndroidFragment();
		fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.android_FrameLayout,navigationFragment).commit();

	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}
}
