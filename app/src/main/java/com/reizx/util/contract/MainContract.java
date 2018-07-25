package com.reizx.util.contract;

import com.reizx.util.presenter.common.IBasePresenter;
import com.reizx.util.view.common.BaseView;

public class MainContract {
    public interface View extends BaseView {

    }

    public interface Presenter extends IBasePresenter<View> {

    }
}
