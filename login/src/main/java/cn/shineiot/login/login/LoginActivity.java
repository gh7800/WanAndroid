package cn.shineiot.login.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.login.R;
import cn.shineiot.login.R2;

/**
 * @author GF63
 */
@Route(path = "/login/loginActivity")
public class LoginActivity extends BaseMvpActivity<LoginView,LoginPresenter> implements LoginView {
    @BindView(R2.id.username)
    EditText etUsername;
    @BindView(R2.id.password)
    EditText etPassword;

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        etUsername.setText("请输入用户名");
        etPassword.setText("pw100861");
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    public void login(View view){

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }
}
