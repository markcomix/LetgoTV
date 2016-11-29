package com.letgo.letgotv.ui.util;

import com.letgo.letgotv.R;
import com.letgo.letgotv.application.App;

public class ErrorUtils {

    public static String getErrorMsg(Throwable e) {

        String errorMessage;

        if (e instanceof Exception) {

            errorMessage = e.getMessage();

        } else {

            errorMessage = App.applicationContext.getString(R.string.default_error);
        }

        return errorMessage;

    }

}

