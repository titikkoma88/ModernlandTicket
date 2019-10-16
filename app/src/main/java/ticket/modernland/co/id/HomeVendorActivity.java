package ticket.modernland.co.id;

import android.content.DialogInterface;
import android.content.Intent;
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

public class HomeVendorActivity extends AppCompatActivity {

    Button btnpro, btnlo, btnexit;
    RelativeLayout maincontent;
    LinearLayout mainmenu, btnmenu;
    Animation fromtop, frombottom;
    ImageView userpicbig, menuapro, menuassign, menulistvend;
    TextView tvdashboard, tvsupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_vendor);

        //btnmenu = (Button) findViewById(R.id.btnmenu);

        btnmenu = (LinearLayout) findViewById(R.id.btnmenu);

        btnpro = (Button) findViewById(R.id.btnPro);
        btnlo = (Button) findViewById(R.id.btnLo);
        btnexit = (Button) findViewById(R.id.btnExit);

        tvdashboard = (TextView) findViewById(R.id.tvDashboard);
        tvsupport = (TextView) findViewById(R.id.tvSupport);

        userpicbig = (ImageView) findViewById(R.id.userpicbig);
        menuapro = (ImageView) findViewById(R.id.menuApro);
        menuassign = (ImageView) findViewById(R.id.menuAssign);
        menulistvend = (ImageView) findViewById(R.id.menuListVend);

        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        Toast.makeText(this,"Vendor Home", Toast.LENGTH_LONG).show();

        maincontent = (RelativeLayout) findViewById(R.id.maincontent);
        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);

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

                //maincontent.animate().translationX(-750);
                //mainmenu.animate().translationX(-750);

                maincontent.animate().translationX(-1000);
                mainmenu.animate().translationX(-1000);
            }
        });

        menuapro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeVendorActivity.this,"Menu Approval", Toast.LENGTH_LONG).show();
            }
        });

        menuassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeVendorActivity.this,"Menu Assigment", Toast.LENGTH_LONG).show();
            }
        });

        menulistvend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeVendorActivity.this,"Menu List Vendor", Toast.LENGTH_LONG).show();
            }
        });

    }
}
