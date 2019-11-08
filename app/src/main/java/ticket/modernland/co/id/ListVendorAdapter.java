package ticket.modernland.co.id;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListVendorAdapter extends RecyclerView.Adapter<ListVendorViewHolder> {

    public ArrayList<ListTicketUser> data;

    @NonNull
    @Override
    public ListVendorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View x = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_list_user, viewGroup,
                        false);

        ListVendorViewHolder lv = new ListVendorViewHolder(x);

        return lv;

    }

    @Override
    public void onBindViewHolder(@NonNull ListVendorViewHolder listVendorViewHolder, int i) {

        ListTicketUser lv = data.get(i);
        listVendorViewHolder.et_reported.setText(lv.reported);
        listVendorViewHolder.et_prosummary.setText(lv.problem_summary);
        listVendorViewHolder.et_idticket.setText(lv.id_ticket+ "");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
