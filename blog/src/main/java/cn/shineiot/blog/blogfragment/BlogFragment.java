package cn.shineiot.blog.blogfragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.shineiot.base.module.BaseMvpFragment;
import cn.shineiot.base.module.BasePresenter;
import cn.shineiot.blog.R;

/**
 * @author GF63
 */
@Route(path = "/fragment/blogFragment")
public class BlogFragment extends BaseMvpFragment {
    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_blog;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
