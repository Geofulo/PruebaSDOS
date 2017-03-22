package com.geoapps.pruebasdos.webservices;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geoapps.pruebasdos.R;
import com.geoapps.pruebasdos.administrador.UsuariosAdapter;
import com.geoapps.pruebasdos.administrador.VerTareaActivity;

import models.ItemWS;

/**
 * Created by geovanni on 21/03/17.
 */

public class WebServicesAdapter extends RecyclerView.Adapter<WebServicesAdapter.ViewHolder>
{
    public static ItemWS[] items;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public LinearLayout linearLayout;
        public TextView itemTextView;
        public TextView categoryTextView;
        public TextView lTextView;

        public ViewHolder(LinearLayout l)
        {
            super(l);
            linearLayout = l;
            itemTextView = (TextView) l.findViewById(R.id.tv_item_adapter);
            categoryTextView = (TextView) l.findViewById(R.id.tv_category_adapter);
            lTextView = (TextView) l.findViewById(R.id.tv_l_adapter);

            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext().getApplicationContext(), VerItemActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("item", items[getAdapterPosition()]);
                    v.getContext().getApplicationContext().startActivity(i);
                }
            });
        }
    }

    public WebServicesAdapter(ItemWS[] items) {
        this.items = items;
    }

    @Override
    public WebServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_web_services, parent, false);
        WebServicesAdapter.ViewHolder vh = new WebServicesAdapter.ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(WebServicesAdapter.ViewHolder holder, int position) {
        holder.itemTextView.setText(items[position].getItem());
        holder.categoryTextView.setText(items[position].getCategory());
        holder.lTextView.setText(items[position].getL());
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
