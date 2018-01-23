package com.codegene.femicodes.cscproject.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codegene.femicodes.cscproject.R;
import com.codegene.femicodes.cscproject.ResultActivity;
import com.codegene.femicodes.cscproject.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    final static String REFERENCE_CHILD = "products";
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
        mDatabase = FirebaseDatabase.getInstance().getReference().child(REFERENCE_CHILD);


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
        FirebaseRecyclerAdapter<Product, ProductsViewHolder> FBRA = new FirebaseRecyclerAdapter<Product, ProductsViewHolder>(
                Product.class,
                R.layout.browse_all_product_item,
                ProductsViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(ProductsViewHolder viewHolder, final Product model, int position) {
                final String product_key = getRef(position).getKey().toString();
                viewHolder.setProductName(model.getProductName());
                // viewHolder.setNafdacNumber(model.getNafdacNumber());
                viewHolder.setImageUrl(getContext(), model.getImageUrl());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(), ResultActivity.class);
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



