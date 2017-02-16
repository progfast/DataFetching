package com.stylight.codingtask.view;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stylight.codingtask.R;
import com.stylight.codingtask.model.Product;
import com.stylight.codingtask.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by muhammadabubakar on 2/16/17.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.item_thumbnail)
    SimpleDraweeView img;

    @BindView(R.id.item_title)
    TextView name;

    @BindView(R.id.item_price)
    TextView price;

    public ProductViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(Product product) {
        name.setText(product.getName());
        price.setText(product.getPrice()+product.getCurrencySymbol());
        String imgUrl = product.getImage();
        if(Utils.isNull(imgUrl)){
            img.setImageURI(null);
        }
        else {
            img.setImageURI(Uri.parse(imgUrl));
        }

    }
}