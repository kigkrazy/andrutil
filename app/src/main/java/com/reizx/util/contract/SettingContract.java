package com.reizx.util.contract;

import com.reizx.util.presenter.common.IBasePresenter;
import com.reizx.util.view.common.BaseView;

public class SettingContract {
    public interface View extends BaseView {
        void showIpStatus(String msg);
    }

    public interface  Presenter extends IBasePresenter<View> {
    }
}
