package cn.shineiot.compontentpro.ui.integral;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 * 积分
 */
@Route(path = ARouterPath.INTEGRAL_ACTIVITY)
public class IntegralActivity extends BaseMvpActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_integral;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setupToolbar_center(toolbar,"积分系统");

	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}
}
