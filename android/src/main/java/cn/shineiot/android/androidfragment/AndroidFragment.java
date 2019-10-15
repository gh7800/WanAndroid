package cn.shineiot.android.androidfragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.shineiot.android.R;
import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.module.BasePresenter;

/**
 * @author GF63
 */
@Route(path = "/android/androidFragment")
public class AndroidFragment extends BaseMvpFragment {
    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
