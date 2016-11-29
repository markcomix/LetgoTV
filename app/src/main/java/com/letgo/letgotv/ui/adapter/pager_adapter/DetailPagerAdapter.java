package com.letgo.letgotv.ui.adapter.pager_adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.letgo.letgotv.ui.fragment.DetailFragment;
import com.letgo.letgotv.ui.model.DetailListItemDTO;

import java.util.List;

/**
 * Detail Adapter. Use StatePagerAdapter for better memory manage
 */
public class DetailPagerAdapter extends FragmentStatePagerAdapter {

    //List of Items
    private List<DetailListItemDTO> list;

    public DetailPagerAdapter(FragmentManager fragmentManager, List<DetailListItemDTO> list) {

        super(fragmentManager);

        this.list = list;
    }

    @Override
    public DetailFragment getItem(int position) {

        return DetailFragment.newInstance(list.get(position));
    }

    @Override
    public int getCount() {

        if (list != null)
            return list.size();
        else
            return 0;
    }
}
