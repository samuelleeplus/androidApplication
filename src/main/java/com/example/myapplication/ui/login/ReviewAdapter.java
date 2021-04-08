package com.example.myapplication.ui.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    Context context ;
    public ArrayList<String> reviews, names ;

    //we need to receive reviews
    // and their ids

    public ReviewAdapter(ArrayList<String> reviews, ArrayList<String> names, Context context) {
        this.reviews = reviews;
        this.names = names;
        this.context = context;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // could be R.layout.activity_list_restaurants.
        View listItem = layoutInflater.inflate(R.layout.list_reviews, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.userId.setText(names.get(position));
        holder.review.setText(reviews.get(position));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Review by user: "+ names.get(position), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView userId ;
        TextView review ;
        ConstraintLayout layout ;

        ViewHolder(View view){
            super(view);
            layout = view.findViewById(R.id.listReviewConstraint) ;
            userId = view.findViewById(R.id.reviewName);

            review = view.findViewById(R.id.reviewContent);;

        }
    }
}
