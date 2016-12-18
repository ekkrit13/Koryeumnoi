package com.koryeumnoi.koryeumnoi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class SearchActivity extends AppCompatActivity {

    private TextInputLayout searchInput;
    private View btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final Activity activity = this;

        searchInput = (TextInputLayout) findViewById(R.id.search_field);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchInput.getEditText().getText().toString().trim().equals("")) {
                    Toast.makeText(SearchActivity.this, "Please input ISBN Number", Toast.LENGTH_SHORT).show();
                } else {

                    EntityModel entity = new EntityModel();

                    //Assign Data to EntityModel object
                    entity.getValues().put("isbn_number",searchInput.getEditText().getText().toString());

                    //Create ServerConnector object
                    ServerConnector connector = new ServerConnector();

                    //Connect Web page with Entitymodel object
                    String strresult = connector.connect("getisbnsearch.php",true,entity);
                    //Toast.makeText(SearchActivity.this, strresult, Toast.LENGTH_SHORT).show();

                    //Get JSON to String
                    //สร้างกระบวนการตรงนี้ไว้ใน UserModel ไว้หมดแล้ว

                    try {
                        JSONObject jdata = new JSONObject(strresult);
                        //Toast.makeText(SearchActivity.this, jdata.get("isbn").toString(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    DetailModel detail = new DetailModel();
                    detail.setDetailModel(strresult);
                    //Toast.makeText(SearchActivity.this,detail.getBook_id(),Toast.LENGTH_SHORT).show();

                    //ถ้าทุกอย่างเรียบร้อยให้ไปหน้า RentListActivity

                    if (detail.getBook_id() != null){
                        //Toast.makeText(SearchActivity.this,detail.getTitle(),Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(SearchActivity.this,DetailActivity.class);
                        Intent intent = getIntent();
                        i.putExtra("MEMBER_ID",intent.getExtras().getString("MEMBER_ID"));
                        i.putExtra("BOOK_ID",detail.getBook_id());
                        i.putExtra("ISBN_ID",detail.getIsbn());
                        i.putExtra("TITLE",detail.getTitle());
                        i.putExtra("COVER",detail.getCover());
                        i.putExtra("DETAIL",detail.getDetail());
                        i.putExtra("NAME",detail.getName());

                        startActivity(i);
                        finish();

                    }else {
                        Toast.makeText(SearchActivity.this,"\'"+searchInput.getEditText().getText().toString()+"\' Doesn't exist in Database.",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }

}

