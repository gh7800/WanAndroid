package cn.shineiot.base.module;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * @author wangs on 2017/3/7.
 */

public abstract class BaseMvpFragment<V,T extends BasePresenter<V>> extends Fragment {

	public abstract int getLayoutId();
	public abstract void initViews(View view);
	public abstract T initPresenter();

	private View mFragmentView;

	public T presenter;
	public Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		initP();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mFragmentView == null) {
			mFragmentView = inflater.inflate(getLayoutId(), container, false);
			ButterKnife.bind(this, mFragmentView);
			initViews(mFragmentView);
		}
		return mFragmentView;
	}

	@Override
	public void onResume() {
		resumeP();
		super.onResume();
	}


	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		detachP();
		super.onDestroy();
	}

	private void initP() {
		presenter = initPresenter();
		if (presenter != null){
			presenter.attachView((V) this);
		}

	}

	private void resumeP() {
		if (presenter != null){
			presenter.attachView((V) this);
		}

	}

	private void detachP() {
		if (presenter != null){
			presenter.detachView();
			presenter = null;
		}

	}

}
