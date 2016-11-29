package com.letgo.letgotv.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.letgo.letgotv.R;
import com.letgo.letgotv.config.AppConstants;
import com.letgo.letgotv.ui.adapter.pager_adapter.DetailPagerAdapter;
import com.letgo.letgotv.ui.model.DetailDTO;
import com.letgo.letgotv.ui.model.DetailListItemDTO;
import com.letgo.letgotv.ui.util.DepthPageTransformer;

import java.util.List;

/**
 * Detail Activity
 */
public class DetailActivity extends BaseActivity {

    //View Pager to show the TV Shows
    private ViewPager pager;

    //Adapter
    private PagerAdapter pagerAdapter;

    //List of TV Shows
    private List<DetailListItemDTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        getIntentData();

        //Config the Pager
        setupPager();

        setupToolbar();
    }

    private void getIntentData() {

        //Check if we have Extras
        if ((getIntent() != null) && (getIntent().hasExtra(AppConstants.UI.IntentParams.DETAIL_PARAM))) {

            //Get the Extra
            DetailDTO detailDTO = getIntent().getParcelableExtra(AppConstants.UI.IntentParams.DETAIL_PARAM);

            //Get the List inside the Detail DTO
            list = detailDTO.getList();
        }
    }

    private void setupPager() {

        //Get the Pager
        pager = (ViewPager) findViewById(R.id.activity_detail_pager);

        //Creat the Adapter (send the list)
        pagerAdapter = new DetailPagerAdapter(getSupportFragmentManager(), list);

        //Set the Adapter
        pager.setAdapter(pagerAdapter);

        //Set the Page Animation
        pager.setPageTransformer(true, new DepthPageTransformer());
    }

    private void setupToolbar() {

        //Obtengo la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_detail_action_bar);
        setSupportActionBar(toolbar);

        //Add back button to activity
        if (getSupportActionBar() != null) {

            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Manage te Bakckend. If whe are in +1 page, back to other page.
     * If whe are in first one, close the screen
     */
    @Override
    public void onBackPressed() {

        if (pager.getCurrentItem() == 0) {

            super.onBackPressed();

        } else {

            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    /**
     * Set the Home click (back arrow)
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                this.finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
