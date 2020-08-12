package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME = "userName";
    public static final String EXTRA_PASS_WORD = "passWord";
    private TextView mTextViewUsernameS;
    private TextView mTextViewPasswordS;
    private Button mButtonSignUpS;
    private String mUsernameS;
    private String mPasswordS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();

        mButtonSignUpS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsernameS=String.valueOf(mTextViewUsernameS.getText());
                mPasswordS=String.valueOf(mTextViewPasswordS.getText());
                Intent intent=new Intent();
                intent.putExtra(EXTRA_USER_NAME,mUsernameS);
                intent.putExtra(EXTRA_PASS_WORD,mPasswordS);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void findViews() {
        mButtonSignUpS=findViewById(R.id.buttonSignupS);
        mTextViewUsernameS=findViewById(R.id.editTextUsernsmeSignup);
        mTextViewPasswordS=findViewById(R.id.editTextPasswordSignup);
    }
}