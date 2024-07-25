package com.example.contacts;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ContactsDao {

     @Insert
    void Insert(Contacts contact);

     @Delete
    void Delete(Contacts contact);

     @Query( "SELECT * FROM contacts_table" )
    LiveData<List<Contacts>> getAllContacts();


}
