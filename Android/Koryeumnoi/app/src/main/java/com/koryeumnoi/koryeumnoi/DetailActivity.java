package com.koryeumnoi.koryeumnoi;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private Button confirm_btn;
    private String book_id, member_id, isbn_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Activity activity = this;

        ServerConnector connector = new ServerConnector();
        String cover_path = connector.seturl_cover;

        TextView TVNameBook_edt = (TextView) findViewById(R.id.TVNameBook);
        TextView TVISBN_edt = (TextView) findViewById(R.id.TVISBN);
        TextView TVCategory_edt = (TextView) findViewById(R.id.TVCategory);
        TextView TVDetail_edt = (TextView) findViewById(R.id.TVDetail);
        ImageView IMVpicbook_edt = (ImageView) findViewById(R.id.IMVpicbook);

        Intent i = getIntent();
        TVNameBook_edt.setText(i.getExtras().getString("TITLE"));
        TVISBN_edt.setText(i.getExtras().getString("ISBN_ID"));
        TVCategory_edt.setText(i.getExtras().getString("NAME"));
        TVDetail_edt.setText(i.getExtras().getString("DETAIL"));

        book_id = i.getExtras().getString("BOOK_ID");
        member_id = i.getExtras().getString("MEMBER_ID");
        isbn_id = i.getExtras().getString("ISBN_ID");

        Glide.with(activity).load(cover_path+i.getExtras().getString("COVER")).centerCrop().into(IMVpicbook_edt);


        confirm_btn = (Button) findViewById(R.id.BTConfirm);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EntityModel entity = new EntityModel();

                //Assign Data to EntityModel object
                entity.getValues().put("book_id",book_id);
                entity.getValues().put("member_id",member_id);
                entity.getValues().put("isbn_id",isbn_id);

                //Create ServerConnector object
                ServerConnector connector = new ServerConnector();

                //Connect Web page with Entitymodel object
                String strresult = connector.connect("insertrentlist.php",true,entity);
                //Toast.makeText(activity, strresult, Toast.LENGTH_SHORT).show();


                if(strresult.equals("true")) {
                    Intent dointent = new Intent(activity,MainActivity.class);
                    dointent.putExtra("MEMBER_ID",member_id);
                    startActivity(dointent);
                    finish();
                } else {
                    Toast.makeText(activity, "Sorry Can't Rent This Book.", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
