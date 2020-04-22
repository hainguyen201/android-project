package com.example.recyclerview_email.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_email.ItemClickListener;
import com.example.recyclerview_email.R;
import com.example.recyclerview_email.models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ItemAddapter extends RecyclerView.Adapter<ItemAddapter.ViewHolder>  {
    private List<ItemModel> items;
    private ItemClickListener itemClickListener;
    private List<ItemModel> itemsAll;

    public ItemAddapter(List<ItemModel> items) {
        this.items = items;
    }

    public ItemAddapter(List<ItemModel> items, ItemClickListener itemClickListener) {
        this.items = items;
        this.itemClickListener = itemClickListener;
        itemsAll= new ArrayList<>(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_out_email, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = items.get(position);
        holder.textFirst.setText(String.valueOf(item.getTextTile().charAt(0)));
        holder.textContent.setText(item.getTextContent());
        holder.textTime.setText(item.getTime());
        holder.textFirst.getBackground().setColorFilter(Color.parseColor(item.getBgColor()), PorterDuff.Mode.SRC_ATOP);
        if (item.isCkFavorite())
            holder.imgFavorite.setImageResource(R.drawable.ic_star_black);
        else holder.imgFavorite.setImageResource(R.drawable.ic_star_nomal);
    }



    @Override
    public int getItemCount() {
        return 0;
    }

    /*@Override
    public Filter getFilter() {
        return examfilter;
    }
    private Filter examfilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ItemModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemsAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ItemModel item : itemsAll) {
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
    };*/

    class ViewHolder extends RecyclerView.ViewHolder {
        //khai báo các widget
        TextView textFirst;
        TextView textTitle;
        TextView textTime;
        TextView textContent;
        CheckBox ckFavorite;
        ImageView imgFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //gán các widget từ layout
            textFirst = itemView.findViewById(R.id.text_first);
            textTitle = itemView.findViewById(R.id.text_title);
            textContent = itemView.findViewById(R.id.text_content);
            textTime = itemView.findViewById(R.id.text_time);
            ckFavorite = itemView.findViewById(R.id.ck_favorite);
            imgFavorite = itemView.findViewById(R.id.img_favorite);
            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null)
                        itemClickListener.onItemClick(getAdapterPosition());
                }
            });
            //khai báo các thuộc tính sự kiên

        }
    }
}
