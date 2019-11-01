package cn.shineiot.base.ui;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cn.shineiot.base.ARouterPath;
import cn.shineiot.base.R;
import cn.shineiot.base.R2;
import cn.shineiot.base.manager.AppManager;
import cn.shineiot.base.module.BaseMvpActivity;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.base.utils.Constants;
import cn.shineiot.base.utils.DensityUtils;
import cn.shineiot.base.utils.SPUtils;
import cn.shineiot.base.view.FontSizeView;


/**
 * @author GF63
 * 全局设置字体大小
 * 需要在 MainActivity重写 getResource()
 */
@Route(path = ARouterPath.FONT_SIZE_ACTIVITY)
public class FontSizeActivity extends BaseMvpActivity {
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.fsv_font_size)
    FontSizeView fontSizeView;
    @BindView(R2.id.tv_font_size1)
    TextView tv_font_size1;
    @BindView(R2.id.tv_font_size2)
    TextView tv_font_size2;
    @BindView(R2.id.tv_font_size3)
    TextView tv_font_size3;


    private boolean isChange = false;
    private float fontSizeScale;
    private int defaultPos;



    @Override
    protected int provideLayoutId() {
        return R.layout.activity_fontsize;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setupToolbar_center(toolbar,"字体大小");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFontSize();
            }
        });
        fontSizeView.setChangeCallbackListener(new FontSizeView.OnChangeCallbackListener() {
            @Override
            public void onChangeListener(int position) {
                int dimension = getResources().getDimensionPixelSize(R.dimen.sp_stander);
                //根据position 获取字体倍数
                fontSizeScale = (float) (0.875 + 0.125 * position);
                //放大后的sp单位
                double v = fontSizeScale * (int) DensityUtils.px2sp(FontSizeActivity.this, dimension);
                //改变当前页面大小
                changeTextSize((int) v);
                isChange = !(position == defaultPos);

            }
        });

        float  scale = (float) SPUtils.get(this, Constants.SP_FontScale, 0.0f);
        if (scale > 0.5) {
            defaultPos = (int) ((scale - 0.875) / 0.125);
        } else {
            defaultPos=1;
        }
        //注意： 写在改变监听下面 —— 否则初始字体不会改变
        fontSizeView.setDefaultPosition(defaultPos);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    /**
     * 改变textsize 大小
     */
    private void changeTextSize(int dimension) {
        tv_font_size1.setTextSize(dimension);
        tv_font_size2.setTextSize(dimension);
        tv_font_size3.setTextSize(dimension);
    }

    /**
     * 重新配置缩放系数
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res =super.getResources();
        Configuration config = res.getConfiguration();
        config.fontScale= 1;//1 设置正常字体大小的倍数
        res.updateConfiguration(config,res.getDisplayMetrics());
//        mContext.createConfigurationContext(config);
        return res;
    }

    /**
     * 修改字体大小
     */
    public void changeFontSize(){
        if(isChange){
            SPUtils.put(FontSizeActivity.this, Constants.SP_FontScale,fontSizeScale);
            //重启应用
            AppManager.getAppManager().finishAllActivityAndExit();
            ARouter.getInstance().build(ARouterPath.MAIN_ACTIVITY).navigation();
        }else{
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        changeFontSize();
        return true;
    }
}
