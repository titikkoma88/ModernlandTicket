package ticket.modernland.co.id;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AssigmentFragment extends Fragment {


    public AssigmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_assigment, container, false);

        final RecyclerView rvAssigment =(RecyclerView) x.findViewById(R.id.rvAssigment);

        SharedPreferences sp = getActivity()
                .getSharedPreferences("DATALOGIN", 0);

        String id_user      = sp.getString("id_user", "");
        String app          = sp.getString("app", "");

        //Toast.makeText(getActivity(),
        //        "Welcome " + app + "  " + id_user, Toast.LENGTH_LONG).show();

        LinearLayoutManager lmm = new LinearLayoutManager(getActivity());
        lmm.setOrientation(LinearLayoutManager.VERTICAL);
        rvAssigment.setLayoutManager(lmm);

        // 1. postman
        OkHttpClient postman = new OkHttpClient();

        // 2. request
        Request request = new Request.Builder()
                .get()
                .url(Setting.IP + "assigment_ticket.php?a=" + id_user + "&" + "b=" + app)
                .build();

        // 3. progress dialog
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait");
        pd.setTitle("Loading ...");
        pd.setIcon(R.drawable.ic_check_black_24dp);
        pd.setCancelable(false);
        pd.show();

        // 4. send
        postman.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Please Try Again",
                                Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String hasil = response.body().string();
                try {
                    JSONArray jm = new JSONArray(hasil);

                    final AssigmentAdapter adapter = new AssigmentAdapter();
                    adapter.data = new ArrayList<MyTicketUser>();
                    for (int i = 0;i < jm.length();i++)
                    {
                        JSONObject joasg = jm.getJSONObject(i);
                        MyTicketUser asg = new MyTicketUser();

                        asg.reported = joasg.getString("nama");
                        asg.problem_summary = joasg.getString("problem_summary");
                        asg.id_ticket = joasg.getString("id_ticket");

                        adapter.data.add(asg);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            rvAssigment.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return x;
    }

}
