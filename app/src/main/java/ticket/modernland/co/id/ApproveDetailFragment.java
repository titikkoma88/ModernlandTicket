package ticket.modernland.co.id;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApproveDetailFragment extends Fragment {


    public ApproveDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_approve_detail, container, false);

        Toast.makeText(getActivity(),
                "Welcome Detail Approval", Toast.LENGTH_LONG).show();

        return x;
    }

}
