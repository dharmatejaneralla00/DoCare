package com.marveltech.docare;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

public class BedAdapter extends  RecyclerView.Adapter<BedAdapter.BedsViewHolder> {
Context context;
ArrayList<BedsData> bedsData;
Dialog dialog,dialog_bookbed;
String gh;
TextView hname,location,addresstv,hname_bed;
EditText name,phone,age;
ImageView verifycustom;
ProgressDialog progressDialog;
    public BedAdapter(Context context, ArrayList<BedsData> bedsData) {
        this.context = context;
        this.bedsData = bedsData;
    }

    @NonNull
    @Override
    public BedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bedsrecyclerview,parent,false);
        return new BedsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BedsViewHolder holder, int position) {
        BedsData bedsData1 = bedsData.get(position);
        holder.location.setText(bedsData1.getLocation());
        holder.hname.setText(bedsData1.getHname());
        String verifystring = bedsData1.getVerify();
        String hospitalid = bedsData1.getHospitalid();
        if (TextUtils.equals(verifystring,"verified"))
        {
            holder.verify.setVisibility(View.VISIBLE);
        }
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custombeddialog);
        new Color();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        hname = dialog.findViewById(R.id.custombeddialog_hname);
        location = dialog.findViewById(R.id.custombeddialog_location);
        addresstv = dialog.findViewById(R.id.custombeddialog_address);
        hname.setText(bedsData1.getHname());
        location.setText(bedsData1.getLocation());
        addresstv.setText(bedsData1.getAddress());
        verifycustom = dialog.findViewById(R.id.custombeddialog_verify);
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("DoCare");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        if (TextUtils.equals(verifystring,"verified"))
            verifycustom.setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.custombeddialog_bookbed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_bookbed = new Dialog(context);
                dialog.setContentView(R.layout.custombedbooking);
                hname_bed = dialog_bookbed.findViewById(R.id.custombedbooking_hname);
                hname_bed.setText(bedsData1.getHname());
                name = dialog_bookbed.findViewById(R.id.custombedbooking_name);
                age = dialog_bookbed.findViewById(R.id.custombedbooking_age);
                phone = dialog_bookbed.findViewById(R.id.custombedbooking_phone);
                String book_name = name.getText().toString();
                String book_age = age.getText().toString();
                String book_phone =phone.getText().toString();
                String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                dialog_bookbed.findViewById(R.id.custombedbooking_bookbed).setOnClickListener(v1 -> {
                    if (!TextUtils.isEmpty(book_age)
                            &&!TextUtils.isEmpty(book_name)
                            &&!TextUtils.isEmpty(book_phone)
                            && book_name.length()>0
                            && book_phone.length() == 10)
                    {
                        progressDialog.setMessage("Booking Bed Please wait.....");
                        progressDialog.show();
                        bedbookingHelperClass bedbookingHelperClass = new bedbookingHelperClass(book_name,book_phone,uid,hospitalid,book_age,book_phone);
                    }
                    else {
                        progressDialog.setMessage("Fields Required..");
                        progressDialog.show();
                        progressDialog.dismiss();
                    }
                });

            }
        });
}

    @Override
    public int getItemCount() {
        return bedsData.size();
    }

    public static class BedsViewHolder extends RecyclerView.ViewHolder {
        TextView hname,location;
        ImageView verify;
        public BedsViewHolder(@NonNull View itemView) {
            super(itemView);
            hname = itemView.findViewById(R.id.bedbookng_hname);
            location = itemView.findViewById(R.id.bedbooking_location);
            verify = itemView.findViewById(R.id.bedbooking_verified);
        }
    }
}
