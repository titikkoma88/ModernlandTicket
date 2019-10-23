package ticket.modernland.co.id;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ApprovalAdapter extends RecyclerView.Adapter<ApprovalViewHolder> {

    ArrayList<MyTicketUser> data;

    @NonNull
    @Override
    public ApprovalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View x = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_approval, viewGroup,
                        false);

        ApprovalViewHolder ap = new ApprovalViewHolder(x);

        return ap;
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovalViewHolder approvalViewHolder, int i) {

        MyTicketUser ap = data.get(i);
        approvalViewHolder.et_reportedap.setText(ap.reported);
        approvalViewHolder.et_prosummaryap.setText(ap.problem_summary);
        approvalViewHolder.et_idticketap.setText(ap.id_ticket+ "");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
