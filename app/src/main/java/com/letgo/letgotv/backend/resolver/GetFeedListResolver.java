package com.letgo.letgotv.backend.resolver;

import com.letgo.letgotv.R;
import com.letgo.letgotv.application.App;
import com.letgo.letgotv.backend.model.ResultEntity;
import com.letgo.letgotv.backend.response.ResultListResponse;
import com.letgo.letgotv.backend.util.CheckConnection;
import com.letgo.letgotv.backend.util.FailResponseException;
import com.letgo.letgotv.config.AppConstants;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Make the backend call to get the TV Shows Feed List
 */
public class GetFeedListResolver extends BaseResolver {

    /**
     * Flag to use Mock data or real one
     */
    boolean useMockData = false;

    /**
     * Main method
     *
     * @param page
     * @return
     */
    public Observable<ResultListResponse> makeCall(final int page) {

        return Observable.create(new Observable.OnSubscribe<ResultListResponse>() {

                 @Override
                 public void call(final Subscriber<? super ResultListResponse> subscriber) {

                     if (!useMockData) {

                         getDataFromBackend(subscriber, page);

                     }else{

                         getMockData(subscriber);
                     }

                 }
             }
        ).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Mock Backend call
     *
     * @param subscriber
     */
    private void getMockData(Subscriber<? super ResultListResponse> subscriber) {

        ResultListResponse response = new ResultListResponse();

        ArrayList<ResultEntity> list = new ArrayList<>();

        list.add(new ResultEntity(1, "Movie 1", "bla bla bla", "", 10));
        list.add(new ResultEntity(2, "Movie 2", "bla bla bla", "", 9.55f));
        list.add(new ResultEntity(3, "Movie 3", "bla bla bla", "", 1));

        response.setResult(list);

        subscriber.onNext(response);

        subscriber.onCompleted();
    }

    /**
     * Real Backend Call
     *
     * @param subscriber
     * @param page
     */
    private void getDataFromBackend(final Subscriber<? super ResultListResponse> subscriber, int page) {

        //Check Conection
        if (!CheckConnection.getInstance().isOnline()){

            Exception exception = new FailResponseException(0, App.applicationContext.getResources().
                    getString(R.string.network_error_fail), null);

            subscriber.onError(exception);

        }else {

            //Make the call
            client.getFeed(AppConstants.Backend.API_KEY, Locale.getDefault().getLanguage(), page).
                    enqueue(new Callback<ResultListResponse>() {

                    @Override
                    public void onResponse(Call<ResultListResponse> call, Response<ResultListResponse> response) {

                        //Get the Body
                        ResultListResponse responseBody = response.body();

                        try {

                            //If the Response is OK, call the Observer
                            if ((responseBody != null) && (responseBody.isResponseOk())) {

                                subscriber.onNext(responseBody);

                                subscriber.onCompleted();

                            } else {

                                //Else, create a Fail Response with the Code and Message
                                subscriber.onError(parseErrorBody(responseBody, response.errorBody()));
                            }

                        } catch (Exception e) {

                            subscriber.onError(e);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultListResponse> call, Throwable t) {

                        subscriber.onError(t);
                    }
                });
        }
    }
}