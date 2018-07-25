package com.reizx.util.contract;

import android.support.v4.app.Fragment;

import com.reizx.util.presenter.common.IBasePresenter;
import com.reizx.util.view.common.BaseView;

public class MainActivityContract {
    public interface View extends BaseView {
        /**
         * 切换主fragment
         * @param fragment
         */
        void startFragment(Fragment fragment);
    }

    public interface  Presenter extends IBasePresenter<View> {
        void checkVersion(String currentVersion);
    }
}
