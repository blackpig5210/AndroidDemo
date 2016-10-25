package com.blackpig.www.logindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by black on 2016/9/28/0028.
 */
public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.input_name) EditText nameText;
    @Bind(R.id.input_address) EditText addressText;
    @Bind(R.id.input_email) EditText emailText;
    @Bind(R.id.input_mobile) EditText mobileText;
    @Bind(R.id.input_password) EditText passwordText;
    @Bind(R.id.input_reEnterPassword) EditText reEnterPasswordText;
    @Bind(R.id.btn_signup) Button signupButton;
    @Bind(R.id.link_login) TextView loginLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        signupButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                signup();
                break;
            case R.id.link_login:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            default:
                break;
        }

    }

    private void signup() {
        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = nameText.getText().toString();
        String address = addressText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = mobileText.getText().toString();
        String password = passwordText.getText().toString();
        String reEnterPassword = reEnterPasswordText.getText().toString();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // On complete call either onSignupSuccess or onSignupFailed
                // depending on success
                onSignupSuccess();
                // onSignupFailed();
                progressDialog.dismiss();
            }
        }, 3000);




    }

    private void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        return;
    }

    private void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String address = addressText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = mobileText.getText().toString();
        String password = passwordText.getText().toString();
        String reEnterPassword = reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (address.isEmpty()) {
            addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            addressText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=11) {
            mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4
                || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            reEnterPasswordText.setError(null);
        }

        return valid;
    }
}
