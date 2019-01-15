package com.xiao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.xiao.ipc.IAIDLBookInterface;
import com.xiao.ipc.entry.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService extends Service {
    private ArrayList<Book> books;
    public BookService() {
        books = new ArrayList<>();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new ServiceBinder();
    }

    class ServiceBinder extends IAIDLBookInterface.Stub {

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            return books;
        }
    }
}
