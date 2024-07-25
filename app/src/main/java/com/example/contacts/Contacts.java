package com.example.contacts;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_table")
public class Contacts {

    @ColumnInfo(name = "Contact_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Contact_name")
    private String Name;

// if ColumnInfo is not used database will make column of name email whatever is initialized like in 19th line
    @ColumnInfo(name = "Contact_email")
    private String email;


    public Contacts( String email, String Name) {

        this.email = email;
        this.Name = Name;
    }

    public Contacts() {
//     for null pointer exception
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
