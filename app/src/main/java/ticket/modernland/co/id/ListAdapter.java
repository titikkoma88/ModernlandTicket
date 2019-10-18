package ticket.modernland.co.id;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

    public ArrayList<ListTicketUser> data;

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View x = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_list_user, viewGroup,
                        false);

        ListViewHolder lu = new ListViewHolder(x);

        return lu;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {

        ListTicketUser lu = data.get(i);
        listViewHolder.et_reported.setText(lu.reported);
        listViewHolder.et_prosummary.setText(lu.problem_summary);
        listViewHolder.et_idticket.setText(lu.id_ticket+ "");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
