package com.letgo.letgotv.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import com.letgo.letgotv.R;
import com.letgo.letgotv.ui.fragment.FeedListFragment;
import com.letgo.letgotv.ui.util.BaseFunctions;

/**
 * Feed List Activity
 */
public class FeedListActivity extends BaseActivity {

    //flag to check if we have to close the App
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_feed);

        replaceFragment(R.id.activity_list_feed_frame_container,
                FeedListFragment.newInstance());
    }

    /**
     * Closes the Application if the user taps twice on the Back button.
     */
    @Override
    public void onBackPressed() {

        if (exit) {

            this.finish();

        } else {

            BaseFunctions.displaySnackbar(findViewById(android.R.id.content), getString(R.string.exit_app));

            exit = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }
}
