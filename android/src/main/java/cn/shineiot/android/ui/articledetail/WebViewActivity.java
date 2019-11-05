package cn.shineiot.android.ui.articledetail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import cn.shineiot.android.R;
import cn.shineiot.android.R2;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;

/**
 * @author GF63
 */
@Route(path = ARouterPath.WEB_VIEW_ACTIVITY)
public class WebViewActivity extends BaseMvpActivity {
	@BindView(R2.id.toolbar)
	Toolbar toolbar;
	@BindView(R2.id.linearLayout)
	LinearLayout linearLayout;
	private AgentWeb mAgentWeb;

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_webview;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		String url = getIntent().getStringExtra("url");
		String title = getIntent().getStringExtra("title");
		setupToolbar_center(toolbar,title);

		mAgentWeb = AgentWeb.with(this)
				.setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))
				.useDefaultIndicator()
				.createAgentWeb()
				.ready()
				.go(url);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mAgentWeb.getWebLifeCycle().onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mAgentWeb.getWebLifeCycle().onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(mAgentWeb.handleKeyEvent(keyCode,event)){
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}
}
