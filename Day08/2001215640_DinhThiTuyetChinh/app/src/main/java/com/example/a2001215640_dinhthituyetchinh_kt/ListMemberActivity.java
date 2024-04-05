package com.example.a2001215640_dinhthituyetchinh_kt;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListMemberActivity extends AppCompatActivity {

    Button addMember;
   ArrayList<Member> dsdt;
    ListItemRecycleView recyclerViewAdapter;
    RecyclerView recyclerView;
    EditText txtTimKiem;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_member);

        addMember = findViewById(R.id.btnAddNewMember);
        addMember.setOnClickListener(e->
                {
                        toAddMemberActivity();
                }
        );


        dsdt = new ArrayList<>();
        Member dt = new Member("M001", "Taylor", "swift@gmail.com");
        Member dt2 = new Member("M002", "Susan", "susan@gmail.com");
        dsdt.add(dt);
        dsdt.add(dt2);

        recyclerViewAdapter = new ListItemRecycleView(this, dsdt);
        recyclerView = findViewById(R.id.recListMember);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(recyclerViewAdapter);

        txtTimKiem = findViewById(R.id.txtSearch);

        txtTimKiem.addTextChangedListener(new TextWatcher() {
            private String newTypedString = "";
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newTypedString = s.subSequence(start, start + count).toString().trim();
                System.out.println(newTypedString);
               List<Member> filterID = dsdt.stream().
                       filter(m->
                               m.getId().contains(newTypedString)
                               || m.getName().contains(newTypedString)
                               || m.getEmail().contains(newTypedString)
                       )
                       .collect(Collectors.toList());

                recyclerViewAdapter = new ListItemRecycleView(ListMemberActivity.this, filterID);
                recyclerView = findViewById(R.id.recListMember);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListMemberActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(ListMemberActivity.this,DividerItemDecoration.VERTICAL);

                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });


    }
    public void toAddMemberActivity()
    {
        Intent intent = new Intent(this, AddMemberActivity.class);
        startActivity(intent);
    }



}