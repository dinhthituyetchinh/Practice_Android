package com.example.a2001215640_dinhthituyetchinh_kt;

import static android.app.PendingIntent.getActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.regex.Pattern;

public class AddMemberActivity extends AppCompatActivity {

    Button btnTroLai, btnSub;
    EditText email;
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
        });


        


    }

    public  boolean patternMatches(String emailAddress) {
        return Pattern.compile("^(.+)@(\\S+)$")
                .matcher(emailAddress)
                .matches();
    }



    public void toListMemberActivity()
    {
        Intent intent = new Intent(this, ListMemberActivity.class);
        startActivity(intent);
    }
}