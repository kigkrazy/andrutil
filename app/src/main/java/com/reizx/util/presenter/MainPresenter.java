package com.reizx.util.presenter;

import com.reizx.util.contract.MainContract;
import com.reizx.util.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter() {
    }
}
