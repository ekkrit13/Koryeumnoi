package com.koryeumnoi.koryeumnoi;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel {

    private String member_id, student_id;

    public String getMemberId() {
        return member_id;
    }

    public void setMemberId(String member_id) {
        this.member_id = member_id;
    }

    public String getStudentId() {
        return student_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    public  void  setUserModel(String jsonObjstr){

        try {
            JSONObject data = new JSONObject(jsonObjstr);

            this.student_id = data.getString("student_id");
            this.member_id = data.getString("member_id");
            //this.student_id = data.get("student_id").toString();
            //this.member_id = Integer.parseInt(data.get("member_id").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}

