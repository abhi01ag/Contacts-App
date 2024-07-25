package com.example.contacts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyviewModel extends AndroidViewModel {

    private Repository myrepository;
    private LiveData<List<Contacts>> allContacts;

    public MyviewModel(@NonNull Application application) {
        super( application );
        this.myrepository = new Repository( application );
    }

    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = myrepository.getAllContacts();
        return allContacts;
    }

    public void addnewContacts(Contacts contact){
        myrepository.addContact( contact );
    }

    public void deleteContacts(Contacts contact){
        myrepository.deleteContact( contact );
    }
}
