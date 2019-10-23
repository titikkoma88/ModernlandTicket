package ticket.modernland.co.id;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ApprovalViewHolder extends RecyclerView.ViewHolder {

    TextView et_idticketap, et_reportedap, et_prosummaryap;

    public ApprovalViewHolder(@NonNull final View itemView) {
        super(itemView);

        et_idticketap = (TextView) itemView.findViewById(R.id.et_idticketap);
        et_reportedap = (TextView) itemView.findViewById(R.id.et_reportedap);
        et_prosummaryap = (TextView) itemView.findViewById(R.id.et_prosummaryap);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentApprovalActivity ap = (ContentApprovalActivity) itemView.getContext();

                String id = et_idticketap.getText().toString();

                TicketApproveFragment ta = new TicketApproveFragment();

                Bundle b = new Bundle();
                b.putString("idnya", id);

                ta.setArguments(b);

                ap.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameApprove, ta)
                        .addToBackStack(null)
                        .commit();

            }
        });

    }
}
