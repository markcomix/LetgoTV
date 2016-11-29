package com.letgo.letgotv.backend;

import com.letgo.letgotv.backend.response.ResultListResponse;
import com.letgo.letgotv.config.AppConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface with all the Api Services
 */
public interface ApiService {

    @GET(AppConstants.Backend.Endpoint.TOP_RATED_TV_SHOW)
    Call<ResultListResponse> getFeed(
        @Query("api_key") String apiKey,
        @Query("language") String language,
        @Query("page") long page
    );

    @GET(AppConstants.Backend.Endpoint.SIMILAR_TV_SHOW)
    Call<ResultListResponse> getDetail(
        @Path("showId") String showId,
        @Query("api_key") String apiKey,
        @Query("language") String language,
        @Query("page") long page
    );
}
