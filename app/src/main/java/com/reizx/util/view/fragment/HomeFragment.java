package com.reizx.util.view.fragment;

import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.util.R;
import com.reizx.util.contract.HomeConstract;
import com.reizx.util.presenter.HomePresenter;
import com.reizx.util.view.common.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeConstract.View{
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_app_show_ip_des)
    TextView tvIp;

    @OnClick(R.id.btn_app_start_service)
    public void startZkService(){
        presenter.startZkService(baseActivity);
    }

    @OnClick(R.id.btn_app_stop_service)
    public void stopZkService(){
        presenter.stopZkService(baseActivity);
    }

    @OnClick(R.id.btn_app_request_ip)
    public void requestIp(){
        presenter.showCurrentIp();
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initAllMembersView() {
        super.initAllMembersView();
        initTopBar();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().Inject(this);
    }

    public void initTopBar(){
        mTopBar.setTitle("主页");
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    @Override
    public void setCurrentIp(String ip) {
        tvIp.setText(ip);
    }
}
