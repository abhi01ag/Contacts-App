package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {

    MyviewModel myviewModel;
    Contacts contact;
    Context context;

    public AddNewContactClickHandler(Contacts contact, Context context, MyviewModel myviewModel) {
        this.contact = contact;
        this.context = context;
        this.myviewModel = myviewModel;
    }

    public void onSubmitbuttonClicked(View view){

        if(contact.getEmail() == null || contact.getName() == null){
            Toast.makeText(context,"Fields cannot be empty",Toast.LENGTH_LONG).show();
        }else{
            Intent i = new Intent(context,MainActivity.class);
//                    i.putExtra( "Name",contact.getName() );
//                    i.putExtra( "Email",contact.getEmail() );

            Contacts c = new Contacts(contact.getEmail(),contact.getName());
            myviewModel.addnewContacts( c );
            context.startActivity( i );
        }
    }
}
