package cn.shineiot.compontentpro.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.manager.AppManager;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 */
public class SplashActivity extends BaseMvpActivity {
	@BindView(R.id.textView)
	TextView textView;
	int n = 2;

	Handler handler = new Handler();

	@Override
	protected int provideLayoutId() {
		return R.layout.layout_splash;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		textView.setText(String.valueOf(n));

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				n--;
				if (n < 0) {
					ARouter.getInstance().build(ARouterPath.MAIN_ACTIVITY).navigation();
					AppManager.getAppManager().finishActivity(SplashActivity.this);
				}else{
					textView.setText(String.valueOf(n));
					handler.postDelayed(this,1000);
				}
			}
		}, 1000);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}
}
