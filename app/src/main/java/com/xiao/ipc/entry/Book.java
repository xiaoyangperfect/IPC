package com.xiao.ipc.entry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${yang} on 2019/1/15.
 */
public class Book implements Parcelable {

    private String bookName;
    private String author;
    private int price;

    public Book() {
    }

    public Book(Parcel in) {
        bookName = in.readString();
        author = in.readString();
        price = in.readInt();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeString(author);
        dest.writeInt(price);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
