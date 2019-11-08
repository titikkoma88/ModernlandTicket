package ticket.modernland.co.id;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.nikartm.support.BadgePosition;
import ru.nikartm.support.ImageBadgeView;

public class HomeVendorActivity extends AppCompatActivity {

    private ImageBadgeView imageBadgeViewApro, imageBadgeViewAssign;

    private int value = 0;

    Button btnpro, btnlo, btnexit;
    RelativeLayout maincontent;
    LinearLayout mainmenu, btnmenu;
    Animation fromtop, frombottom;
    ImageView userpicbig, menulistvend;
    TextView tvdashboard, tvsupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_vendor);

        //btnmenu = (Button) findViewById(R.id.btnmenu);

        imageBadgeViewApro = findViewById(R.id.ibv_approve);
        imageBadgeViewAssign = findViewById(R.id.ibv_Assign);

        btnmenu = (LinearLayout) findViewById(R.id.btnmenu);

        btnpro = (Button) findViewById(R.id.btnPro);
        btnlo = (Button) findViewById(R.id.btnLo);
        btnexit = (Button) findViewById(R.id.btnExit);

        tvdashboard = (TextView) findViewById(R.id.tvDashboard);
        tvsupport = (TextView) findViewById(R.id.tvSupport);

        userpicbig = (ImageView) findViewById(R.id.userpicbig);
        menulistvend = (ImageView) findViewById(R.id.menuListVend);

        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        //Toast.makeText(this,"Vendor Home", Toast.LENGTH_LONG).show();

        maincontent = (RelativeLayout) findViewById(R.id.maincontent);
        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);

        SharedPreferences sp = getSharedPreferences("DATALOGIN", 0);

        String id_user  = sp.getString("id_user", "");
        String app      = sp.getString("app", "");

        OkHttpClient postman = new OkHttpClient();

        Request request = new Request.Builder()
                .get()
                .url(Setting.IP + "counter_approval.php?a=" + id_user + "&" + "b=" + app)
                .build();

        final ProgressDialog pd = new ProgressDialog(HomeVendorActivity.this);
        pd.setMessage("Please wait");
        pd.setTitle("Loading ...");
        pd.setIcon(R.drawable.ic_check_black_24dp);
        pd.setCancelable(false);
        pd.show();

        postman.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
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
                    JSONObject j = new JSONObject(hasil);
                    boolean st = j.getBoolean("status");
                    final int approve = j.getInt("approve");
                    final int assigment = j.getInt("assigment");

                    if(st == false)
                    {
                        final String p = j.getString("pesan");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        p, Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        });
                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();

                                initIconWithBadgesApro(approve);
                                initIconWithBadgesAssign(assigment);

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        btnlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder ab = new AlertDialog.Builder(HomeVendorActivity.this);

                ab.create();
                ab.setTitle("Confirmation");
                ab.setIcon(R.drawable.ic_check_black_24dp);
                ab.setMessage("Are you sure to logout?");
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        getSharedPreferences("DATALOGIN", MODE_PRIVATE)
                                .edit().clear().commit();

                        Intent i = new Intent(HomeVendorActivity.this, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                ab.show();

            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder ab = new android.app.AlertDialog.Builder(HomeVendorActivity.this);

                ab.create();
                ab.setTitle("Confirmation");
                ab.setIcon(R.drawable.ic_check_black_24dp);
                ab.setMessage("Are you sure to exit?");
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finishAffinity();

                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                ab.show();
            }
        });

        btnpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeVendorActivity.this,"Menu Profile", Toast.LENGTH_LONG).show();
            }
        });

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincontent.animate().translationX(0);
                mainmenu.animate().translationX(0);

                btnpro.startAnimation(frombottom);
                btnlo.startAnimation(frombottom);
                btnexit.startAnimation(frombottom);

                tvdashboard.startAnimation(fromtop);
                tvsupport.startAnimation(fromtop);
                userpicbig.startAnimation(fromtop);

            }
        });

        maincontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //maincontent.animate().translationX(-990);
                //mainmenu.animate().translationX(-990);

                maincontent.animate().translationX(-600);
                mainmenu.animate().translationX(-600);

                //maincontent.animate().translationX(-1000);
                //mainmenu.animate().translationX(-1000);
            }
        });

        menulistvend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeVendorActivity.this,"Menu List Vendor", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void menuApro(View view) {

        Intent i = new Intent(HomeVendorActivity.this, ContentApprovalActivity.class);
        startActivity(i);

    }

    public void menuAssign(View view) {

        Intent i = new Intent(HomeVendorActivity.this, ContentAssigmentActivity.class);
        startActivity(i);

    }

    // Initialize a badge programmatically
    private void initIconWithBadgesApro(int approve) {
        value = approve;
        Typeface typeface = Typeface.createFromAsset(getAssets(), "exo_regular.ttf");
        imageBadgeViewApro.setBadgeValue(value)
                .setBadgeOvalAfterFirst(true)
                .setBadgeTextSize(16)
                .setMaxBadgeValue(999)
                .setBadgeTextFont(typeface)
                .setBadgeBackground(getResources().getDrawable(R.drawable.rectangle_rounded))
                .setBadgePosition(BadgePosition.TOP_RIGHT)
                .setBadgeTextStyle(Typeface.NORMAL)
                .setShowCounter(true)
                .setBadgePadding(4);
    }

    private void initIconWithBadgesAssign(int assigment) {
        value = assigment;
        Typeface typeface = Typeface.createFromAsset(getAssets(), "exo_regular.ttf");
        imageBadgeViewAssign.setBadgeValue(value)
                .setBadgeOvalAfterFirst(true)
                .setBadgeTextSize(16)
                .setMaxBadgeValue(999)
                .setBadgeTextFont(typeface)
                .setBadgeBackground(getResources().getDrawable(R.drawable.rectangle_rounded))
                .setBadgePosition(BadgePosition.TOP_RIGHT)
                .setBadgeTextStyle(Typeface.NORMAL)
                .setShowCounter(true)
                .setBadgePadding(4);
    }


}
