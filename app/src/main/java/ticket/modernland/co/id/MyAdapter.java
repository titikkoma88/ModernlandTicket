package ticket.modernland.co.id;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    ArrayList<MyTicketUser> data;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View x = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_my_user, viewGroup,
                        false);

        MyViewHolder mu = new MyViewHolder(x);

        return mu;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        MyTicketUser mu = data.get(i);
        myViewHolder.et_reportedm.setText(mu.reported);
        myViewHolder.et_prosummarym.setText(mu.problem_summary);
        myViewHolder.et_idticketm.setText(mu.id_ticket+ "");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
