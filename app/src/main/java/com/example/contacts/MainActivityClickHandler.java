package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandler {

    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void FABClicked(View view){
        Intent i = new Intent( view.getContext(), AddNewContactActivity.class );
        context.startActivity( i );
    }
}
