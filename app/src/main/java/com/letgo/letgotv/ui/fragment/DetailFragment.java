package com.letgo.letgotv.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.letgo.letgotv.R;
import com.letgo.letgotv.config.AppConstants;
import com.letgo.letgotv.ui.model.DetailListItemDTO;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Detail Fragment
 */
public class DetailFragment extends BaseFragment {

    //List Item DTO
    private DetailListItemDTO item;

    //Fist Air Date
    @Bind(R.id.fragment_detail_first_air_date)
    public TextView fistAirDate;

    //Title
    @Bind(R.id.fragment_detail_title)
    public TextView title;

    //Main Image
    @Bind(R.id.fragment_detail_main_image)
    public ImageView mainImage;

    //Main Image Progress Bar
    @Bind(R.id.fragment_detail_main_image_progress)
    public ProgressBar mainImageProgress;

    //Detail Text
    @Bind(R.id.fragment_detail_overview)
    public TextView overview;

    //Vote Average Text
    @Bind(R.id.fragment_detail_vote_average)
    public TextView voteAverage;

    public static DetailFragment newInstance(DetailListItemDTO item) {

        DetailFragment fragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putParcelable(AppConstants.UI.IntentParams.DETAIL_PARAM, item);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.item = getArguments().getParcelable(AppConstants.UI.IntentParams.DETAIL_PARAM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        setupView();
    }

    private void setupView() {

        //Set Fist Air Date
        if (!TextUtils.isEmpty(item.getFirstAirDate()))
            fistAirDate.setText(item.getFirstAirDate());
        else
            fistAirDate.setText("");

        //Set Title
        if (!TextUtils.isEmpty(item.getTitle()))
            title.setText(item.getTitle());
        else
            title.setText("");

        //Check if we have a valid Image URL
        if (!TextUtils.isEmpty(item.getImageUrl())){

            //Get the Image
            Picasso.with(getContext())
                    .load(item.getImageUrl())
                    .noFade()
                    .resize(AppConstants.UI.ImageSize.DETAIL_MAIN_IMAGE.first,
                            AppConstants.UI.ImageSize.DETAIL_MAIN_IMAGE.second)
                    .placeholder(R.drawable.placeholder_movie)
                    .into(mainImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                            mainImageProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                            mainImageProgress.setVisibility(View.GONE);
                        }
                    });
        }else{
            mainImage.setImageDrawable(ContextCompat.getDrawable(getActivity(),
                    R.drawable.placeholder_movie));

            mainImageProgress.setVisibility(View.GONE);
        }

        //Set Overview
        if (!TextUtils.isEmpty(item.getOverview()))
            overview.setText(item.getOverview());
        else
            overview.setText("");

        //Set Vote Average
        if (!TextUtils.isEmpty(item.getVoteAverage()))
            voteAverage.setText(item.getVoteAverage());
        else
            voteAverage.setText("");
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        ButterKnife.unbind(this);
    }
}
