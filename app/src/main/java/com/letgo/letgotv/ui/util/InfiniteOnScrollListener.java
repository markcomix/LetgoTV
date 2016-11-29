package com.letgo.letgotv.ui.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Class to manage the Infinite Scroll
 */
public abstract class InfiniteOnScrollListener extends RecyclerView.OnScrollListener {

    // true if we are still waiting for the last set of data to load.
    private boolean loading = false;

    // the current page being displayed/loaded
    private static int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    // if there is no more data to load, we stop fetching from the server
    private boolean isLimitReached = false;

    public InfiniteOnScrollListener(LinearLayoutManager linearLayoutManager) {

        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        super.onScrolled(recyclerView, dx, dy);

        if (mLinearLayoutManager == null)
            return;

        // first and last visible items of the list
        int lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();

        if (isLimitReached)
            return;

        // when the user reaches the bottom of the list, we fetch more data
        if (!loading && lastVisibleItem == recyclerView.getAdapter().getItemCount() - 1) {

            // load next page
            current_page++;

            onLoadMore(current_page);
        }
    }

    /**
     * Start loading from the first page again.
     */
    public void reset() {

        this.loading = true;

        current_page = 1;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setIsLimitReached(boolean isLimitReached) {
        this.isLimitReached = isLimitReached;
    }

    public abstract void onLoadMore(int current_page);

}
