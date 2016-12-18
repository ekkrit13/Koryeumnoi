package com.koryeumnoi.koryeumnoi;

public class CardModel {

    private String title,isbn,rent_date,deadline_date,cover;

    public CardModel(String title, String isbn, String rent_date, String deadline_date, String cover) {
        this.title = title;
        this.isbn = isbn;
        this.rent_date = rent_date;
        this.deadline_date = deadline_date;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getRent_date() {
        return rent_date;
    }

    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }

    public String getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
