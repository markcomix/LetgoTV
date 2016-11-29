package com.letgo.letgotv.business.resolver;

import com.letgo.letgotv.R;
import com.letgo.letgotv.application.App;
import com.letgo.letgotv.backend.BackendManager;
import com.letgo.letgotv.backend.response.ResultListResponse;
import com.letgo.letgotv.business.model.ResultListModel;

import rx.Observable;
import rx.Subscriber;

/**
 * Get Feed Bussiness Resolver
 */
public class GetFeedListBusinessResolver extends BaseBusinessResolver {

    /**
     * Subscriber to cal when the Get Feed List is resolved
     */
    private Subscriber<? super ResultListModel> subscriber;

    public GetFeedListBusinessResolver(BackendManager backendManager) {

        super(backendManager);
    }

    public Observable<ResultListModel> getObservable(final int page) {

        return Observable.create(new Observable.OnSubscribe<ResultListModel>() {

                 @Override
                 public void call(Subscriber<? super ResultListModel> subscriber) {

                     GetFeedListBusinessResolver.this.subscriber = subscriber;

                     getFromBackend(page);
                 }
             }
        );
    }

    /**
     * Call Backend "Facade"
     *
     * @param page
     */
    private void getFromBackend(int page) {

        backendManager.getFeedList(page).subscribe(new Subscriber<ResultListResponse>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable error) {

                onFailureGetFromBackend(error);
            }

            @Override
            public void onNext(ResultListResponse response) {

                onSuccessGetFromBackend(response);
            }
        });
    }

    /**
     * Return a generic error message
     */
    private void onFailureGetFromBackend(Throwable error) {

        String message;

        if (error != null){

            message = error.getMessage();

        }else {

            message = App.applicationContext.getString(R.string.get_feed_generic_error_message);
        }

        subscriber.onError(new Exception(message));

    }

    /**
     * Return the data. Also make the conversion from Entity Obj to Business Object
     * @param response
     */
    private void onSuccessGetFromBackend(ResultListResponse response) {

        ResultListModel listModel = null;

        if (response != null)
            listModel = new ResultListModel(response.getResult());

        subscriber.onNext(listModel);

        subscriber.onCompleted();
    }

}
