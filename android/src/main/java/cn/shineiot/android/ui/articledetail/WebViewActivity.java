package cn.shineiot.android.ui.articledetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebViewClient;

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
public class WebViewActivity extends BaseMvpActivity<ArticleView, ArticlePresenter> {
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
        id = getIntent().getIntExtra(ID, 0);
        String str = getIntent().getStringExtra(TITLE);
        String title = StringUtils.delHtmlTags(str);

        setupToolbar_center(toolbar, title);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(R.color.colorPrimary)
                .setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        view.loadUrl(customJs(url));
                    }
                })
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(url);
    }

    /**
     * 加载js，去掉掘金、简书、CSDN等H5页面的Title、底部操作栏，以及部分广告
     */
    private String customJs(String url) {
        StringBuilder js = new StringBuilder();
        js.append("javascript:(function(){");
        switch (Uri.parse(url).getHost()) {
            case "juejin.im": {
                js.append("var headerList = document.getElementsByClassName('main-header-box');");
                js.append("if(headerList&&headerList.length){headerList[0].parentNode.removeChild(headerList[0])}");
                js.append("var openAppList = document.getElementsByClassName('open-in-app');");
                js.append("if(openAppList&&openAppList.length){openAppList[0].parentNode.removeChild(openAppList[0])}");
                js.append("var actionBox = document.getElementsByClassName('action-box');");
                js.append("if(actionBox&&actionBox.length){actionBox[0].parentNode.removeChild(actionBox[0])}");
                js.append("var actionBarList = document.getElementsByClassName('action-bar');");
                js.append("if(actionBarList&&actionBarList.length){actionBarList[0].parentNode.removeChild(actionBarList[0])}");
                js.append("var columnViewList = document.getElementsByClassName('column-view');");
                js.append("if(columnViewList&&columnViewList.length){columnViewList[0].style.margin = '0px'}");
                break;
            }
            case "www.jianshu.com": {
                js.append("var jianshuHeader = document.getElementById('jianshu-header');");
                js.append("if(jianshuHeader){jianshuHeader.parentNode.removeChild(jianshuHeader)}");
                js.append("var headerShimList = document.getElementsByClassName('header-shim');");
                js.append("if(headerShimList&&headerShimList.length){headerShimList[0].parentNode.removeChild(headerShimList[0])}");
                js.append("var fubiaoList = document.getElementsByClassName('fubiao-dialog');");
                js.append("if(fubiaoList&&fubiaoList.length){fubiaoList[0].parentNode.removeChild(fubiaoList[0])}");
                js.append("var ads = document.getElementsByClassName('note-comment-above-ad-wrap');");
                js.append("if(ads&&ads.length){ads[0].parentNode.removeChild(ads[0])}");

                js.append("var lazyShimList = document.getElementsByClassName('v-lazy-shim');");
                js.append("if(lazyShimList&&lazyShimList.length&&lazyShimList[0]){lazyShimList[0].parentNode.removeChild(lazyShimList[0])}");
                js.append("if(lazyShimList&&lazyShimList.length&&lazyShimList[1]){lazyShimList[1].parentNode.removeChild(lazyShimList[1])}");
                break;
            }
            case "blog.csdn.net": {
                js.append("var csdnToolBar = document.getElementById('csdn-toolbar');");
                js.append("if(csdnToolBar){csdnToolBar.parentNode.removeChild(csdnToolBar)}");
                js.append("var csdnMain = document.getElementById('main');");
                js.append("if(csdnMain){csdnMain.style.margin='0px'}");
                js.append("var operate = document.getElementById('operate');");
                js.append("if(operate){operate.parentNode.removeChild(operate)}");
                break;
            }
            default:
                break;
        }
        js.append("})()");
        return js.toString();
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
