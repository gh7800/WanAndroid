package cn.shineiot.base.module;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import cn.shineiot.base.R;
import cn.shineiot.base.manager.AppManager;


/**
 * @author GF63
 */
public abstract class BaseActivity extends AppCompatActivity {


    protected abstract int provideLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    public Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
//        BaseBus.getInstance().register(this);
        mContext = this;
        initView(savedInstanceState);


    }


    @Override
    protected void onResume() {
        Log.e(this.getClass().getName() , "Resumebus");

        super.onResume();
    }

    public void setupToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        setCenterTitleBar(actionBar,toolbar);
        setTitle(title);
    }

    public void setupToolbar(Toolbar toolbar, int title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        setCenterTitleBar(actionBar,toolbar);
        setTitle(title);
    }

    private void setCenterTitleBar(ActionBar actionBar, Toolbar toolbar){
        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationIcon(R.drawable.icon_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(this.getClass().getName() , "Pausebus");

    }


    @Override
    protected void onDestroy() {
        Log.e(this.getClass().getName() , "Destroy");
        AppManager.getAppManager().finishActivity(this);
//        BaseBus.getInstance().unregister(this);
        super.onDestroy();
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

}
