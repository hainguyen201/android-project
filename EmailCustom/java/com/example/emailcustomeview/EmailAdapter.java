package com.example.emailcustomeview;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Random;

import static com.example.emailcustomeview.HexToDecimalExample3.getDecimal;

public class EmailAdapter extends BaseAdapter {
    List<ItemModel> items;

    public EmailAdapter(List<ItemModel> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        Random rd=new Random();
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_out_email, parent, false);
            viewHolder= new ViewHolder();
            viewHolder.textTitle=convertView.findViewById(R.id.text_title);
            viewHolder.textFirst=convertView.findViewById(R.id.text_first);
            viewHolder.textContent=convertView.findViewById(R.id.text_content);
            viewHolder.textTime=convertView.findViewById(R.id.text_time);
            viewHolder.ckFavorite=convertView.findViewById(R.id.ck_favorite);
            viewHolder.imgFavorite=convertView.findViewById(R.id.img_favorite);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder=(ViewHolder) convertView.getTag();
        final ItemModel item=items.get(position);
        viewHolder.textTitle.setText(item.getTextTile());
        viewHolder.textFirst.setText(String.valueOf(item.getTextTile().charAt(0)));
        viewHolder.textContent.setText(item.getTextContent());
        viewHolder.textTime.setText(item.getTime());
        ColorFilter colorFilter= new ColorFilter();
        viewHolder.textFirst.getBackground().setColorFilter(Color.parseColor(item.getBgColor()), PorterDuff.Mode.SRC_ATOP);
        if(item.isCkFavorite())
            viewHolder.imgFavorite.setImageResource(R.drawable.ic_star_black);
        else viewHolder.imgFavorite.setImageResource(R.drawable.ic_star_nomal);
        viewHolder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischecked=items.get(position).isCkFavorite();
                items.get(position).setCkFavorite(!ischecked);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    class  ViewHolder{
        TextView textFirst;
        TextView textTitle;
        TextView textTime;
        TextView textContent;
        CheckBox ckFavorite;
        ImageView imgFavorite;
    }
}
