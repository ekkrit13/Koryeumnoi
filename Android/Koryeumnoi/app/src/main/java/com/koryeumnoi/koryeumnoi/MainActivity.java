package com.koryeumnoi.koryeumnoi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //CardModel Start Here
    //1.Init Data List
    private ArrayList<CardModel> dataset;

    //2.Init Adapter
    private CustomAdapter adapter;

    private Button scan_btn, search_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;

        scan_btn = (Button) findViewById(R.id.button);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });


        search_btn = (Button) findViewById(R.id.button_search);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dointent = new Intent(activity,SearchActivity.class);
                Intent intent = getIntent();
                dointent.putExtra("MEMBER_ID",intent.getExtras().getString("MEMBER_ID"));
                startActivity(dointent);
                finish();
            }
        });

        //GetRentList Start Here
        Intent intent = getIntent();
        Intent intent2 = getIntent();

        EntityModel entity = new EntityModel();
        EntityModel entity2 = new EntityModel();

        //Assign Data to EntityModel object
        entity.getValues().put("member_id",intent.getExtras().getString("MEMBER_ID"));

        //Assign Data to EntityModel object
        entity2.getValues().put("member_id",intent2.getExtras().getString("MEMBER_ID"));

        //Create ServerConnector object
        ServerConnector connector = new ServerConnector();
        ServerConnector connector2 = new ServerConnector();

        //Connect Web page with Entitymodel object
        String strresult = connector.connect("getrentlist.php",true,entity);
        String strresult2 = connector2.connect("getuserprofile.php",true,entity2);
        //Toast.makeText(activity, strresult, Toast.LENGTH_SHORT).show();

        //Get JSON to String
        //สร้างกระบวนการตรงนี้ไว้ใน UserModel ไว้หมดแล้ว

        try {
            JSONObject jdata = new JSONObject(strresult);
            //Toast.makeText(activity, student_id, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        UserModel user = new UserModel();
        user.setUserModel(strresult2);

        TextView student_id_edt = (TextView) findViewById(R.id.student_id_edt);
        student_id_edt.setText(user.getStudentId());

        //Toast.makeText(activity,user.getStudentId(),Toast.LENGTH_SHORT).show();

        //CardView Start Here
        //3.new Data List
        dataset = new ArrayList<CardModel>();

        //4.new Adapter
        adapter = new CustomAdapter(activity, dataset);

        //5.call RecyclerView
        RecyclerView rcy = (RecyclerView)findViewById(R.id.recycler_view);

        //6.Set adapter of RecyclerView
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.setAdapter(adapter);

        //Get JSON to String
        //สร้างกระบวนการตรงนี้ไว้ใน UserModel ไว้หมดแล้ว

        try {
            JSONArray jdata = new JSONArray (strresult);

            if(jdata.length()==0) {
                TextView none_rent = (TextView) findViewById(R.id.none_tv);
                none_rent.setText("ไม่มีรายการยืมหนังสือ");
            }

            for (int i=0; i<jdata.length(); i++) {
                JSONObject actor = jdata.getJSONObject(i);
                CardModel data = new CardModel(actor.getString("title"),actor.getString("isbn"),actor.getString("rent_date"),actor.getString("deadline_date"),actor.getString("cover"));
                dataset.add(data);
            }

            //Toast.makeText(ScanActivity.this, jdata.get("isbn").toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                //Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();

                EntityModel entity = new EntityModel();

                //Assign Data to EntityModel object
                entity.getValues().put("isbn_number",result.getContents());

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

                if (detail.getBook_id() != null){
                    //Toast.makeText(SearchActivity.this,detail.getTitle(),Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this,DetailActivity.class);
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

                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
