package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_SIGNUP = 0;
    public static final String BUNDLE_KEY_USERNAME = "username";
    public static final String BUNDLE_KEY_PASSWORD = "password";
    public static final String BUNDLE_KEY_INPUT_USERNAME = "inputUsername";
    public static final String BUNDLE_KEY_INPUT_PASSWORD = "inputPassword";
    public static final String EXTRA_INPUT_USER_NAME = "inputUserName";
    public static final String EXTRA_INPUT_PASSWORD = "inputPassword";
    private FrameLayout mFrameLayoutMain;
    private TextView mTextViewuserName;
    private TextView mTextViewpassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;
    private String mUsername = null;
    private String mPassword = null;
    private String mInputUsername = "";
    private String mInputPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        if (savedInstanceState != null) {
            mUsername = savedInstanceState.getString(BUNDLE_KEY_USERNAME);
            mPassword = (String) savedInstanceState.get(BUNDLE_KEY_PASSWORD);
            mInputUsername = savedInstanceState.getString(BUNDLE_KEY_INPUT_USERNAME);
            mInputPassword = savedInstanceState.getString(BUNDLE_KEY_INPUT_PASSWORD);
        }
        setListeners();


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_KEY_USERNAME, mUsername);
        outState.putString(BUNDLE_KEY_PASSWORD, mPassword);
        outState.putString(BUNDLE_KEY_INPUT_USERNAME, mInputUsername);
        outState.putString(BUNDLE_KEY_INPUT_PASSWORD, mInputPassword);

    }

    private void setListeners() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.putExtra(EXTRA_INPUT_USER_NAME, mInputUsername);
                intent.putExtra(EXTRA_INPUT_PASSWORD, mInputPassword);
                startActivityForResult(intent, REQUEST_CODE_SIGNUP);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInputUsername = String.valueOf(mTextViewuserName.getText());
                mInputPassword = String.valueOf(mTextViewpassword.getText());
                if (mUsername == null || mPassword == null) {
                    Toast toast = Toast.makeText(LoginActivity.this, "please first sign up", Toast.LENGTH_LONG);
                    toast.show();
                } else if (mInputPassword.isEmpty() || mInputUsername.isEmpty()) {
                    Toast toast = Toast.makeText(LoginActivity.this, "please fill both fields", Toast.LENGTH_LONG);
                    toast.show();
                } else if (mUsername.equals(mInputUsername) && mPassword.equals(mInputPassword)) {
                    Snackbar snackbar = Snackbar.make(mFrameLayoutMain, R.string.correct_login_input, Snackbar.LENGTH_LONG);
                    View snackBarView = snackbar.getView();
                    //snackBarView.setBackgroundColor(Color.GREEN);
                    TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
                    textView.setTextColor(Color.GREEN);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(mFrameLayoutMain, R.string.wrong_login_input, Snackbar.LENGTH_LONG);
                    View snackBarView = snackbar.getView();
                    TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar.show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_SIGNUP) {
            mUsername = data.getStringExtra(SignUpActivity.EXTRA_USER_NAME);
            mPassword = data.getStringExtra(SignUpActivity.EXTRA_PASS_WORD);
            mInputUsername = mUsername;
            mTextViewuserName.setText(mInputUsername);
            mInputPassword = mPassword;
            mTextViewpassword.setText(mInputPassword);
        }
    }

    private void findViews() {
        mFrameLayoutMain = findViewById(R.id.loginFrameLayout);
        mTextViewuserName = findViewById(R.id.editTextUsername);
        mTextViewpassword = findViewById(R.id.editTextPassword);
        mButtonLogin = findViewById(R.id.buttonLogin);
        mButtonSignUp = findViewById(R.id.buttonSignupL);
    }
}