package com.marveltech.docare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BedsActivity extends AppCompatActivity {
RecyclerView recyclerView;
BedAdapter bedAdapter;
DatabaseReference reference;
ArrayList<BedsData> bedsDataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beds);
        recyclerView = findViewById(R.id.beds_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        reference = FirebaseDatabase.getInstance().getReference("Hospitals");
        bedsDataArrayList = new ArrayList<>();
        bedAdapter = new BedAdapter(this,bedsDataArrayList);
        recyclerView.setAdapter(bedAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    BedsData bedsData = dataSnapshot.getValue(BedsData.class);
                    bedsDataArrayList.add(bedsData);
                }
                bedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}