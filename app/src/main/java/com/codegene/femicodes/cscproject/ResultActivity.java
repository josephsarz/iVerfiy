package com.codegene.femicodes.cscproject;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codegene.femicodes.cscproject.model.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ResultActivity extends AppCompatActivity {


    final static String REFERENCE_CHILD = "products";
    private static final String TAG = ResultActivity.class.getSimpleName();
    DatabaseReference myRef;
    FirebaseDatabase database;
    String code;
    Product product;
    TextView mDrugName;
    TextView mNafdacNumber;
    ImageView mDrugHeader;
    FloatingActionButton mBarcodeScannerfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(REFERENCE_CHILD);
        code = getIntent().getStringExtra("code");
        mDrugName = findViewById(R.id.drug_name);
        mNafdacNumber = findViewById(R.id.nafdac_number);
        mDrugHeader = findViewById(R.id.drug_header_image);
        mBarcodeScannerfab = findViewById(R.id.fab);
        mBarcodeScannerfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindItem(code);
            }
        });

        VerifyAsyncTask runner = new VerifyAsyncTask();
        runner.execute();
    }


    public void updateUI() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                assert product != null;
                mDrugName.setText(product.getProductName());
                mNafdacNumber.setText(product.getNafdacNumber());
                Picasso.with(ResultActivity.this)
                        .load(product.getImageUrl())
                        .into(mDrugHeader);
            }
        }, 10000);

    }


    public void FindItem(String value) {

        String child = "nafdacNumber";

        Query query = myRef.orderByChild(child).equalTo(value);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                product = dataSnapshot.getValue(Product.class);

                assert product != null;
                Toast.makeText(ResultActivity.this, "Product found " + product.getProductName() + product.getNafdacNumber(), Toast.LENGTH_LONG).show();

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
                Toast.makeText(ResultActivity.this, "Item not found: "+databaseError, Toast.LENGTH_LONG).show();
            }
        });


    }


    @SuppressLint("StaticFieldLeak")
    private class VerifyAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            FindItem(code);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            updateUI();
            super.onPostExecute(aVoid);
        }
    }


}
