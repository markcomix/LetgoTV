package com.letgo.letgotv.business;

import com.letgo.letgotv.backend.BackendManager;
import com.letgo.letgotv.business.model.ResultListModel;
import com.letgo.letgotv.business.resolver.GetDetailListCoreResolver;
import com.letgo.letgotv.business.resolver.GetFeedListBusinessResolver;

import rx.Observable;

/**
 * "Facade" Class to access Bussiness Services
 */
public class BusinessManager {

    private BackendManager backendManager;

    public BusinessManager() {

        this.backendManager = new BackendManager();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// GET FEED //////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Get the Feed of TV Shows
     *
     * @param page: number of page to get
     *
     * @return
     */
    public Observable<ResultListModel> getFeedList(int page) {

        GetFeedListBusinessResolver coreResolver = new GetFeedListBusinessResolver(backendManager);

        return coreResolver.getObservable(page);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// GET DETAIL //////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Get the Feed of Similar TV Shows
     *
     * @param page: number of page to get
     * @param showId: tv show
     *
     * @return
     */
    public Observable<ResultListModel> getDetailList(int page, int showId) {

        GetDetailListCoreResolver coreResolver = new GetDetailListCoreResolver(backendManager);

        return coreResolver.getObservable(page, showId);
    }
}