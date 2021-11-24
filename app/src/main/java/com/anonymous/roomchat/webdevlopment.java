package com.anonymous.roomchat;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class webdevlopment extends AppCompatActivity {
    EditText mes;
    Button send;

    DatabaseReference databaseMessage_web;

    ListView listviewmessages;
    List<Web_msg> webmessg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webdevlopment);

        databaseMessage_web = FirebaseDatabase.getInstance().getReference("web");

        mes = findViewById(R.id.msg);
        send = findViewById(R.id.send);
        webmessg = new ArrayList<>();
        listviewmessages = findViewById(R.id.webmessages);


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            String usersemailid = signInAccount.getEmail();

        }


        send.setOnClickListener(view -> sendMessage());

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseMessage_web.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                webmessg.clear();
                for (DataSnapshot webmsgsnapshot : dataSnapshot.getChildren()) {
                    Web_msg web_msg = webmsgsnapshot.getValue(Web_msg.class);
                    webmessg.add(web_msg);
                }
                Msglist adaptor = new Msglist(webdevlopment.this, webmessg);
                listviewmessages.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage() {
        GoogleSignInAccount info = GoogleSignIn.getLastSignedInAccount(this);
        String message = mes.getText().toString().trim();
        String username = info.getDisplayName();
        String useremail = info.getEmail();

        if (!TextUtils.isEmpty(message)) {
            String id = databaseMessage_web.push().getKey();

            Web_msg web = new Web_msg(id, message, username, useremail);
            databaseMessage_web.child(id).setValue(web);
        } else {
            Toast.makeText(this, "Please Enter a message", Toast.LENGTH_SHORT).show();
        }
    }
}