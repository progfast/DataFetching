package com.stylight.codingtask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stylight.codingtask.R;
import com.stylight.codingtask.model.Product;
import com.stylight.codingtask.view.ProductViewHolder;

import java.util.List;


/**
 * Created by muhammadabubakar on 27/11/16.
 */
public class BandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Product> items;


    public BandAdapter(Context mainActivity) {
        this.context = mainActivity;
    }

    public void updateList(List<Product> data) {
        items = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ProductViewHolder vh = new ProductViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Product p = items.get(position);
        ((ProductViewHolder) holder).bind(p);

    }


    @Override
    public int getItemCount() {
        if (items != null) {
            int size = items.size();
            return size;
        }
        return 0;
    }


}
