package com.letgo.letgotv.ui.fragment.view;

import com.letgo.letgotv.ui.model.DetailListItemDTO;
import com.letgo.letgotv.ui.model.FeedListItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Feed View. List of Method to implement
 */
public interface FeedListView extends BaseView {

    void onSuccessGetFeedList(List<FeedListItemDTO> list);

    void onSuccessGetDetailList(ArrayList<DetailListItemDTO> list);
}