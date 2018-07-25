package com.reizx.util.bean.event;

import android.support.v4.app.Fragment;

public class ChangeMainFragmentEvent {
    Fragment fragment;

    public ChangeMainFragmentEvent(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
