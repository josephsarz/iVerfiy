package com.codegene.femicodes.cscproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ResultActivity extends AppCompatActivity {

    String productName;
    String nafdacNumber;
    String imageUrl;
    String productType;
    String manufacturerName;
    String batchNumber;
    String manufacturingDate;
    String expiringDate;
    TextView mProductName;
    TextView mNafdacNumber;
    TextView mProductType;
    TextView mManufacturerName;
    TextView mBatchNumber;
    TextView mManufacturingDate;
    TextView mExpiringDate;
    ImageView mDrugHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getting data from previous activity
        productName = getIntent().getStringExtra("productName");
        productType = getIntent().getStringExtra("productType");
        nafdacNumber = getIntent().getStringExtra("nafdacNumber");
        imageUrl = getIntent().getStringExtra("imageUrl");
        manufacturerName = getIntent().getStringExtra("manufacturerName");
        batchNumber = getIntent().getStringExtra("batchNumber");
        manufacturingDate = getIntent().getStringExtra("manufacturingDate");
        expiringDate = getIntent().getStringExtra("expiringDate");

        getSupportActionBar().setTitle(productName);

        //initializing views
        mProductName = findViewById(R.id.product_name);
        mProductType = findViewById(R.id.product_type);
        mNafdacNumber = findViewById(R.id.nafdac_number);
        mDrugHeader = findViewById(R.id.drug_header_image);
        mManufacturerName = findViewById(R.id.manufacturer_name);
        mBatchNumber = findViewById(R.id.batch_number);
        mManufacturingDate = findViewById(R.id.manufacturing_date);
        mExpiringDate = findViewById(R.id.expiring_date);

        //giving view content
        mProductName.setText(productName);
        mProductType.setText(productType);
        mNafdacNumber.setText(nafdacNumber);
        mManufacturerName.setText(manufacturerName);
        mBatchNumber.setText(batchNumber);
        mManufacturingDate.setText(manufacturingDate);
        mExpiringDate.setText(expiringDate);
        Picasso.with(ResultActivity.this)
                .load(imageUrl)
                .placeholder(R.drawable.drugimage)
                .error(R.drawable.drugimage)
                .into(mDrugHeader);
    }

}
