package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME = "userName";
    public static final String EXTRA_PASS_WORD = "passWord";
    private TextView mTextViewUsernameS;
    private TextView mTextViewPasswordS;
    private Button mButtonSignUpS;
    private String mInputUsernameS;
    private String mInputPasswordS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();
        mInputUsernameS = getIntent().getStringExtra(LoginActivity.EXTRA_INPUT_USER_NAME);
        mInputPasswordS = getIntent().getStringExtra(LoginActivity.EXTRA_INPUT_PASSWORD);
        mTextViewUsernameS.setText(mInputUsernameS);
        mTextViewPasswordS.setText(mInputPasswordS);

        setListeners();
    }

    private void setListeners() {
        mButtonSignUpS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mInputUsernameS = String.valueOf(mTextViewUsernameS.getText());
                mInputPasswordS = String.valueOf(mTextViewPasswordS.getText());
                if (mInputPasswordS.isEmpty() || mInputUsernameS.isEmpty()) {
                    Toast toast = Toast.makeText(SignUpActivity.this, "please fill both fields", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_USER_NAME, mInputUsernameS);
                    intent.putExtra(EXTRA_PASS_WORD, mInputPasswordS);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void findViews() {
        mButtonSignUpS = findViewById(R.id.buttonSignupS);
        mTextViewUsernameS = findViewById(R.id.editTextUsernsmeSignup);
        mTextViewPasswordS = findViewById(R.id.editTextPasswordSignup);
    }
}