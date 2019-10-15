package cn.shineiot.navigation.navigationfragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.navigation.R;

/**
 * @author GF63
 */
@Route(path = "/navigation/navigationFragment")
public class NavigationFragment extends BaseMvpFragment {
    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
