package com.moonlabcoding.calculatriceappwithhistorique;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoriqueAdapter extends BaseAdapter {

    private List<Operation> historique;

    public HistoriqueAdapter(List<Operation> historique) {
        this.historique = historique;
    }

    @Override
    public int getCount() {
        return historique.size();
    }

    @Override
    public Operation getItem(int position) {
        return historique.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsvelement, parent, false);
        }

        Operation historiqueItem = getItem(position);

        TextView txtOperation = convertView.findViewById(R.id.txtOperation);
        TextView txtResultat = convertView.findViewById(R.id.txtResultat);

        txtOperation.setText(historiqueItem.getOperation());
        txtResultat.setText(historiqueItem.getResultat());

        return convertView;
    }
}
