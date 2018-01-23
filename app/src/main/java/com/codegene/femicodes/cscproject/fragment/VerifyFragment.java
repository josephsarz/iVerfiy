package com.codegene.femicodes.cscproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codegene.femicodes.cscproject.R;
import com.codegene.femicodes.cscproject.ResultActivity;
import com.codegene.femicodes.cscproject.ScanActivity;
import com.codegene.femicodes.cscproject.model.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class VerifyFragment extends Fragment {

    final static String REFERENCE_CHILD = "products";
    Button mSearchNafdacNumberBtn;
    EditText mNafdacNumberEditText;
    CoordinatorLayout layout;
    DatabaseReference myRef;
    Product product;
    ProgressBar mProgress;


    public VerifyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify, container, false);

        layout = view.findViewById(R.id.coordinator_layout);

        myRef = FirebaseDatabase.getInstance().getReference(REFERENCE_CHILD);
        mNafdacNumberEditText = view.findViewById(R.id.nafdac_number_edittext);
        mProgress = view.findViewById(R.id.progress_bar);


        mSearchNafdacNumberBtn = view.findViewById(R.id.nafdac_number_search_button);
        mSearchNafdacNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = mNafdacNumberEditText.getText().toString().trim();
                // close the activity in case of empty barcode
                if (TextUtils.isEmpty(code)) {
                    Snackbar snackbar = Snackbar
                            .make(layout, "field is empty!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    mProgress.setVisibility(View.VISIBLE);
                    FindItem(code);
                }
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ScanActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

    public void FindItem(String value) {
        Toast.makeText(getContext(), "Searching", Toast.LENGTH_LONG).show();
        String child = "nafdacNumber";

        Query query = myRef.orderByChild(child).equalTo(value);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                product = dataSnapshot.getValue(Product.class);

                if (product != null) {

                    mProgress.setVisibility(View.VISIBLE);

                    Toast.makeText(getContext(), product.getManufacturerName(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getContext(), ResultActivity.class);
                    intent.putExtra("productName", product.getProductName());
                    intent.putExtra("productType", product.getProductType());
                    intent.putExtra("imageUrl", product.getImageUrl());
                    intent.putExtra("nafdacNumber", product.getNafdacNumber());
                    intent.putExtra("manufacturingName", product.getManufacturerName());
                    intent.putExtra("batchNumber", product.getBatchNumber());
                    intent.putExtra("manufacturingDate", product.getManufacturingDate());
                    intent.putExtra("expiringDate", product.getExpiringDate());
                    startActivity(intent);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mProgress.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Item not found: " + databaseError, Toast.LENGTH_LONG).show();
            }
        });

        mProgress.setVisibility(View.VISIBLE);
    }


}
