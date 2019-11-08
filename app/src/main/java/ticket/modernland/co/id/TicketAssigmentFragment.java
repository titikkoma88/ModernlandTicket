package ticket.modernland.co.id;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TicketAssigmentFragment extends Fragment {

    public TicketAssigmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View x = inflater.inflate(R.layout.fragment_ticket_assigment, container, false);

        final String id = getArguments().getString("idnya");

        final TextView tIdTiket = (TextView) x.findViewById(R.id.tIdTiketAsg);
        final TextView tUser = (TextView) x.findViewById(R.id.tUserAsg);
        final TextView tTanggal = (TextView) x.findViewById(R.id.tTanggalAsg);
        final TextView tKategori = (TextView) x.findViewById(R.id.tKategoriAsg);
        final TextView tSubKategori = (TextView) x.findViewById(R.id.tSubKategoriAsg);
        final TextView tDeskripsi = (TextView) x.findViewById(R.id.tDeskripsiAsg);

        OkHttpClient postman = new OkHttpClient();

        Request r = new Request.Builder()
                .get()
                .url(Setting.IP + "get_ticket.php?a=" + id)
                .build();

        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait ...");
        pd.setTitle("Loading Ticket ...");
        pd.setIcon(R.drawable.ic_check_black_24dp);
        pd.setCancelable(true);
        pd.show();

        Toast.makeText(getActivity(),
                "Assigmnet Ticket No " + id,
                Toast.LENGTH_LONG).show();

        postman.newCall(r).enqueue(new Callback() {
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
                    JSONObject jo = new JSONObject(hasil);

                    final String id_ticket = jo.getString("id_ticket");
                    final String tanggal = jo.getString("tanggal");
                    final String nama = jo.getString("nama");
                    final String nama_kategori = jo.getString("nama_kategori");
                    final String nama_sub_kategori = jo.getString("nama_sub_kategori");
                    final String problem_summary = jo.getString("problem_summary");
                    final String problem_detail = jo.getString("problem_detail");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tIdTiket.setText(id_ticket);
                            tTanggal.setText(tanggal);
                            tUser.setText(nama);
                            tKategori.setText(nama_kategori);
                            tSubKategori.setText(nama_sub_kategori);
                            tDeskripsi.setText(problem_detail);

                            pd.dismiss();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Button bUpdate = (Button) x.findViewById(R.id.bUpdateAsg);
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idticket = tIdTiket.getText().toString();

                FormAssigmentFragment faf = new FormAssigmentFragment();

                Bundle bf = new Bundle();
                bf.putString("idticketasgnya",idticket);

                faf.setArguments(bf);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameAssigment, faf)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return x;
    }

}
