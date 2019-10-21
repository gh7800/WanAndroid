package cn.shineiot.login.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.maning.mndialoglibrary.MToast;

import butterknife.BindView;
import cn.shineiot.base.manager.AppManager;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.utils.Constants;
import cn.shineiot.base.utils.LoadingDialog;
import cn.shineiot.base.utils.SPUtils;
import cn.shineiot.base.utils.ToastUtils;
import cn.shineiot.login.R;
import cn.shineiot.login.R2;
import cn.shineiot.login.bean.User;

/**
 * @author GF63
 */
@Route(path = "/login/loginActivity")
public class LoginActivity extends BaseMvpActivity<LoginView, LoginPresenter> implements LoginView {
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

    public void login(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        presenter.login(username, password);
    }

    @Override
    public void SuccessData(User user) {
//        ToastUtils.showToast(mContext,"登录成功");
        etPassword.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
//                MToast.makeTextShort(mContext, "登录成功");
            }
        }, 1000);
        SPUtils.put(mContext, Constants.USERNAME, user.getUsername());
        setResult(Activity.RESULT_OK);
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public void showLoading(String msg) {
        LoadingDialog.showDialog(mContext, msg);
    }

    @Override
    public void hideLoading() {
        LoadingDialog.hideDialog();
    }

    @Override
    public void showError(String msg) {
        MToast.makeTextShort(mContext, "msg");
    }
}
