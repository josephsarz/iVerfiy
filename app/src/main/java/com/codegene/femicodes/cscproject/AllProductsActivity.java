package com.codegene.femicodes.cscproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codegene.femicodes.cscproject.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AllProductsActivity extends AppCompatActivity {


    final static String REFERENCE_CHILD = "products";
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize recyclerview and FIrebase objects
        recyclerView = findViewById(R.id.browse_products_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child(REFERENCE_CHILD);


    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Product, BlogzoneViewHolder> FBRA = new FirebaseRecyclerAdapter<Product, BlogzoneViewHolder>(
                Product.class,
                R.layout.browse_all_product_item,
                BlogzoneViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogzoneViewHolder viewHolder, Product model, int position) {
                final String product_key = getRef(position).getKey().toString();
                viewHolder.setProductName(model.getProductName());
                // viewHolder.setNafdacNumber(model.getNafdacNumber());
                viewHolder.setImageUrl(getApplicationContext(), model.getImageUrl());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        };
        recyclerView.setAdapter(FBRA);
    }

    public static class BlogzoneViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogzoneViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setProductName(String productName) {
            TextView product_name = mView.findViewById(R.id.product_name_tv);
            product_name.setText(productName);
        }

//        public void setNafdacNumber(String nafdacNumber) {
//            TextView nafdac_number = mView.findViewById(R.id)
//            nafdac_number.setText(nafdacNumber);
//        }

        public void setImageUrl(Context ctx, String imageUrl) {
            ImageView product_image = mView.findViewById(R.id.product_image_tv);
            Picasso.with(ctx).load(imageUrl).into(product_image);
        }
    }
}


