package ticket.modernland.co.id;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView et_idticketm, et_reportedm, et_prosummarym;

    public MyViewHolder(@NonNull final View itemView) {
        super(itemView);

        et_idticketm = (TextView) itemView.findViewById(R.id.et_idticketm);
        et_reportedm = (TextView) itemView.findViewById(R.id.et_reportedm);
        et_prosummarym = (TextView) itemView.findViewById(R.id.et_prosummarym);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentMyActivity m = (ContentMyActivity) itemView.getContext();

                String id = et_idticketm.getText().toString();

                TicketDetailFragment df = new TicketDetailFragment();

                Bundle b = new Bundle();
                b.putString("idnya", id);

                df.setArguments(b);

                m.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameMyUser, df)
                        .addToBackStack(null)
                        .commit();

            }
        });

    }
}
