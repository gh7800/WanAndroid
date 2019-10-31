package cn.shineiot.compontentpro.ui.home;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhy.changeskin.SkinManager;

import butterknife.BindView;
import butterknife.OnClick;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.utils.Constants;
import cn.shineiot.base.utils.DBHelper;
import cn.shineiot.base.utils.DBUtil;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.base.utils.SPUtils;
import cn.shineiot.base.utils.ToastUtils;
import cn.shineiot.compontentpro.R;
import me.jessyan.autosize.AutoSizeCompat;


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

    private TextView tvUsername;
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

        String username = (String) SPUtils.get(mContext, Constants.USERNAME, "");
        View layout = navigationView.inflateHeaderView(R.layout.nav_header);
        tvUsername = layout.findViewById(R.id.nav_header_tv);
        if (!TextUtils.isEmpty(username)) {
            tvUsername.setText(username);
            navigationView.getMenu().findItem(R.id.menu_item_six).setTitle("退出");
        }

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
    public MainPresenter initPresenter() {
        return new MainPresenter();
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
                case R.id.menu_item_six:
                    String username = (String) SPUtils.get(mContext,Constants.USERNAME,"");
                    if(TextUtils.isEmpty(username)) {
                        ARouter.getInstance().build("/login/loginActivity").navigation(MainActivity.this, Constants.MAINTO_LOGINACTIVITY);
                    }else{
                        ToastUtils.showToast("退出登录");
                        SPUtils.put(mContext,Constants.USERNAME,"");
                        DBUtil.getInstance().deleteTable(DBHelper.getDaoSession().getUserDao());

                        tvUsername.setText("");
                        navigationView.getMenu().findItem(R.id.menu_item_six).setTitle("登录");
                    }
                    drawerLayout.closeDrawers();
                    break;
                default:
                    break;
            }
            return false;//是否选中
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == Constants.MAINTO_LOGINACTIVITY) {
            String username = (String) SPUtils.get(mContext,Constants.USERNAME,"");
            tvUsername.setText(username);
            navigationView.getMenu().findItem(R.id.menu_item_six).setTitle("退出");
        }
    }

    /**
     * 1、全局设置字体需要重写，设置字体
     * 2、AndroidAutoSize 适配异常的情况下需要重写，并添加上AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources());
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();

        AutoSizeCompat.autoConvertDensityOfGlobal(res);

        Configuration config = res.getConfiguration();
        if (fontSizeScale > 0.5) {
            config.fontScale = fontSizeScale;//1 设置正常字体大小的倍数
        }
        res.updateConfiguration(config, res.getDisplayMetrics());

        return res;
    }
}
