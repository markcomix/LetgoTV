package com.letgo.letgotv.backend;

import com.letgo.letgotv.backend.resolver.GetDetailListResolver;
import com.letgo.letgotv.backend.resolver.GetFeedListResolver;
import com.letgo.letgotv.backend.response.ResultListResponse;

import rx.Observable;

/**
 * "Facade" Class to access Backend Services
 */
public class BackendManager {

    public BackendManager() {

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////// GET FEED ///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Get the Feed of TV Shows from the backend
     *
     * @param page: number of page to get
     *
     * @return
     */
    public Observable<ResultListResponse> getFeedList(int page) {

        GetFeedListResolver backendResolver = new GetFeedListResolver();

        return backendResolver.makeCall(page);

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////// GET DETAIL ///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Get the Feed of Similar TV Shows from another
     *
     * @param page: number of page to get
     * @param showId: tv show
     *
     * @return
     */
    public Observable<ResultListResponse> getDetailList(int page, int showId) {

        GetDetailListResolver backendResolver = new GetDetailListResolver();

        return backendResolver.makeCall(page, showId);

    }
}