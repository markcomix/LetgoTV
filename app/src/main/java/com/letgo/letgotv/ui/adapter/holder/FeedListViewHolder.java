package com.letgo.letgotv.ui.adapter.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.letgo.letgotv.R;

/**
 * Holder for the Feed List
 */
public class FeedListViewHolder extends RecyclerView.ViewHolder {

    //Card
    public CardView card;

    //Main Image (Poster)
    public ImageView mainImage;

    //Progress Bar to show during the Image Downloading
    public ProgressBar mainImageProgress;

    //Tv Show Title
    public TextView title;

    //TV Show Vote Average
    public TextView voteAverage;

    public FeedListViewHolder(View itemView) {

        super(itemView);

        card = (CardView) itemView.findViewById(R.id.list_item_feed_card);

        mainImage = (ImageView) itemView.findViewById(R.id.list_item_feed_main_image);
        mainImageProgress = (ProgressBar) itemView.findViewById(R.id.list_item_feed_main_image_progress);

        title = (TextView) itemView.findViewById(R.id.list_item_feed_title);

        voteAverage = (TextView) itemView.findViewById(R.id.list_item_feed_vote_average);
    }
}