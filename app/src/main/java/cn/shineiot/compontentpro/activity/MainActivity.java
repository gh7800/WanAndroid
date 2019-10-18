package cn.shineiot.compontentpro.activity;


import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhy.changeskin.SkinManager;

import butterknife.BindView;
import butterknife.OnClick;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.utils.Constants;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.SPUtils;
import cn.shineiot.base.utils.ToastUtils;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 */
@Route(path = "/app/mainActivity")
public class MainActivity extends BaseMvpActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;

    private Fragment fragmentAndroid, fragmentBlog, fragmentKnowledge, fragmentNavigation;
    private Fragment currentFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public boolean isSkin = false;
    private float fontSizeScale;

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setupToolbar_center(toolbar, "首页");
        toolbar.setNavigationIcon(R.drawable.icon_menu);

        fontSizeScale = (float) SPUtils.get(this, Constants.SP_FontScale, 0.0f);

        toolbar.setNavigationOnClickListener(toolbarNavigationClick);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        navigationView.setItemIconTintList(null);

        fragmentAndroid = (Fragment) ARouter.getInstance().build("/android/androidFragment").navigation();
        fragmentBlog = (Fragment) ARouter.getInstance().build("/blog/blogFragment").navigation();
        fragmentKnowledge = (Fragment) ARouter.getInstance().build("/knowledge/knowledgeFragment").navigation();
        fragmentNavigation = (Fragment) ARouter.getInstance().build("/navigation/navigationFragment").navigation();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, fragmentAndroid).commit();
        currentFragment = fragmentAndroid;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.bottom_home, R.id.bottom_sort, R.id.bottom_cart, R.id.bottom_user})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_home:
                switchContent(fragmentAndroid);
                toolbarTitle.setText("首页");
                break;
            case R.id.bottom_sort:
                switchContent(fragmentBlog);
                toolbarTitle.setText("博客");
                break;
            case R.id.bottom_cart:
                switchContent(fragmentKnowledge);
                toolbarTitle.setText("知识体系");
                break;
            case R.id.bottom_user:
                switchContent(fragmentNavigation);
                toolbarTitle.setText("导航");
                break;
            default:
                break;
        }
    }

    public void switchContent(Fragment to) {
        if (currentFragment != to) {
            FragmentTransaction transaction = fragmentManager.beginTransaction().setCustomAnimations(
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            if (!to.isAdded()) {
                transaction.hide(currentFragment).add(R.id.framelayout, to).commit();
            } else {
                transaction.hide(currentFragment).show(to).commit(); //提交
            }
            currentFragment = to;
        }
    }

    public View.OnClickListener toolbarNavigationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START);
            } else {
                drawerLayout.closeDrawers();
            }
        }
    };
    public NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_item_one:
                    drawerLayout.closeDrawers();
                    break;
                case R.id.menu_item_two:
                    drawerLayout.closeDrawers();
                    break;
                case R.id.menu_item_three:
                    drawerLayout.closeDrawers();
                    break;
                case R.id.menu_item_four:
                    drawerLayout.closeDrawers();
                    ARouter.getInstance().build("/baseActivity/fontSizeActivity").navigation();
                    break;
                case R.id.menu_item_five:
//                    ARouter.getInstance().build("/activity/detailActivity").navigation();
                    drawerLayout.closeDrawers();
                    LogUtil.e("");
                    if (!isSkin) {
                        isSkin = true;
                        menuItem.setTitle("日间");
                        SkinManager.getInstance().changeSkin("night");
                        menuItem.setIcon(R.drawable.skin_time_night);
                    } else {
                        SkinManager.getInstance().changeSkin("light");
                        isSkin = false;
                        menuItem.setTitle("夜间");
                        menuItem.setIcon(R.drawable.skin_time_light);
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = res.getConfiguration();
        if (fontSizeScale > 0.5) {
            config.fontScale = fontSizeScale;//1 设置正常字体大小的倍数
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
