package com.letgo.letgotv.ui.fragment;

import android.support.v4.app.Fragment;

import com.letgo.letgotv.ui.fragment.presenter.BasePresenter;

/**
 * Base Fragment
 */
public class BaseFragment extends Fragment {

    protected BasePresenter presenter;

    @Override
    public void onResume() {

        super.onResume();

        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {

        super.onPause();

        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
