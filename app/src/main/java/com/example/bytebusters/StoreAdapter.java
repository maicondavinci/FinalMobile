package com.example.bytebusters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StoreAdapter extends BaseAdapter {

    private Context context;
    private List<Store> stores;
    private LayoutInflater inflater;

    public StoreAdapter(Context context, List<Store> stores) {
        this.context = context;
        this.stores = stores;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stores.size();
    }

    @Override
    public Object getItem(int position) {
        return stores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.store_item, parent, false);
        }

        Store store = stores.get(position);

        TextView nameTextView = view.findViewById(R.id.store_name);
        TextView mallTextView = view.findViewById(R.id.store_mall);
        TextView streetTextView = view.findViewById(R.id.store_street);
        TextView cityTextView = view.findViewById(R.id.store_city);
        TextView numberTextView = view.findViewById(R.id.store_number);

        nameTextView.setText(store.getName());
        mallTextView.setText("Centro Comercial: " + store.getMall());
        streetTextView.setText("Calle: " + store.getStreet());
        cityTextView.setText("Ciudad: " + store.getCity());
        numberTextView.setText("Número: " + store.getNumber());

        return view;
    }
}
