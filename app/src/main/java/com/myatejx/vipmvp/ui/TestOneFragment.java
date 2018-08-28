package com.myatejx.vipmvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myatejx.architecture.base.BaseFragment;
import com.myatejx.architecture.business.bus.Result;
import com.myatejx.vipmvp.R;
import com.myatejx.vipmvp.business.TestBus;
import com.myatejx.vipmvp.constant.TestResultCode;
import com.myatejx.vipmvp.databinding.FragmentTestOneBinding;

/**
 * @author KunMinX
 * @date 2018/8/21
 */
public class TestOneFragment extends BaseFragment {

    private FragmentTestOneBinding mBinding;

    public static TestOneFragment newInstance() {
        TestOneFragment fragment = new TestOneFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_one, container, false);
        mBinding = FragmentTestOneBinding.bind(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        TestBus.requestBean();
    }

    @Override
    public void onResult(Result testResult) {
        int resultCode = testResult.getResultCode();
        switch (resultCode) {
            case TestResultCode.FAILURE:
                break;
            /*case BaseResultCode.CANCEL:
                break;*/
            default:
        }
    }
}
