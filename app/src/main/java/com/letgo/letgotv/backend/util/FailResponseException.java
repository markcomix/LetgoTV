package com.letgo.letgotv.backend.util;

import com.letgo.letgotv.backend.response.BaseResponse;

/**
 * Fail Response Exception: used as a response on any exception in backend layer
 */
public class FailResponseException extends Exception {

    private final int reason;

    private final BaseResponse response;

    public FailResponseException(int reason, String message, BaseResponse response) {

        super(message);

        this.reason = reason;
        this.response = response;
    }

    public int getReason() {
        return reason;
    }

    public BaseResponse getResponse() {
        return response;
    }
}