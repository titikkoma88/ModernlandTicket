package ticket.modernland.co.id;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ListViewHolder extends RecyclerView.ViewHolder {

    TextView et_idticket, et_reported, et_prosummary;

    public ListViewHolder(@NonNull final View itemView) {
        super(itemView);

        et_idticket = (TextView) itemView.findViewById(R.id.et_idticket);
        et_reported = (TextView) itemView.findViewById(R.id.et_reported);
        et_prosummary = (TextView) itemView.findViewById(R.id.et_prosummary);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentListActivity l = (ContentListActivity) itemView.getContext();

                String id = et_idticket.getText().toString();

                TicketDetailFragment df = new TicketDetailFragment();

                Bundle b = new Bundle();
                b.putString("idnya", id);

                df.setArguments(b);

                l.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameListUser, df)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
