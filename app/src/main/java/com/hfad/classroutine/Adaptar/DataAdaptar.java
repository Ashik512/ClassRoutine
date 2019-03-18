package com.hfad.classroutine.Adaptar;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.classroutine.MainActivity;
import com.hfad.classroutine.Model.User;
import com.hfad.classroutine.R;
import com.hfad.classroutine.RoutineDesignActivity;

import java.util.List;

public class DataAdaptar extends RecyclerView.Adapter<DataAdaptar.FetchAdapter> {

    Context context;
    List<User> userList;

    public DataAdaptar(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public FetchAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.layoutlist,null);
        return new FetchAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FetchAdapter holder, int i) {

        final User user = userList.get(i);

        holder.subject.setText(user.getSubject());
        holder.teacher.setText(user.getTeacher());
        holder.room_no.setText(user.getRoom_no());
        holder.start_time.setText(user.getStart_time());
        holder.finish_time.setText(user.getFinish_time());
        holder.day_select.setText(user.getDay_select());

       if(!MainActivity.Data.equals("Student")) {
             holder.cardView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     Intent goInput = new Intent(context, RoutineDesignActivity.class);
                     goInput.putExtra("id", user.getId());
                     goInput.putExtra("subject",user.getSubject());
                     goInput.putExtra("teacher",user.getTeacher());
                     goInput.putExtra("room_no",user.getRoom_no());
                     goInput.putExtra("start_time",user.getStart_time());
                     goInput.putExtra("finish_time",user.getFinish_time());
                     goInput.putExtra("day_select",user.getDay_select());

                     context.startActivity(goInput);

                 }
             });
         }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class FetchAdapter extends RecyclerView.ViewHolder
    {

        TextView subject,teacher,room_no,start_time,finish_time,day_select;
        CardView cardView;

        public FetchAdapter(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.model_sub_id);
            teacher = itemView.findViewById(R.id.model_teach_id);
            room_no = itemView.findViewById(R.id.room_id);
            start_time =  itemView.findViewById(R.id.start_id);
            finish_time =  itemView.findViewById(R.id.finish_id);
            day_select =  itemView.findViewById(R.id.day_id);

            cardView = itemView.findViewById(R.id.card_view_id);
        }
    }

}
