package com.reizx.util.presenter.common;

import com.reizx.util.view.common.BaseView;

/**
 * 基础的presenter类
 * @param <T>
 */
public interface IBasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
