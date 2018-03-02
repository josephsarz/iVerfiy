package com.codegene.femicodes.cscproject.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codegene.femicodes.cscproject.Constants;
import com.codegene.femicodes.cscproject.ProductDetailsActivity;
import com.codegene.femicodes.cscproject.R;
import com.codegene.femicodes.cscproject.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class ProductsFragment extends Fragment {

    String SearchString = "";
    ProgressBar mProgress;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        //initialize recyclerview and FIrebase objects
        recyclerView = view.findViewById(R.id.browse_products_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child(Constants.REFERENCE_CHILD_PRODUCTS);
        mProgress = view.findViewById(R.id.progressbar_product_list);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Products");
    }


    @Override
    public void onStart() {
        super.onStart();
        Query mQuerySearch = mDatabase.orderByChild("productName").equalTo("panadol");

        mProgress.setVisibility(View.VISIBLE);
        FirebaseRecyclerAdapter<Product, ProductsViewHolder> FBRA = new FirebaseRecyclerAdapter<Product, ProductsViewHolder>(
                Product.class,
                R.layout.browse_all_product_item,
                ProductsViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(ProductsViewHolder viewHolder, final Product model, int position) {
                mProgress.setVisibility(View.INVISIBLE);
                final String product_key = getRef(position).getKey().toString();
                viewHolder.setProductName(model.getProductName());
                // viewHolder.setNafdacNumber(model.getNafdacNumber());
                viewHolder.setImageUrl(getContext(), model.getImageUrl());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                        intent.putExtra("productName", model.getProductName());
                        intent.putExtra("productType", model.getProductType());
                        intent.putExtra("imageUrl", model.getImageUrl());
                        intent.putExtra("nafdacNumber", model.getNafdacNumber());
                        intent.putExtra("manufacturingName", model.getManufacturerName());
                        intent.putExtra("batchNumber", model.getBatchNumber());
                        intent.putExtra("manufacturingDate", model.getManufacturingDate());
                        intent.putExtra("expiringDate", model.getExpiringDate());
                        startActivity(intent);

                    }
                });
            }
        };
        recyclerView.setAdapter(FBRA);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    SearchString = "";
                } else {
                    // adapter.filter(newText);
                    SearchString = newText;
                    Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ProductsViewHolder(View itemView) {
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



