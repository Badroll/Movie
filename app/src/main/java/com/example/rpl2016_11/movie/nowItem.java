package com.example.rpl2016_11.movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;

public class nowItem extends ArrayList<Parcelable> implements Parcelable {
   /* private final String imageurl;
    private final String tittle;
    private final String rate;
    private final String language;
    private final String description;
    private final String popularity;
    private final String date;*/
    String Imageurl;
    String Tittle;
    String Rate;
    String Language;
    String Description;
    String Popularity;
    String Date;

   // public nowItem(String id, String string, String cursorString, String s, String string1, String cursorString1, String s1, String string2, String cursorString2){}

    public nowItem( String imageurl, String tittle, String rate, String language, String description, String popularity, String date) {
        Imageurl = imageurl;
        Tittle = tittle;
        Rate = rate;
        Language = language;
        Description = description;
        Popularity = popularity;
        Date = date;
    }

    public nowItem() {

    }

   /* public nowItem(String imageurl, String tittle, String rate, String language, String description, String popularity, String date) {
        this.imageurl = imageurl;
        this.tittle = tittle;
        this.rate = rate;
        this.language = language;
        this.description = description;
        this.popularity = popularity;
        this.date = date;
    }*/

    public String getImageurl() {
        return Imageurl;
    }

    public void setImageurl(String imageurl) {
        Imageurl = imageurl;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPopularity() {
        return Popularity;
    }

    public void setPopularity(String popularity) {
        Popularity = popularity;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(Imageurl);
        parcel.writeString(Tittle);
        parcel.writeString(Rate);
        parcel.writeString(Language);
        parcel.writeString(Description);
        parcel.writeString(Popularity);
        parcel.writeString(Date);
    }

    protected nowItem(Parcel in){
        this.Imageurl = in.readString();
        this.Tittle= in.readString();
        this.Rate= in.readString();
        this.Language= in.readString();
        this.Description= in.readString();
        this.Popularity= in.readString();
        this.Date= in.readString();
    }

    public static final Creator<nowItem> CREATOR = new Creator<nowItem>() {
        @Override
        public nowItem createFromParcel(Parcel in) {
            return new nowItem(in);
        }

        @Override
        public nowItem[] newArray(int size) {
            return new nowItem[size];
        }
    };

    @Override
    public Stream<Parcelable> stream() {
        return null;
    }

    @Override
    public Stream<Parcelable> parallelStream() {
        return null;
    }
}
