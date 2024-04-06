package com.example.a2001215640_dinhthituyetchinh_kt;

import static android.app.PendingIntent.getActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddMemberActivity extends AppCompatActivity {


    Button btnTroLai, btnSub;
    EditText email, firstname, lastname;
    ListItemRecycleView recyclerViewAdapter;
    ArrayList<Member> dsdt = new ArrayList<>() ;
    private int maxId;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        btnTroLai = findViewById(R.id.btnCancel);
        btnTroLai.setOnClickListener(e->{
            toListMemberActivity();
        });

        btnSub = findViewById(R.id.btnSubmit);
        email = findViewById(R.id.txtEmail);
        firstname = findViewById(R.id.txtFirstName);
        lastname = findViewById(R.id.txtLastName);
        btnSub.setOnClickListener(e->
        {
            if(patternMatches(String.valueOf(email.getText())) == false)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddMemberActivity.this);

                builder.setMessage("Email không hợp lệ.");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User taps OK button.
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else {

                btnSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent myIntent= getIntent();
                        String ten= firstname.getText().toString()+" "+lastname.getText().toString();
                        Member dt = new Member(generateNewMemberID(), ten, email.getText().toString());
                        dsdt.add(dt);
                        myIntent.putExtra("package",dsdt);
                        setResult(33,myIntent);
                        finish();
                    }
                });
            }
        });


    }

    private void showMemberList() {
        Intent intent = new Intent(this, ListMemberActivity.class);
        intent.putExtra("memberList", dsdt);
        startActivity(intent);

    }

    public  boolean patternMatches(String emailAddress) {
        return Pattern.compile("^(.+)@(\\S+)$")
                .matcher(emailAddress)
                .matches();
    }

    private void addMember() {

        String newMemberID = generateNewMemberID();

        Member newMember = new Member(newMemberID, firstname.getText() + " " + lastname.getText(),email.getText().toString());
        dsdt.add(newMember);


    }

    private String generateNewMemberID() {

        int maxID = 0;
        for (Member member : dsdt) {
            String memberID = member.getId();
            int id = Integer.parseInt(memberID.substring(1));
            if (id > maxID) {
                maxID = id;
            }
        }

        // Tạo ID mới
        int newID = maxID + 1;
        String newMemberID = "M" + String.format("%03d", newID); // Định dạng số thành chuỗi
        return newMemberID;
    }



    public void toListMemberActivity()
    {
        Intent intent = new Intent(this, ListMemberActivity.class);
        startActivity(intent);
    }












}