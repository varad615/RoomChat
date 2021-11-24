package com.anonymous.roomchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    Button logout;
    TextView name, email;
    ImageView imageView;
    Button webdev, androiddev, fedev, bkdev, fullstack;

    FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        imageView = findViewById(R.id.profile);
        email = findViewById(R.id.email);

        // button
        webdev = findViewById(R.id.webdev);
        androiddev = findViewById(R.id.androiddev);
        fedev = findViewById(R.id.fedev);
        bkdev = findViewById(R.id.bedev);
        fullstack = findViewById(R.id.fullstackdev);


        // activity
        webdev.setOnClickListener(view -> {
            Intent web = new Intent(Profile.this, webdevlopment.class);
            startActivity(web);
        });

        //

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
            String username = signInAccount.getDisplayName();

            Glide.with(this).load(signInAccount.getPhotoUrl()).into(imageView);
            Toast.makeText(this, "Welcome " + username, Toast.LENGTH_SHORT).show();

        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i = new Intent(Profile.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}