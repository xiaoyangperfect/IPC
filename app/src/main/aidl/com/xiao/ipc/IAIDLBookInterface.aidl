// IAIDLBookInterface.aidl
package com.xiao.ipc;
import com.xiao.ipc.entry.Book;

// Declare any non-default types here with import statements

interface IAIDLBookInterface {
    void addBook(in Book book);
    List<Book> getBooks();
}
