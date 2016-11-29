package com.letgo.letgotv.ui.fragment.presenter;

import com.letgo.letgotv.application.App;
import com.letgo.letgotv.business.model.ResultListModel;
import com.letgo.letgotv.business.model.ResultModel;
import com.letgo.letgotv.ui.fragment.view.FeedListView;
import com.letgo.letgotv.ui.model.DetailListItemDTO;
import com.letgo.letgotv.ui.model.FeedListItemDTO;
import com.letgo.letgotv.ui.util.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Feed Presenter
 */
public class FeedListPresenter extends BasePresenter {

    /**
     * Get the Feed List
     * 
     * @param page: page to get
     */
    public void doGetFeedList(int page) {

        addSubscription(App.bussinessManager.getFeedList(page).
                subscribe(createGetFeedListSubscriber()));
    }

    /**
     * Get Feed Return Subscriber
     * 
     * @return
     */
    private Subscriber<ResultListModel> createGetFeedListSubscriber() {

        return new Subscriber<ResultListModel>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable error) {

                view.showError(ErrorUtils.getErrorMsg(error));
            }

            @Override
            public void onNext(ResultListModel response) {

                //Convert the Business Object in UI Object 
                List<FeedListItemDTO> list = new ArrayList<>();

                if ((response != null) && (response.getList() != null)) {

                    for (ResultModel model : response.getList()) {

                        FeedListItemDTO dto = new FeedListItemDTO(model);

                        list.add(dto);
                    }
                }

                //Tell the view 
                if (view instanceof FeedListView)
                    ((FeedListView) view).onSuccessGetFeedList(list);
            }
        };
    }

    //Item Clicked on the view
    private FeedListItemDTO feedItem;

    /**
     * Get the Similar TV Shows
     *
     * @param page: page number
     * @param feedItem: item clicled
     */
    public void doGetDetailList(int page, FeedListItemDTO feedItem) {

        //save the selected item
        this.feedItem = feedItem;

        addSubscription(App.bussinessManager.getDetailList(page, feedItem.getId()).
                subscribe(createGetDetailListSubscriber()));
    }

    /**
     * Get Detail Return Subscriber
     *
     * @return
     */
    private Subscriber<ResultListModel> createGetDetailListSubscriber() {

        return new Subscriber<ResultListModel>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable error) {

                view.showError(ErrorUtils.getErrorMsg(error));
            }

            @Override
            public void onNext(ResultListModel response) {

                //Convert the Business Object in UI Object
                ArrayList<DetailListItemDTO> list = new ArrayList<>();

                //Add the Selected item as First element
                list.add(new DetailListItemDTO(feedItem));

                if ((response != null) && (response.getList() != null)) {

                    for (ResultModel model : response.getList()) {

                        DetailListItemDTO dto = new DetailListItemDTO(model);

                        list.add(dto);
                    }
                }

                //Tell the view
                if (view instanceof FeedListView)
                    ((FeedListView) view).onSuccessGetDetailList(list);
            }
        };
    }
}
