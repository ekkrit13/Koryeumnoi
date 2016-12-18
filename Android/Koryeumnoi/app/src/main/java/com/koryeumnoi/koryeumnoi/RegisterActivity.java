package com.koryeumnoi.koryeumnoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout userName;
    private TextInputLayout password;
    private View btnLogin, btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (TextInputLayout) findViewById(R.id.username_field);
        password = (TextInputLayout) findViewById(R.id.pass_field);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getEditText().getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please input your Username", Toast.LENGTH_SHORT).show();
                } else if (password.getEditText().getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please input your Password", Toast.LENGTH_SHORT).show();
                } else {
                    EntityModel entity = new EntityModel();

                    //Assign Data to EntityModel object
                    entity.getValues().put("register_username",userName.getEditText().getText().toString());
                    entity.getValues().put("register_password",password.getEditText().getText().toString());

                    //Create ServerConnector object
                    ServerConnector connector = new ServerConnector();

                    //Connect Web page with Entitymodel object
                    String strresult = connector.connect("insertuserregister.php",true,entity);
                    //Toast.makeText(LoginActivity.this, strresult, Toast.LENGTH_SHORT).show();

                    if(strresult.equals("true")) {
                        Toast.makeText(RegisterActivity.this, "Register Complete! Welcome.", Toast.LENGTH_SHORT).show();
                        Intent dointent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(dointent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Sorry Can't Register Right Now.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dointent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(dointent);
                finish();

                //Toast.makeText(LoginActivity.this,"Register",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
