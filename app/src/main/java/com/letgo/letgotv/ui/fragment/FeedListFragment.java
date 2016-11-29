package com.letgo.letgotv.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;

import com.letgo.letgotv.R;
import com.letgo.letgotv.config.AppConstants;
import com.letgo.letgotv.ui.activity.DetailActivity;
import com.letgo.letgotv.ui.adapter.recycler_view_adapter.FeedListAdapter;
import com.letgo.letgotv.ui.fragment.presenter.FeedListPresenter;
import com.letgo.letgotv.ui.fragment.view.FeedListView;
import com.letgo.letgotv.ui.interfaces.IOnItemSelectedListener;
import com.letgo.letgotv.ui.model.DetailDTO;
import com.letgo.letgotv.ui.model.DetailListItemDTO;
import com.letgo.letgotv.ui.model.FeedListItemDTO;
import com.letgo.letgotv.ui.util.BaseFunctions;
import com.letgo.letgotv.ui.util.InfiniteOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Feed list Fragment
 */
public class FeedListFragment extends BaseFragment implements FeedListView, IOnItemSelectedListener {

    @Bind(R.id.fragment_list_feed_list)
    RecyclerView recyclerView;

    @Bind(R.id.fragment_list_feed_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private FeedListAdapter adapter;

    private InfiniteOnScrollListener infiniteOnScrollListener;
    private int currentPage = 1;

    private List<FeedListItemDTO> list;

    public static FeedListFragment newInstance() {

        FeedListFragment fragment = new FeedListFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        presenter = new FeedListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_feed, container, false);

        //Bind the Fragment and the View
        ButterKnife.bind(this, view);

        //Config the Recycle View
        setupRecyclerView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        //Bind the Fragment and the View
        presenter.setView(this, this);

        currentPage = 1;

        this.getList();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    private void setupRecyclerView() {

        //Create the LayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        //Create the Adapter for the RecyclerView
        adapter = new FeedListAdapter(list, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Add Animation
        SlideInUpAnimator animator = new SlideInUpAnimator();
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.setAddDuration(500);
        animator.setRemoveDuration(500);
        animator.setChangeDuration(500);
        animator.setMoveDuration(500);
        recyclerView.setItemAnimator(animator);

        //Add Action on P2R
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                currentPage = 1;

                getList();
            }
        });

        //Add Action for Infinity Scroll
        infiniteOnScrollListener = new InfiniteOnScrollListener(layoutManager) {

            @Override
            public void onLoadMore(int current_page) {

                currentPage = current_page;

                getList();
            }
        };

        recyclerView.addOnScrollListener(infiniteOnScrollListener);
    }

    private void getList() {

        if (adapter == null || infiniteOnScrollListener == null)
            return;

        if (currentPage == 1)
            infiniteOnScrollListener.reset();
        else
            infiniteOnScrollListener.setLoading(true);

        //Hack to show Progress Bar the First time
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                if (swipeRefreshLayout != null)
                    swipeRefreshLayout.setRefreshing(true);
            }
        });

        ((FeedListPresenter) presenter).doGetFeedList(currentPage);
    }

    @Override
    public void showError(String error) {

        infiniteOnScrollListener.setLoading(false);

        swipeRefreshLayout.setRefreshing(false);

        BaseFunctions.displaySnackbar(getView(), error);
    }

    @Override
    public void onSuccessGetFeedList(List<FeedListItemDTO> list) {

        infiniteOnScrollListener.setLoading(false);

        swipeRefreshLayout.setRefreshing(false);

        infiniteOnScrollListener.setIsLimitReached(list == null || list.isEmpty());

        //if we are in Page 1, add new list to adapter.
        if (currentPage == 1) {

            adapter.updateList(list);

        } else {
            //Any other case, add result to current list
            adapter.addToList(list);
        }
    }

    /**
     * On Item Select, get the Detail
     *
     * @param item
     */
    @Override
    public void onItemSelected(Object item) {

        if (adapter == null)
            return;

        swipeRefreshLayout.setRefreshing(true);

        ((FeedListPresenter) presenter).doGetDetailList(1, (FeedListItemDTO)item);
    }

    @Override
    public void onSuccessGetDetailList(ArrayList<DetailListItemDTO> list) {

        swipeRefreshLayout.setRefreshing(false);

        Intent intent = new Intent(getActivity(), DetailActivity.class);

        //Create a DTO to add the list
        DetailDTO detailDTO = new DetailDTO();
        detailDTO.setList(list);

        //Send the DTO to the other Activity using Parcelable
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.UI.IntentParams.DETAIL_PARAM, detailDTO);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
