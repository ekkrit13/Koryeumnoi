package com.koryeumnoi.koryeumnoi;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailModel {

    private String book_id, isbn, cover, title, name, detail;

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public  void  setDetailModel(String jsonObjstr){

        try {
            JSONObject data = new JSONObject(jsonObjstr);

            this.book_id = data.getString("book_id");
            this.isbn = data.getString("isbn");
            this.cover = data.getString("cover");
            this.title = data.getString("title");
            this.name = data.getString("name");
            this.detail = data.getString("detail");
            //this.student_id = data.get("student_id").toString();
            //this.member_id = Integer.parseInt(data.get("member_id").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}

