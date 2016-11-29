package com.letgo.letgotv.application;

import android.app.Application;
import android.content.Context;

import com.letgo.letgotv.business.BusinessManager;

/**
 * Created by marcos.buricchi on 08/11/2016.
 */

public class App  extends Application {

    public static BusinessManager bussinessManager;

    public static Context applicationContext;

    @Override
    public void onCreate() {

        super.onCreate();

        applicationContext = this;

        bussinessManager = new BusinessManager();
    }
}
