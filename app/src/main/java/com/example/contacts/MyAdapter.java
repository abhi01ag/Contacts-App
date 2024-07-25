package com.example.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.databinding.ItemLayoutBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {
    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        ItemLayoutBinding Itemlayoutbinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_layout,
                        parent,
                        false

                );

        return new ContactViewHolder( Itemlayoutbinding );
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Contacts currentContact = contacts.get(position);

        holder.Itemlayoutbinding.setContact( currentContact );


    }

    @Override
    public int getItemCount() {
        if (contacts!=null){
            return contacts.size();
        }else{
            return 0;
        }
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;


//        inform the associated recyclerview the underlying dataset is changed
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{


        private ItemLayoutBinding Itemlayoutbinding;

        public ContactViewHolder(@NonNull  ItemLayoutBinding itemlayoutbinding) {
            super(itemlayoutbinding.getRoot());
            this.Itemlayoutbinding = itemlayoutbinding;
        }
    }

}
