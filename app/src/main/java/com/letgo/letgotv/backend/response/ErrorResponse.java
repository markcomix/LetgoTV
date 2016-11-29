package com.letgo.letgotv.backend.response;

import com.letgo.letgotv.config.AppConstants;

/**
 * Created by marcos.buricchi on 16/11/2016.
 */

public class ErrorResponse {

    protected int status_code;

    protected String status_message;

    public ErrorResponse() {

        status_code = 0;
        status_message = "";
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public boolean isResponseOk() {

        return status_code == AppConstants.Backend.STATUS_OK;
    }
}
