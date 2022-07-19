package com.example.hust;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SubjectAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Subject> subjectList;

    public SubjectAdapter(MainActivity context, int layout, List<Subject> subjectList) {
        this.context = context;
        this.layout = layout;
        this.subjectList = subjectList;
    }

    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        TextView textMSSV, textHoten;

        ImageView imgSua, imgXoa;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Khai báo ViewHolder
        ViewHolder viewHolder;
        if(view==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.textMSSV =(TextView) view.findViewById(R.id.textviewCustom);
            viewHolder.textHoten = (TextView) view.findViewById(R.id.textviewMotaCustom);

            viewHolder.imgSua = (ImageView) view.findViewById(R.id.imgSua);
            viewHolder.imgXoa = (ImageView) view.findViewById(R.id.imgXoa);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textMSSV.setText(subjectList.get(i).getTen());
        viewHolder.textHoten.setText(subjectList.get(i).getMota());


        viewHolder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Sửa", Toast.LENGTH_SHORT).show();
                context.dialogUpdate(subjectList.get(i).getId(), subjectList.get(i).getTen(), subjectList.get(i).getMota());

            }
        });
        viewHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                context.dialogDelete(subjectList.get(i).getTen(), subjectList.get(i).getId());
            }
        });
        return view;

    }
}
