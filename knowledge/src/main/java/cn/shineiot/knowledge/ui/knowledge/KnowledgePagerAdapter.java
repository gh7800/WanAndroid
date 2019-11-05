package cn.shineiot.knowledge.ui.knowledge;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GF63
 */
public class KnowledgePagerAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> fragmentList = new ArrayList<>();
	private List<String> tabNameList = new ArrayList<>();

	public KnowledgePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public void setData(List<Fragment> fragmentList,List<String> tabNameList) {
		this.fragmentList = fragmentList;
		this.tabNameList = tabNameList;
	}

	@Override
	public Fragment getItem(int i) {
		return fragmentList.get(i);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabNameList.get(position);
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		super.destroyItem(container, position, object);
	}
}
