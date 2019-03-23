package com.csgames.mixparadise;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.csgames.brock.Ingredients;

import java.io.InputStream;
import java.net.URL;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Ingredients ingredients;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ConstraintLayout mainLayout;
        public MyViewHolder(ConstraintLayout layout) {
            super(layout);
            mainLayout = layout;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ingredient_item, parent, false);
        MyViewHolder vh = new MyViewHolder(layout);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            ConstraintLayout layout = holder.mainLayout;

            ImageView i = (ImageView) layout.getViewById(R.id.image_view);
//            URL myUrl = new URL(ingredients.getJuices().get(position).getImageURL());
//            InputStream inputStream = (InputStream) myUrl.getContent();
//            Drawable drawable = Drawable.createFromStream(inputStream, null);
//            i.setImageDrawable(drawable);
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
        }
        catch (Exception e) {
            Log.e("VIew", e.getStackTrace().toString());
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ingredients.getJuices().size();
    }
}
