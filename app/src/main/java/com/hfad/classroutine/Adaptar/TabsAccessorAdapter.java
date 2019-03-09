package com.hfad.classroutine.Adaptar;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hfad.classroutine.Fragments.MondayFragment;
import com.hfad.classroutine.Fragments.SaturdayFragment;
import com.hfad.classroutine.Fragments.SundayFragment;
import com.hfad.classroutine.Fragments.ThursdayFragment;
import com.hfad.classroutine.Fragments.TuesdayFragment;
import com.hfad.classroutine.Fragments.WednesdayFragment;

public class TabsAccessorAdapter extends FragmentPagerAdapter {

    public TabsAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SaturdayFragment Saturday = new SaturdayFragment();
                return Saturday;

            case 1:
                SundayFragment Sunday = new SundayFragment();
                return Sunday;

            case 2:
                MondayFragment Monday = new MondayFragment();
                return Monday;

            case 3:
                TuesdayFragment Tuesday = new TuesdayFragment();
                return Tuesday;

            case 4:
                WednesdayFragment Wednesday = new WednesdayFragment();
                return Wednesday;

            case 5:
                ThursdayFragment Thursday = new ThursdayFragment();
                return Thursday;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

            case 0:
                return "SAT";

            case 1:
                return "SUN";

            case 2:
                return "MON";

            case 3:
                return "TUE";

            case 4:
                return "WED";

            case 5:
                return "THU";

            default:
                return null;
        }
    }
}
