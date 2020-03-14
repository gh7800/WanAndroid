package cn.shineiot.android.ui.articledetail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import cn.shineiot.android.R;
import cn.shineiot.android.R2;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.utils.StringUtils;

/**
 * @author GF63
 */
@Route(path = ARouterPath.WEB_VIEW_ACTIVITY)
public class WebViewActivity extends BaseMvpActivity<ArticleView,ArticlePresenter> {
	public static final String TITLE = "title";
	public static final String URL = "url";
	public static final String ID = "id";

	@BindView(R2.id.toolbar)
	Toolbar toolbar;
	@BindView(R2.id.linearLayout)
	LinearLayout linearLayout;
	private AgentWeb mAgentWeb;

	private int id;

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_webview;
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		String url = getIntent().getStringExtra(URL);
		id = getIntent().getIntExtra(ID,0);
		String str = getIntent().getStringExtra(TITLE);
		String title = StringUtils.delHtmlTags(str);

		setupToolbar_center(toolbar, title);

		mAgentWeb = AgentWeb.with(this).setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1)).useDefaultIndicator().createAgentWeb().ready().go(url);
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
		if (mAgentWeb.handleKeyEvent(keyCode, event)) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public ArticlePresenter initPresenter() {
		return new ArticlePresenter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.webview_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.wb_collect) {
			presenter.collect(id);
		} else if (itemId == R.id.wb_share) {

		}
		return super.onOptionsItemSelected(item);
	}
}
