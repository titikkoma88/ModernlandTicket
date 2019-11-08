package ticket.modernland.co.id;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AssigmentViewHolder extends RecyclerView.ViewHolder {

    TextView et_idticketasg, et_reportedasg, et_prosummaryasg;

    public AssigmentViewHolder(@NonNull final View itemView) {
        super(itemView);

        et_idticketasg = (TextView) itemView.findViewById(R.id.et_idticketasg);
        et_reportedasg = (TextView) itemView.findViewById(R.id.et_reportedasg);
        et_prosummaryasg = (TextView) itemView.findViewById(R.id.et_prosummaryasg);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentAssigmentActivity asg = (ContentAssigmentActivity) itemView.getContext();

                String id = et_idticketasg.getText().toString();

                TicketAssigmentFragment tas = new TicketAssigmentFragment();

                Bundle b = new Bundle();
                b.putString("idnya", id);

                tas.setArguments(b);

                asg.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameAssigment, tas)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }
}
