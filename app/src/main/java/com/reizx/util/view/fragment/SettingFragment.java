package com.reizx.util.view.fragment;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.andrutil.log.LogcatAppenderBuilder;
import com.reizx.andrutil.log.LoggerConfigeration;
import com.reizx.util.R;
import com.reizx.util.contract.SettingContract;
import com.reizx.util.presenter.SettingPresenter;
import com.reizx.util.util.AsfMgrLog;
import com.reizx.util.util.RxUtil;
import com.reizx.util.view.MainActivity;
import com.reizx.util.view.common.BaseFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.android.LogcatAppender;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingContract.View {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_setting_page_show_ip_des)
    TextView tvIpStatus;
    Disposable ds;

    @SuppressLint("CheckResult")
    @OnClick(R.id.btn_setting_page_test)
    public void clickTest(){
        if (ds != null && !ds.isDisposed()){
            AsfMgrLog.d("dispose the subscribe...");
            ds.dispose();
            ds = null;
            return;
        }

        AsfMgrLog.d("click setting page test");
        ds = Flowable.interval(1,  TimeUnit.SECONDS)
                .onBackpressureDrop()
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        AsfMgrLog.d("the inter ... " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AsfMgrLog.d("flow err : " + throwable);
                    }
                });
    }

    @OnClick(R.id.btn_setting_page_logback)
    public void logback(){
        try {
            LogcatAppender logcatAppender = LogcatAppenderBuilder
                    .newBuilder()
                    .name("asf")
                    .build();

            LoggerConfigeration.newConfigeration().addAppend(logcatAppender).config();
        } catch (Exception e) {
            //e.printStackTrace();
        }

        Logger logger = LoggerFactory.getLogger("asf");

        /*以下日志只会在控制台输出*/
        logger.trace("angcyo-->{}", "trace");
        logger.debug("angcyo-->{}", "debug");
        logger.info("angcyo-->{}", "info");
        logger.warn("angcyo-->{}", "warn");
        logger.error("angcyo-->{}", "error");

//        Logger logtest = LoggerFactory.getLogger("baseLog");
//        /*以下日志会在BASE_ROLL_FILE声明的文件中输出,并且也会在控制台输出*/
//        logtest.trace("logtest-->{}", "trace");
//        logtest.debug("logtest-->{}", "debug");
//        logtest.info("logtest-->{}", "info");
//        logtest.warn("logtest-->{}", "warn");
//        logtest.error("logtest-->{}", "error");
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_setting;
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

    public void initTopBar() {
        mTopBar.setTitle("设置");
    }

    @Override
    public void showIpStatus(String msg) {
        tvIpStatus.setText(msg);
    }
}
