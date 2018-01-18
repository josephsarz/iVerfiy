package com.codegene.femicodes.cscproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codegene.femicodes.cscproject.model.Report;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportActivity extends AppCompatActivity {

    final static String REFERENCE_CHILD = "reports";
    FirebaseDatabase mReportDatabase;
    DatabaseReference mReportDatabaseReference;
    private EditText productName;
    private EditText productType;
    private EditText storeLocation;
    private EditText complainDetails;
    private EditText complaintName;
    private EditText complaintPhoneNo;
    private EditText complaintEmail;
    private EditText sstate;
    private EditText slga;
    private EditText stown;
    private Button mReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mReportDatabase = FirebaseDatabase.getInstance();
        mReportDatabaseReference = mReportDatabase.getReference(REFERENCE_CHILD);

        productName = findViewById(R.id.product_name);
        productType = findViewById(R.id.product_type);
        storeLocation = findViewById(R.id.store_location);
        complainDetails = findViewById(R.id.complain_detail);
        complaintName = findViewById(R.id.complaint_name);
        complaintPhoneNo = findViewById(R.id.complaint_phone_no);
        complaintEmail = findViewById(R.id.complaint_email);
        sstate = findViewById(R.id.state);
        slga = findViewById(R.id.lga);
        stown = findViewById(R.id.town);
        mReportButton = findViewById(R.id.report_btn);

        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ReportActivity.this, "POSTING...", Toast.LENGTH_LONG).show();
                final String pname = productName.getText().toString().trim();
                final String ptype = productType.getText().toString().trim();
                final String location = storeLocation.getText().toString().trim();
                final String cdetails = complainDetails.getText().toString().trim();
                final String cname = complaintName.getText().toString().trim();
                final String cphoneno = complaintPhoneNo.getText().toString().trim();
                final String cemail = complaintEmail.getText().toString().trim();
                final String state = sstate.getText().toString().trim();
                final String lga = slga.getText().toString().trim();
                final String town = stown.getText().toString().trim();

                Report report = new Report(pname, ptype, location, cdetails, cname, cphoneno, cemail, state, lga, town);

                mReportDatabaseReference.push().setValue(report).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(ReportActivity.this, " Successful ", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ReportActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                    }
                });

            }
        });

    }

}
