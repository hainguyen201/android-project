package com.example.recyclerview_email.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_email.R;
import com.example.recyclerview_email.models.EmailItemModel;

import java.util.ArrayList;
import java.util.List;
public class EmailItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    List<EmailItemModel> items;
    List<EmailItemModel> itemsAll;

    public EmailItemAdapter(List<EmailItemModel> items) {
        this.items = items;
        itemsAll=new ArrayList<>(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_out_email, parent, false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailViewHolder viewHolder = (EmailViewHolder) holder;
        EmailItemModel item = items.get(position);

        viewHolder.textLetter.setText(item.getTextTile().substring(0, 1));
        Drawable background = viewHolder.textLetter.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(Color.parseColor(item.getBgColor()), PorterDuff.Mode.SRC_ATOP));
        viewHolder.textName.setText(item.getTextTile());

        viewHolder.textContent.setText(item.getTextContent());
        viewHolder.textTime.setText(item.getTime());
        if (item.isCkFavorite())
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_black);
        else
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_nomal);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return examfilter;
    }
    public Filter getFilter2() {
        return favoriteFilter;
    }
    private Filter favoriteFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<EmailItemModel> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0)
            {
                filteredList.addAll(itemsAll);
            }else
                for (EmailItemModel item : itemsAll) {
                    if (item.isCkFavorite()) {
                        filteredList.add(item);
                    }
                }
            FilterResults results= new FilterResults();
            results.values =filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    private Filter examfilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<EmailItemModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() < 3) {
                filteredList.addAll(itemsAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (EmailItemModel item : itemsAll) {
                    if (item.getTextTile().toLowerCase().contains(filterPattern)||
                            item.getTextContent().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class EmailViewHolder extends RecyclerView.ViewHolder {
        TextView textLetter;
        TextView textName;
        TextView textContent;
        TextView textTime;
        ImageView imageFavorite;


        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);

            textLetter = itemView.findViewById(R.id.text_first);
            textName = itemView.findViewById(R.id.text_title);

            textContent = itemView.findViewById(R.id.text_content);
            textTime = itemView.findViewById(R.id.text_time);
            imageFavorite = itemView.findViewById(R.id.img_favorite);

            imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isFavorite = items.get(getAdapterPosition()).isCkFavorite();
                    items.get(getAdapterPosition()).setCkFavorite(!isFavorite);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
