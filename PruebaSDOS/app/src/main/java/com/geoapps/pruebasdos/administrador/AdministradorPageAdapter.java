package com.geoapps.pruebasdos.administrador;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geovanni on 17/03/17.
 */

public class AdministradorPageAdapter extends FragmentPagerAdapter {

    final List<Fragment> fragments = new ArrayList<>();
    final List<String> fragmentsTitles = new ArrayList<>();

    public AdministradorPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentsTitles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
