package cn.shineiot.compontentpro.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhy.changeskin.SkinManager;

import butterknife.BindView;
import cn.shineiot.base.R2;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.compontentpro.R;

/**
 * @author GF63
 */
@Route(path = "/activity/detailActivity")
public class DetailActivity extends BaseMvpActivity {
    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    boolean isSkin = false;

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setupToolbar_center(toolbar,"换肤");
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    public void skinClick(View view) {
        if (isSkin) {
            isSkin = false;
            SkinManager.getInstance().changeSkin("light");
        } else {
            isSkin = true;
            SkinManager.getInstance().changeSkin("night");
        }
    }
}
