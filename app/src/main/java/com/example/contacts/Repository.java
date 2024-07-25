package com.example.contacts;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final ContactsDao contactsDao;
    ExecutorService executor;
    Handler handler;

    public Repository(Application application) {

        ContactDataBase contactDataBase = ContactDataBase.getInstance( application );
        this.contactsDao = contactDataBase.getContactDAO();
        //        used for background database operation
        executor = Executors.newSingleThreadExecutor();
//        used for updating the UI
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact){

//      Runnable : executing tasks on separate Thread
        executor.execute( new Runnable() {
            @Override
            public void run() {
                contactsDao.Insert( contact);
            }
        } );
    }

    public void deleteContact(Contacts contact){

        executor.execute( new Runnable() {
            @Override
            public void run() {
                contactsDao.Delete( contact );
            }
        } );

    }

    public LiveData<List<Contacts>> getAllContacts(){
        return contactsDao.getAllContacts();
    }

}
