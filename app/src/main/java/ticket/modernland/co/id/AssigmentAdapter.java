package ticket.modernland.co.id;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AssigmentAdapter extends RecyclerView.Adapter<AssigmentViewHolder> {

    ArrayList<MyTicketUser> data;

    @NonNull
    @Override
    public AssigmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View x = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_assigment, viewGroup,
                        false);

        AssigmentViewHolder asg = new AssigmentViewHolder(x);

        return asg;
    }

    @Override
    public void onBindViewHolder(@NonNull AssigmentViewHolder assigmentViewHolder, int i) {

        MyTicketUser asg = data.get(i);
        assigmentViewHolder.et_reportedasg.setText(asg.reported);
        assigmentViewHolder.et_prosummaryasg.setText(asg.problem_summary);
        assigmentViewHolder.et_idticketasg.setText(asg.id_ticket+ "");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
