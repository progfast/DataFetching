package com.stylight.codingtask.view;

import android.content.Context;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import com.stylight.codingtask.R;
import com.stylight.codingtask.adapter.ItemsListAdapter;
import com.stylight.codingtask.model.Post;
import com.stylight.codingtask.utils.GridSpacingItemDecoration;

import java.util.List;

/**
 * Created by muhammadabubakar on 2/15/17.
 */

public class ItemsListView extends RelativeLayout {

    private ItemsListAdapter adapter;
    private RecyclerView mainList;
    private View noItem;
    private ProgressBar loadBar;
    private int requestInProgressCount = 0;//keep count to show/hide progress bar

    public ItemsListView(Context context) {
        super(context);
        init(context);
    }

    public ItemsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItemsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.items_list_view, this);
        if (v != null) {
            noItem = v.findViewById(R.id.no_item);
            mainList = (RecyclerView) v.findViewById(R.id.list);
            loadBar = (ProgressBar) findViewById(R.id.load_bar);

            GridLayoutManager layoutManager = new GridLayoutManager(context, context.getResources().getInteger(R.integer.columns), GridLayoutManager.VERTICAL, false);
            mainList.setLayoutManager(layoutManager);
            mainList.addItemDecoration(GridSpacingItemDecoration.newGridItemDecoration(layoutManager, (int) getResources().getDimension(R.dimen.main_list_margin), (int) getResources().getDimension(R.dimen.main_list_margin), true));
            adapter = new ItemsListAdapter(context);
            mainList.setAdapter(adapter);
        }

    }

    public void updateList(List<Object> posts) {
        if (posts == null || posts.size() == 0) {
            noItem.setVisibility(VISIBLE);
        } else {
            noItem.setVisibility(GONE);
        }
        adapter.updateList(posts);
    }

    public void showProgress() {
        requestInProgressCount++;
        loadBar.setVisibility(VISIBLE);

    }

    public void hideProgress() {
        requestInProgressCount--;
        if(requestInProgressCount == 0) {
            loadBar.setVisibility(GONE);
        }

    }

}
