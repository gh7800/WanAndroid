package cn.shineiot.android.androidfragment;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cn.shineiot.android.R;
import cn.shineiot.android.R2;
import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.module.BasePresenter;

/**
 * @author GF63
 */
@Route(path = "/android/androidFragment")
public class AndroidFragment extends BaseMvpFragment {
    @BindView(R2.id.textView)
    TextView textView;

    @Override
    public void initViews(View view) {
        textView.setOnClickListener(v -> {
            ARouter.getInstance().build("/activity/detailActivity").navigation();
        });
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
