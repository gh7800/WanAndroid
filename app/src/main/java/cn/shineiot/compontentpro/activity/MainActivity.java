package cn.shineiot.compontentpro.activity;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 */
@Route(path = "/app/mainActivity")
public class MainActivity extends BaseMvpActivity {

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

}
