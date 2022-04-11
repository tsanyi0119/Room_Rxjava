package com.example.room_mvp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_mvp.room.MyData;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> implements Contract.IMyadapter{
    private Contract.IMain callback;
    public Myadapter(Contract.IMain callback){this.callback = callback;};
    List<MyData> arrayList = new ArrayList<MyData>();
    MyData modifyData;
    @Override
    public void dataView(List<MyData>myData) {
        this.arrayList = myData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView thisID,roomID,name,phone;
        public ViewHolder(@NonNull View itemView)  {
            super(itemView);
            thisID = itemView.findViewById(R.id.thisID);
            roomID = itemView.findViewById(R.id.roomID);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modifyData = new MyData(arrayList.get(getAdapterPosition()).getId(),
                            arrayList.get(getAdapterPosition()).getName(),
                            arrayList.get(getAdapterPosition()).getPhone(),
                            arrayList.get(getAdapterPosition()).getHobby(),
                            arrayList.get(getAdapterPosition()).getElseInfo());
                    callback.dataModify(modifyData);
                }
            });
        }

        void bindData(MyData myData,int position) {
                thisID.setText(String.valueOf(position));
                roomID.setText(String.valueOf(myData.getId()));
                name.setText(myData.getName());
                phone.setText(myData.getPhone());
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item,parent,false);




        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Myadapter.ViewHolder holder, int position) {
        holder.bindData(arrayList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
