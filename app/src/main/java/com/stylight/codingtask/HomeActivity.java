package com.stylight.codingtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.stylight.codingtask.model.Band;
import com.stylight.codingtask.model.MainListItem;
import com.stylight.codingtask.model.Post;
import com.stylight.codingtask.model.PostResponse;
import com.stylight.codingtask.model.Product;
import com.stylight.codingtask.model.ProductResponse;
import com.stylight.codingtask.network.DataHandler;
import com.stylight.codingtask.utils.Constants;
import com.stylight.codingtask.view.ItemsListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class HomeActivity extends AppCompatActivity {

    private List<Object> items;
    @BindView(R.id.main_list)
    ItemsListView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initialiseData();
    }

    private void initialiseData() {//This data is supposed to get from backend, but in this case I am forming data locally
        items = new ArrayList<>();
        Band products = new Band(Constants.ITEM_TYPE_PRODUCT,"Check out These", "New and Trending Products","", 8);
        items.add(products);

        MainListItem fashionPosts = new MainListItem(Constants.ITEM_TYPE_POST,"The latest fashion news", "Get up to date","fashion", 3);
        items.add(fashionPosts);

        Band lamps = new Band(Constants.ITEM_TYPE_PRODUCT,"The coolest lamps", "New and Trending Products","28107", 8);
        items.add(lamps);

        MainListItem lifeStyle = new MainListItem(Constants.ITEM_TYPE_POST,"The latest lifestyle news", "Get up to date","lifestyle", 3);
        items.add(lifeStyle);

        fetchData();
    }

    /**
     * fetch all data from server for posts and products
     */
    private void fetchData() {
        for(int i = 0; i < items.size(); i++){
            if(items.get(i) instanceof Band){
                fetchProducts((Band) items.get(i));
            }
            else if(items.get(i) instanceof MainListItem){
                fetchItems((MainListItem) items.get(i));
            }
        }
    }


    private void fetchProducts(final Band band){
        mainList.showProgress();
        DataHandler.getInstance().getProductsByCategory(band.getQuery(), band.getNoOfItemsInSection()).subscribe(new Action1<ProductResponse>() {
            @Override
            public void call(ProductResponse responseObject) {
                mainList.hideProgress();
                List<Product> products = responseObject.getProducts();

                //remove item if there is no item
                if(products == null || products.size() == 0){
                    items.remove(band);
                }
                else{
                    band.setItemsList(products);
                }
                mainList.updateList(items);

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("Error", "Error");
                mainList.hideProgress();
                //remove item if data fetching is failed
                items.remove(band);
                mainList.updateList(items);

            }
        });
    }

    private void fetchItems(final MainListItem item){
        final int index = items.indexOf(item);
        mainList.showProgress();
        if(item.getItemType() == Constants.ITEM_TYPE_POST) {
            DataHandler.getInstance().getPostsBySlug(item.getQuery(), item.getNoOfItemsInSection()).subscribe(new Action1<PostResponse>() {
                @Override
                public void call(PostResponse responseObject) {
                    mainList.hideProgress();
                    //add all items from response to next index of Item specifier, item index is showing section title
                    List<Post> posts = responseObject.getPosts();

                    //remove item if there is no item
                    if(posts == null || posts.size() == 0){
                        items.remove(item);
                    }
                    else{
                        items.addAll(index + 1 , posts);
                    }

                    mainList.updateList(items);

                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Log.e("Error", "Error");
                    mainList.hideProgress();
                    //remove item if data fetching is failed
                    items.remove(item);
                    mainList.updateList(items);
                }
            });
        }

       /* else if(item.getItemType() == Constants.ITEM_TYPE_PRODUCT){
            DataHandler.getInstance().getProductsByCategory(item.getQuery(), item.getNoOfItemsInSection()).subscribe(new Action1<ProductResponse>() {
                @Override
                public void call(ProductResponse responseObject) {
                    items.addAll(index + 1 ,responseObject.getProducts());
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    mainList.updateList(items);
                    Log.e("Error", "Error");
                }
            });
        }*/
    }

}
