package com.dakiiii.hungerwarriorvendor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class FirebaseAuthActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 111;
    public static Intent ordersIntent;
    public static Intent mainIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_auth);
        ordersIntent = new Intent(FirebaseAuthActivity.this, OrdersActivity.class);
        mainIntent = new Intent(FirebaseAuthActivity.this, MainActivity.class);
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        startActivityForResult(
                AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
                RC_SIGN_IN
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                startActivity(mainIntent);
                Log.i("user is : ", user.getDisplayName());
                Toast.makeText(this, user.getDisplayName(), Toast.LENGTH_SHORT).show();;
            } else {
                Log.i("user is :", "Not logged in");
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }
    }
}