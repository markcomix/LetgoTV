package com.letgo.letgotv.backend.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letgo.letgotv.config.AppConstants;

/**
 * Base Response with the Status Code and Message
 */
public class BaseResponse {

    @JsonProperty("status_code")
    protected int status_code;

    @JsonProperty("status_message")
    protected String status_message;

    public BaseResponse() {

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
