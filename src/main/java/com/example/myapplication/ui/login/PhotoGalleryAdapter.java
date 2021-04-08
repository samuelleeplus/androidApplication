package com.example.myapplication.ui.login;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.ViewHolder> {

    int images[];

    Context context ;
    String captions[];


    public PhotoGalleryAdapter(int[] images, Context context, String[] cap) {
        this.images = images;
        this.context = context;
        this.captions = cap ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // could be R.layout.activity_list_restaurants.
        View listItem = layoutInflater.inflate(R.layout.list_gallery, parent, false);
        PhotoGalleryAdapter.ViewHolder viewHolder = new PhotoGalleryAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.textView.setText(captions[position]);
        holder.imageView.setImageResource(images[position]);

        holder.SpecificRestaurantLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Captions: " + captions[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length ;
    }



     class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView ;
         ConstraintLayout SpecificRestaurantLayout ;



         ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.galleryImage);
            textView = view.findViewById(R.id.galleryCaption);
            SpecificRestaurantLayout = view.findViewById(R.id.listGalleryConstraint);




        }


        }


    }

