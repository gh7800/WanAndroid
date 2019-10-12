package cn.shineiot.base.module;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;

/**
 * Created by wangs on 2017/3/7.
 */

public abstract class BaseFragment extends Fragment {

	public abstract void initViews(View view);

	public abstract int getLayoutId();
	private View mFragmentView;
	public Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		Log.e(this.getClass().getName() , "onCreate");

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
//		BaseBus.getInstance().register(this);
		Log.e(this.getClass().getName() , "onResume");
		super.onResume();
	}


	@Override
	public void onPause() {
		super.onPause();
//		BaseBus.getInstance().unregister(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(this.getClass().getName() , "onDestroy");
	}

}
