package com.letgo.letgotv.config;

import android.util.Pair;

/**
 * App Constans
 */
public class AppConstants {

    // App Tag: for logs
    public static final String APP_TAG = "LetgoTV";

    //Default Language Code
    public static final String LANGUAGE_CODE_ENGLISH = "es";

    //Backend Constants
    public static class Backend {

        //Base URL for Backend Calls
        public static final String BASE_URL = "https://api.themoviedb.org/3/";

        //Base URL for get Image Backend Calls
        public static final String BASE_IMAGE_URL= "https://image.tmdb.org/t/p/w1000";

        //API KEY to connect server
        public static final String API_KEY = "7e9dbab5638653e7600ffb597606e505";

        // Status
        public static final int STATUS_OK = 0;

        //Backend EndPoints
        public static class Endpoint {

            public static final String TOP_RATED_TV_SHOW = "tv/top_rated";
            public static final String SIMILAR_TV_SHOW = "tv/{showId}/similar";
        }
    }

    //UI Constants
    public static class UI {

        //Image Size to make the Resize
        public static class IntentParams {

            public static final String DETAIL_PARAM = "EXTRA_DETAIL";
        }

        //Image Size to make the Resize
        public static class ImageSize {

            public static final Pair<Integer, Integer> FEED_MAIN_IMAGE = new Pair<>(500, 750);
            public static final Pair<Integer, Integer> DETAIL_MAIN_IMAGE = new Pair<>(1000, 563);
        }
    }

}
