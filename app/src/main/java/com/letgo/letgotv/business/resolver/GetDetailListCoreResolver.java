package com.letgo.letgotv.business.resolver;

import com.letgo.letgotv.R;
import com.letgo.letgotv.application.App;
import com.letgo.letgotv.backend.BackendManager;
import com.letgo.letgotv.backend.response.ResultListResponse;
import com.letgo.letgotv.business.model.ResultListModel;

import rx.Observable;
import rx.Subscriber;

/**
 * Get Detail Feed Bussiness Resolver
 */
public class GetDetailListCoreResolver extends BaseBusinessResolver {

    /**
     * Subscriber to cal when the Get Detail Feed List is resolved
     */
    private Subscriber<? super ResultListModel> subscriber;

    public GetDetailListCoreResolver(BackendManager backendManager) {

        super(backendManager);
    }

    public Observable<ResultListModel> getObservable(final int page, final int showId) {

        return Observable.create(new Observable.OnSubscribe<ResultListModel>() {

                 @Override
                 public void call(Subscriber<? super ResultListModel> subscriber) {

                     GetDetailListCoreResolver.this.subscriber = subscriber;

                     getFromBackend(page, showId);
                 }
             }
        );
    }

    /**
     * Call Backend "Facade"
     *
     * @param page
     */
    private void getFromBackend(int page, int showId) {

        backendManager.getDetailList(page, showId).subscribe(new Subscriber<ResultListResponse>() {

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

            message = App.applicationContext.getString(R.string.get_detail_generic_error_message);
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
