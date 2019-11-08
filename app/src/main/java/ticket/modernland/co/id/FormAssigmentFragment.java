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
public class FormAssigmentFragment extends Fragment {


    public FormAssigmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View x = inflater.inflate(R.layout.fragment_form_assigment, container, false);

        final String idticketasg = getArguments().getString("idticketasgnya");


        final EditText tTanggalTampil = (EditText) x.findViewById(R.id.tTanggalTampil);
        tTanggalTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth ) {
                        String date = String.valueOf(year) +"-"+String.valueOf(monthOfYear)
                                +"-"+String.valueOf(dayOfMonth) + " 00:00:00";
                        tTanggalTampil.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        Button bSubmit = (Button) x.findViewById(R.id.bSubmitAsg);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner spn_progress = (Spinner) x.findViewById(R.id.spn_progress);
                EditText tTanggalTampil = (EditText) x.findViewById(R.id.tTanggalTampil);
                EditText tDeskripsiProg = (EditText) x.findViewById(R.id.tDeskripsiProgress);

                String pilihanprog = spn_progress.getSelectedItem().toString();
                String isitanggal = tTanggalTampil.getText().toString();
                String isideskripsi = tDeskripsiProg.getText().toString();

                if(isitanggal.length() == 0) {
                    tTanggalTampil.setError("Tanggal is required");
                    return;
                }
                if(isideskripsi.length() == 0) {
                    tDeskripsiProg.setError("Deskripsi is required");
                    return;
                }

                OkHttpClient postman = new OkHttpClient();

                SharedPreferences sp = getActivity()
                        .getSharedPreferences("DATALOGIN", 0);

                String id_user      = sp.getString("id_user", "");

                RequestBody body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("id_ticket", idticketasg)
                        .addFormDataPart("id_user", id_user)
                        .addFormDataPart("progress", pilihanprog)
                        .addFormDataPart("deskripsi_progress", isideskripsi)
                        .addFormDataPart("selesai", isitanggal)
                        .build();

                Request request = new Request.Builder()
                        .post(body)
                        .url(Setting.IP + "proses_assigment_ticket.php")
                        .build();

                final ProgressDialog pd = new ProgressDialog(
                        getActivity()
                );
                pd.setMessage("Please Wait");
                pd.setTitle("Loading Assigment...");
                pd.setIcon(R.drawable.ic_check_black_24dp);
                pd.show();

                postman.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(),
                                        "Please Try Again",
                                        Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String hasil = response.body().string();
                        try {
                            JSONObject j = new JSONObject(hasil);
                            boolean st = j.getBoolean("status");

                            if(st == false)
                            {
                                final String p = j.getString("pesan");
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(),
                                                p, Toast.LENGTH_LONG).show();
                                        pd.dismiss();
                                    }
                                });
                            }
                            else {

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity().getApplicationContext(),
                                                "Sukses Assigment Ticket",
                                                Toast.LENGTH_LONG).show();

                                        getActivity().getSupportFragmentManager()
                                                .popBackStackImmediate();

                                        pd.dismiss();
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

        Button bCancel = (Button) x.findViewById(R.id.bCancelAsg);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .popBackStackImmediate();

            }
        });

        return x;
    }

}
