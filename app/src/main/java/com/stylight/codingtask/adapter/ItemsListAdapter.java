package com.stylight.codingtask.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stylight.codingtask.R;
import com.stylight.codingtask.model.Band;
import com.stylight.codingtask.model.MainListItem;
import com.stylight.codingtask.model.Post;
import com.stylight.codingtask.model.Product;
import com.stylight.codingtask.utils.GridSpacingItemDecoration;
import com.stylight.codingtask.view.PostViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by muhammadabubakar on 2/15/17.
 */
public class ItemsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    private final int TYPE_POST = 1;
    private final int TYPE_PRODUCT = 2;
    private final int TYPE_BAND = 3;
    private final int TYPE_SECTION = 4;

    private Context context;

    public ItemsListAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<Object> data) {
        items = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == TYPE_BAND) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.band_view, parent, false);
            vh = new BandViewHolder(itemView);

        } else if (viewType == TYPE_POST) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
            vh = new PostViewHolder(itemView);

        }
        else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_view, parent, false);

            vh = new SectionViewHolder(itemView);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof BandViewHolder) {
            final Band band = (Band) items.get(position);
            ((BandViewHolder) holder).bind(band);

        } else if(holder instanceof PostViewHolder){
            Post p = (Post) items.get(position);
            ((PostViewHolder) holder).bind(p);
        }
        else if(holder instanceof SectionViewHolder){
            MainListItem sectionItem = (MainListItem) items.get(position);
            ((SectionViewHolder) holder).bind(sectionItem);
        }
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            int size = items.size();
            return size;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_SECTION;
        if(items.get(position) instanceof Post){
            type = TYPE_POST;
        }
        else if(items.get(position) instanceof Band){
            type = TYPE_BAND;
        }

        return type;
    }


    public class BandViewHolder extends RecyclerView.ViewHolder {
        private final BandAdapter adapter;
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.sub_title)
        TextView subTitle;

        @BindView(R.id.items)
        RecyclerView horizontalList;


        public BandViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            adapter = new BandAdapter(context);
            horizontalList.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            horizontalList.setLayoutManager(layoutManager);
            horizontalList.addItemDecoration(GridSpacingItemDecoration.newLinearItemDecoration(layoutManager, (int) context.getResources().getDimension(R.dimen.main_list_margin), true));

        }

        public void bind(Band band) {
            title.setText(band.getTitle());
            subTitle.setText(band.getSubTitle());

            adapter.updateList(band.getItemsList());
        }


    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.sub_title)
        TextView subTitle;


        public SectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(MainListItem item) {
            title.setText(item.getTitle());
            subTitle.setText(item.getSubTitle());
        }
    }
}
