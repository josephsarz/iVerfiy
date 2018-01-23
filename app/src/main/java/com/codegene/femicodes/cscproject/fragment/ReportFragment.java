package com.codegene.femicodes.cscproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.codegene.femicodes.cscproject.MainActivity;
import com.codegene.femicodes.cscproject.R;
import com.codegene.femicodes.cscproject.model.Report;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    final static String REFERENCE_CHILD = "reports";
    FirebaseDatabase mReportDatabase;
    DatabaseReference mReportDatabaseReference;
    CoordinatorLayout layout;
    private EditText productName;
    private EditText productType;
    private EditText storeLocation;
    private EditText complainDetails;
    private EditText complaintName;
    private EditText complaintPhoneNo;


    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        setHasOptionsMenu(true);

        layout = view.findViewById(R.id.coordinator_layout);

        mReportDatabase = FirebaseDatabase.getInstance();
        mReportDatabaseReference = mReportDatabase.getReference(REFERENCE_CHILD);

        productName = view.findViewById(R.id.product_name);
        productType = view.findViewById(R.id.product_type);
        storeLocation = view.findViewById(R.id.store_location);
        complainDetails = view.findViewById(R.id.complain_detail);
        complaintName = view.findViewById(R.id.complaint_name);
        complaintPhoneNo = view.findViewById(R.id.complaint_phone_no);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Report");
    }

    public void addReport() {

        final String pname = productName.getText().toString().trim();
        final String ptype = productType.getText().toString().trim();
        final String location = storeLocation.getText().toString().trim();
        final String cdetails = complainDetails.getText().toString().trim();
        final String cname = complaintName.getText().toString().trim();
        final String cphoneno = complaintPhoneNo.getText().toString().trim();

        if (!TextUtils.isEmpty(pname) && !TextUtils.isEmpty(ptype)) {
            Toast.makeText(getContext(), "POSTING...", Toast.LENGTH_LONG).show();

            Report report = new Report(pname, ptype, location, cdetails, cname, cphoneno);

            mReportDatabaseReference.push().setValue(report).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), " Successful ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                }
            });
        } else {
            Snackbar snackbar = Snackbar
                    .make(layout, "All Fields are required", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_report, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_report_done) {
            addReport();
        }

        return super.onOptionsItemSelected(item);
    }
}
