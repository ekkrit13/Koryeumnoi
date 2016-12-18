package com.koryeumnoi.koryeumnoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout userName;
    private TextInputLayout password;
    private View btnLogin, btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (TextInputLayout) findViewById(R.id.username_field);
        password = (TextInputLayout) findViewById(R.id.pass_field);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getEditText().getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please input your Username", Toast.LENGTH_SHORT).show();
                } else if (password.getEditText().getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please input your Password", Toast.LENGTH_SHORT).show();
                } else {
                    EntityModel entity = new EntityModel();

                    //Assign Data to EntityModel object
                    entity.getValues().put("login_username",userName.getEditText().getText().toString());
                    entity.getValues().put("login_password",password.getEditText().getText().toString());

                    //Create ServerConnector object
                    ServerConnector connector = new ServerConnector();

                    //Connect Web page with Entitymodel object
                    String strresult = connector.connect("getusersignin.php",true,entity);
                    //Toast.makeText(LoginActivity.this, strresult, Toast.LENGTH_SHORT).show();

                    //Get JSON to String
                    //สร้างกระบวนการตรงนี้ไว้ใน UserModel ไว้หมดแล้ว

                    try {
                        JSONObject jdata = new JSONObject(strresult);
                        //Toast.makeText(LoginActivity.this, jdata.get("member_id").toString(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    UserModel user = new UserModel();
                    user.setUserModel(strresult);

                    //Toast.makeText(LoginActivity.this,user.getMemberId(),Toast.LENGTH_SHORT).show();
                    //ถ้าทุกอย่างเรียบร้อยให้ไปหน้า RentListActivity

                    if (user.getMemberId() != null){
                        //Toast.makeText(LoginActivity.this,user.getMemberId(),Toast.LENGTH_SHORT).show();

                        Intent dointent = new Intent(LoginActivity.this,MainActivity.class);

                        dointent.putExtra("MEMBER_ID",user.getMemberId());

                        startActivity(dointent);
                        finish();

                    }else {
                        Toast.makeText(LoginActivity.this,"Username or Password is incorrect.",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dointent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(dointent);
                finish();

                //Toast.makeText(LoginActivity.this,"Register",Toast.LENGTH_SHORT).show();
            }
        });

    }
}