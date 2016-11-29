package com.letgo.letgotv.ui.adapter.recycler_view_adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.letgo.letgotv.R;
import com.letgo.letgotv.config.AppConstants;
import com.letgo.letgotv.ui.adapter.holder.FeedListViewHolder;
import com.letgo.letgotv.ui.interfaces.IOnItemSelectedListener;
import com.letgo.letgotv.ui.model.FeedListItemDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Feed Adapter
 */
public class FeedListAdapter extends RecyclerView.Adapter<FeedListViewHolder> {

    //Interface to get the Item Click
    private IOnItemSelectedListener listener;

    //List of Items
    private List<FeedListItemDTO> list;

    public FeedListAdapter(List<FeedListItemDTO> list, IOnItemSelectedListener listener) {

        this.list = list;
        this.listener = listener;
    }

    @Override
    public FeedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_feed, parent, false);

        return new FeedListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FeedListViewHolder holder, final int position) {

        //Get the Item
        final FeedListItemDTO item = list.get(position);

        if (item != null) {

            //Check if we have a valid Image URL
            if (!TextUtils.isEmpty(item.getImageUrl())){

                //Get the Image
                Picasso.with(holder.mainImage.getContext())
                        .load(item.getImageUrl())
                        .noFade()
                        .resize(AppConstants.UI.ImageSize.FEED_MAIN_IMAGE.first,
                                AppConstants.UI.ImageSize.FEED_MAIN_IMAGE.second)
                        .placeholder(R.drawable.placeholder_movie)
                        .into(holder.mainImage, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {

                                //Hide the Progress Bar
                                holder.mainImageProgress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError() {

                                //Hide the Progress Bar
                                holder.mainImageProgress.setVisibility(View.GONE);
                            }
                        });
            }else {

                holder.mainImage.setImageDrawable(holder.mainImage.
                        getResources().getDrawable(R.drawable.placeholder_movie));

                //Hide the Progress Bar
                holder.mainImageProgress.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(item.getTitle()))
                holder.title.setText(item.getTitle());
            else
                holder.title.setText("");

            if (!TextUtils.isEmpty(item.getVoteAverage()))
                holder.voteAverage.setText(item.getVoteAverage());
            else
                holder.voteAverage.setText("");

            //manage the item Click
            holder.card.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    onCardClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }

    /**
     * Replace the list
     *
     * @param list
     */
    public void updateList(List<FeedListItemDTO> list){

        int count = this.list.size();

        this.list = list;

        this.notifyItemRangeRemoved(0, count);

        this.notifyItemRangeInserted(0, this.list.size());
    }

    /**
     * Add more data to the same list
     *
     * @param list
     */
    public void addToList(List<FeedListItemDTO> list) {

        if (list == null)
            return;

        int positionForNewItems = this.list.size();

        this.list.addAll(list);

        this.notifyItemRangeInserted(positionForNewItems, list.size());
    }

    /**
     * Manage the Click
     *
     * @param item
     */
    private void onCardClick(Object item) {

        if (listener != null){

            listener.onItemSelected(item);
        }
    }
}
