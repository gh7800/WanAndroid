package cn.shineiot.compontentpro.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    @BindView(R.id.textview)
    TextView tv;

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tv.setText("登录");
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick(R.id.textview)
    public void login(View view){
        ARouter.getInstance().build("/login/loginActivity").navigation();
    }
}
