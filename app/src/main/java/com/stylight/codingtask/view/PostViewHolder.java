package com.stylight.codingtask.view;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stylight.codingtask.R;
import com.stylight.codingtask.model.Post;
import com.stylight.codingtask.model.Product;
import com.stylight.codingtask.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by muhammadabubakar on 2/16/17.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {



    @BindView(R.id.item_icon)
    SimpleDraweeView img;

    @BindView(R.id.slug)
    TextView slug;

    @BindView(R.id.item_desc)
    TextView name;


    public PostViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(Post post) {
        name.setText(post.getTitle());
        slug.setText(post.getSlug());

        String imgUrl = post.getTeaserImage();
        if(Utils.isNull(imgUrl)){
            img.setImageURI(null);
        }
        else {
            img.setImageURI(Uri.parse(imgUrl));
        }

    }
}