package ru.samsung.itschool.dbgame;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by student2 on 05.03.18.
 */

public class MyAdapter extends ArrayAdapter {
    ArrayList<Result> res;
    public MyAdapter(@NonNull Context context, @NonNull ArrayList<Result> objects) {
        super(context, R.layout.listadapterview, objects);
        res=objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= LayoutInflater.from(getContext()).inflate(R.layout.listadapterview,null);
        TextView playerName=(TextView)v.findViewById(R.id.playerName);
        playerName.setText(res.get(position).name+"");
        TextView score=(TextView)v.findViewById(R.id.score);
        score.setText(res.get(position).score+"");
        return v;
    }
}
