package cn.shineiot.compontentpro.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 */
@Route(path = "/app/mainActivity")
public class MainActivity extends BaseMvpActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Fragment fragmentAndroid, fragmentBlog, fragmentKnowledge, fragmentNavigation;
    private Fragment currentFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setupToolbar_center(toolbar,"首页");

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
                break;
            case R.id.bottom_sort:
                switchContent(fragmentBlog);
                break;
            case R.id.bottom_cart:
                switchContent(fragmentKnowledge);
                break;
            case R.id.bottom_user:
                switchContent(fragmentNavigation);
                break;
            default:
                break;
        }
    }

    public void switchContent(Fragment to) {
        if (currentFragment != to) {
            FragmentTransaction transaction = fragmentManager.beginTransaction().setCustomAnimations(
                    android.R.anim.fade_in, android.R.anim.fade_out);
            if (!to.isAdded()) {
                transaction.hide(currentFragment).add(R.id.framelayout, to).commit();
            }else {
                transaction.hide(currentFragment).show(to).commit(); //提交
            }
            currentFragment = to;
        }
    }
}
