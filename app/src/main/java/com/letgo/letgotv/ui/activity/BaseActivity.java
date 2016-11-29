package com.letgo.letgotv.ui.activity;

import android.support.v7.app.AppCompatActivity;

import com.letgo.letgotv.ui.fragment.BaseFragment;

/**
 * Base Activity
 */
public class BaseActivity extends AppCompatActivity {

    public void replaceFragment(int containerId, BaseFragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }
}
