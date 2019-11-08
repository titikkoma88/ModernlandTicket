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
public class ListVendorFragment extends Fragment {


    public ListVendorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_list_vendor, container, false);

        final RecyclerView rvListV =(RecyclerView) x.findViewById(R.id.rvListV);

        SharedPreferences sp = getActivity()
                .getSharedPreferences("DATALOGIN", 0);

        String id_user      = sp.getString("id_user", "");
        String app          = sp.getString("app", "");

        //Toast.makeText(getActivity(),
        //        "Welcome " + app, Toast.LENGTH_LONG).show();

        LinearLayoutManager lmlv = new LinearLayoutManager(getActivity());
        lmlv.setOrientation(LinearLayoutManager.VERTICAL);
        rvListV.setLayoutManager(lmlv);

        // 1. postman
        OkHttpClient postman = new OkHttpClient();

        // 2. request
        Request request = new Request.Builder()
                .get()
                .url(Setting.IP + "list_ticket.php?b=" + app)
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
                    JSONArray jlv = new JSONArray(hasil);

                    final ListVendorAdapter adapter = new ListVendorAdapter();
                    adapter.data = new ArrayList<ListTicketUser>();
                    for (int i = 0;i < jlv.length();i++)
                    {
                        JSONObject jo = jlv.getJSONObject(i);
                        ListTicketUser lu = new ListTicketUser();

                        lu.reported = jo.getString("nama");
                        lu.problem_summary = jo.getString("problem_summary");
                        lu.id_ticket = jo.getString("id_ticket");

                        adapter.data.add(lu);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            rvListV.setAdapter(adapter);
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
