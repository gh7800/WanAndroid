package cn.shineiot.base.module;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhy.changeskin.SkinManager;

import butterknife.ButterKnife;
import cn.shineiot.base.R;
import cn.shineiot.base.manager.AppManager;


/**
 * @author zhjh.
 */
public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {


	protected abstract int provideLayoutId();

	protected abstract void initView(Bundle savedInstanceState);

	public Context mContext;

	public T presenter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(provideLayoutId());
		ButterKnife.bind(this);
		AppManager.getAppManager().addActivity(this);
//		BaseBus.getInstance().register(this);
		mContext = this;
		initP();
		initView(savedInstanceState);

		SkinManager.getInstance().register(this);
	}


	@Override
	protected void onResume() {
		Log.e(this.getClass().getName(), "Resumebus");
		resumeP();
		super.onResume();
	}

	public void setupToolbar(Toolbar toolbar, String title) {
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();

		if (actionBar != null) {
			actionBar.setDisplayUseLogoEnabled(true);
			actionBar.setDisplayShowTitleEnabled(true);
			actionBar.setDisplayShowHomeEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);

			toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_back));
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onBackPressed();
				}
			});
		}
		setTitle(title);

	}

	public void setupToolbar_center(Toolbar toolbar, String title) {
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayUseLogoEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setDisplayShowHomeEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_back));
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onBackPressed();
				}
			});
		}
		if (null != title) {
			TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
			mTitle.setText(title);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e(this.getClass().getName(), "Pausebus");

	}


	@Override
	protected void onDestroy() {
		Log.e(this.getClass().getName(), "Destroy");
		detachP();
//		BaseBus.getInstance().unregister(this);
		AppManager.getAppManager().removeActivity(this);
		SkinManager.getInstance().unregister(this);
		super.onDestroy();
	}

	public abstract T initPresenter();

	private void initP() {
		presenter = initPresenter();
		if (presenter != null) {
			presenter.attachView((V) this);
		}
	}

	private void resumeP() {
		if (presenter != null) {
			presenter.attachView((V) this);
		}
	}

	private void detachP() {
		if (presenter != null) {
			presenter.detachView();
			presenter = null;
		}
	}

	@Override
	public Resources getResources() {
		Resources res = super.getResources();
		Configuration config = new Configuration();
		config.setToDefaults();
		res.updateConfiguration(config, res.getDisplayMetrics());
		return res;
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		if (null != this.getCurrentFocus()) {
//			/**
//			 * 点击空白位置 隐藏软键盘
//			 */
//			InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//			return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
//		}
//		return super.onTouchEvent(event);
//	}
}
