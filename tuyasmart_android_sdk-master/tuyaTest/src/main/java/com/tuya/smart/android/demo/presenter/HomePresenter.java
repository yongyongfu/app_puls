package com.tuya.smart.android.demo.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;

import com.tuya.smart.android.demo.R;
import com.tuya.smart.android.demo.activity.AddDeviceTipActivity;
import com.tuya.smart.android.demo.fragment.DeviceListFragment;
import com.tuya.smart.android.demo.fragment.PersonalCenterFragment;
import com.tuya.smart.android.demo.test.utils.DialogUtil;
import com.tuya.smart.android.demo.utils.ActivityUtils;
import com.tuya.smart.android.demo.view.IHomeView;
import com.tuya.smart.android.mvp.presenter.BasePresenter;

/**
 * Created by letian on 16/7/18.
 */
public class HomePresenter extends BasePresenter {

    public static final String TAG = "HomePresenter";

    public static final String TAB_FRGMENT = "TAB_FRGMENT";

    private IHomeView mHomeView;
    protected Activity mActivity;

    public static final int TAB_MY_DEVICE = 0;
    public static final int TAB_PERSONAL_CENTER = 1;

    protected int mCurrentTab = -1;

    public HomePresenter(IHomeView homeView, Activity ctx) {
        mHomeView = homeView;
        mActivity = ctx;
    }


    //添加设备
    public void addDevice() {
        final WifiManager mWifiManager = (WifiManager) mActivity.getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()) {
            DialogUtil.simpleConfirmDialog(mActivity, mActivity.getString(R.string.open_wifi), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            mWifiManager.setWifiEnabled(true);
                            gotoAddDevice();
                            break;
                    }
                }
            });
        } else {
            gotoAddDevice();
        }
    }

    //个人中心
    public void showPersonalCenterPage() {
        showTab(TAB_PERSONAL_CENTER);
    }

    //我的设备
    public void showMyDevicePage() {
        showTab(TAB_MY_DEVICE);
    }

    public void gotoAddDevice() {
        ActivityUtils.gotoActivity(mActivity, AddDeviceTipActivity.class, ActivityUtils.ANIMATE_SLIDE_TOP_FROM_BOTTOM, false);

    }

    public void showTab(int tab) {
        if (tab == mCurrentTab) {
            return;
        }

        mHomeView.offItem(mCurrentTab);

        mHomeView.onItem(tab);

        mCurrentTab = tab;
    }

    public int getFragmentCount() {
        return 2;
    }

    public Fragment getFragment(int type) {
        if (type == TAB_MY_DEVICE) {
            return DeviceListFragment.newInstance();
        } else if (type == TAB_PERSONAL_CENTER) {
            return PersonalCenterFragment.newInstance();
        }
        return null;
    }

    public int getCurrentTab() {
        return mCurrentTab;
    }

    public void setCurrentTab(int tab) {
        mCurrentTab = tab;
    }
}
