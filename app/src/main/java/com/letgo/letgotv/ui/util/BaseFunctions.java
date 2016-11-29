package com.letgo.letgotv.ui.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letgo.letgotv.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * UI Base Functions
 */
public class BaseFunctions {

    private static ObjectMapper mapper;

    static {

        mapper = new ObjectMapper();

        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        DateFormat WS_DATE_FORMAT = new SimpleDateFormat("MMM dd, yyyy h:mm:ss aa", Locale.ENGLISH);

        mapper.setDateFormat(WS_DATE_FORMAT);

    }

    public static ObjectMapper getMapper() {

        return mapper;
    }

    public static void displaySnackbar(View view, String message) {

        displaySnackbar(view, message, null, null);
    }

    /**
     * Diplay a SnackBar
     *
     * @param view
     * @param message
     * @param actionText
     * @param onClickListener
     */
    public static void displaySnackbar(View view, String message, String actionText,
                                       View.OnClickListener onClickListener) {

        if (view == null)
            return;

        Context context = view.getContext();

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        View viewSnackbar = snackbar.getView();

        TextView textSnackbar = (TextView) viewSnackbar.findViewById(android.support.design.R.id.snackbar_text);

        textSnackbar.setTextColor(ContextCompat.getColor(context, android.R.color.white));

        if (actionText != null && onClickListener != null) {

            snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.yellow));

            snackbar.setAction(actionText, onClickListener);

            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }

        snackbar.show();
    }
}
