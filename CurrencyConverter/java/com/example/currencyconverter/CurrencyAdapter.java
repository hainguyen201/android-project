package com.example.currencyconverter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CurrencyAdapter extends BaseAdapter {
    List<ItemModel> items;

    public CurrencyAdapter(List<ItemModel> items) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder= new ViewHolder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner, parent, false);
            viewHolder.textCurrencyName=convertView.findViewById(R.id.text_currency_name);
            convertView.setTag(viewHolder);
        }else
            viewHolder=(ViewHolder) convertView.getTag();
        viewHolder.textCurrencyName.setText(items.get(position).getName());
        return convertView;
    }

    class  ViewHolder
    {
        TextView textCurrencyName;
    }
}
