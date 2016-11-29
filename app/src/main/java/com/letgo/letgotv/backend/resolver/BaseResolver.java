package com.letgo.letgotv.backend.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letgo.letgotv.backend.ApiService;
import com.letgo.letgotv.backend.response.BaseResponse;
import com.letgo.letgotv.backend.response.ErrorResponse;
import com.letgo.letgotv.backend.util.FailResponseException;
import com.letgo.letgotv.backend.util.RestClient;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Base Resolver Class: all Backend resolver must extend this
 */
public class BaseResolver {

    protected ApiService client = null;

    /**
     * Create the Rest Client
     */
    public BaseResolver() {

        this.client = RestClient.INSTANCE.getApiService();
    }


    protected FailResponseException parseErrorBody(BaseResponse response,
                                                   ResponseBody responseBody) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        ErrorResponse errorResponse = mapper.readValue(responseBody.string(),
                ErrorResponse.class);

        return new FailResponseException(errorResponse.getStatus_code(),
                errorResponse.getStatus_message(), response);
    }
}