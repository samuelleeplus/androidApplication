package com.example.myapplication.ui.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Menu;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    ArrayList<Menu> array = new ArrayList<>();
    Context context ;
    Integer totalPrice ;
    public MenuListAdapter(ArrayList<Menu> array, Context context) {
        this.array = array;
        this.context = context;
    }

    public Integer getTotalPrice(){
        return totalPrice ;
    }



    @NonNull
    @Override
    public MenuListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // could be R.layout.activity_list_restaurants.
        View listItem = layoutInflater.inflate(R.layout.list_menus, parent, false);
        MenuListAdapter.ViewHolder viewHolder = new MenuListAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuListAdapter.ViewHolder holder, int position) {
        holder.id.setText(array.get(position).getId().toString());
        holder.price.setText("$"+ array.get(position).getPrice().toString());
        holder.name.setText(array.get(position).getMenuName());
        holder.name.setChecked(false);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.name.isChecked()){
                    holder.name.setCheckMarkDrawable(0);
                    holder.name.setChecked(false);
                    Toast.makeText(context, holder.name.getText().toString() + " is un-checked" , Toast.LENGTH_SHORT).show();
                }
                else{
                    holder.name.setCheckMarkDrawable(R.drawable.ic_check_box_black_24dp);
                    holder.name.setChecked(true);
                    //totalPrice = totalPrice + Integer.valueOf(holder.price.getText().toString()) ;
                    Toast.makeText(context, holder.name.getText().toString() + " is checked" , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return array.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id ;
        TextView price ;
        CheckedTextView name ;
        public ViewHolder( View view) {
            super(view);
            id = view.findViewById(R.id.menuItemNumber);
            price = view.findViewById(R.id.menuItemPrice);
            name = view.findViewById(R.id.menuItemCheck);
        }
    }
}
