package com.example.contacts;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    datasource
    private ContactDataBase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();
//    adapter
    private MyAdapter myAdapter;

//    Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        EdgeToEdge.enable( this );
        setContentView( R.layout.activity_main );
        ViewCompat.setOnApplyWindowInsetsListener( findViewById( R.id.main ), (v, insets) -> {
            Insets systemBars = insets.getInsets( WindowInsetsCompat.Type.systemBars() );
            v.setPadding( systemBars.left, systemBars.top, systemBars.right, systemBars.bottom );
            return insets;
        } );

//        databinding
        mainBinding = DataBindingUtil.setContentView( this,R.layout.activity_main );
        handlers = new MainActivityClickHandler( this );

        mainBinding.setClickHandler( handlers );

//        recycler view
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.setHasFixedSize( true );


//        database

        contactDatabase = contactDatabase.getInstance(this);

//        viewmodel
        MyviewModel myviewModel = new ViewModelProvider( this )
                .get( MyviewModel.class );

//        inserting a new COntact (just for testing)
        Contacts c1 = new Contacts("abhishek@gmail.com","abhi");

        myviewModel.addnewContacts( c1 );
//        we are dealing wiht viewmodel viewmodel deal with repository and repository deal with
//        roomdatabase which have DOA


//        Loading the data from the room database
        myviewModel.getAllContacts().observe( this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {


                contactsArrayList.clear();
                for (Contacts c :contacts){
                    Log.v("TAGY",c.getName());
                    contactsArrayList.add( c );
                }

                myAdapter.notifyDataSetChanged();
            }
        } );

        //        adapter
        myAdapter = new MyAdapter( contactsArrayList );

        recyclerView.setAdapter(myAdapter);

//        swipe to delete

        new ItemTouchHelper( new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

//                swipe left to delete
                Contacts c = contactsArrayList.get( viewHolder.getAdapterPosition() );

                myviewModel.deleteContacts( c );

            }
        } ).attachToRecyclerView( recyclerView );


//        loading the recycler view with th adapter
//        recyclerView.setAdapter(  );

    }
}