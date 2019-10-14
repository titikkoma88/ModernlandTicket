package ticket.modernland.co.id;

import android.provider.ContactsContract;
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

    Button btnpro, btnlo;
    RelativeLayout maincontent;
    LinearLayout mainmenu, btnmenu;
    Animation fromtop, frombottom;
    ImageView userpicbig, menuapro;
    TextView tvdashboard, tvsupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_vendor);

        //btnmenu = (Button) findViewById(R.id.btnmenu);

        btnmenu = (LinearLayout) findViewById(R.id.btnmenu);

        btnpro = (Button) findViewById(R.id.btnPro);
        btnlo = (Button) findViewById(R.id.btnLO);

        tvdashboard = (TextView) findViewById(R.id.tvDashboard);
        tvsupport = (TextView) findViewById(R.id.tvSupport);

        userpicbig = (ImageView) findViewById(R.id.userpicbig);
        menuapro = (ImageView) findViewById(R.id.menuApro);

        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        Toast.makeText(this,"Vendor Home", Toast.LENGTH_LONG).show();

        maincontent = (RelativeLayout) findViewById(R.id.maincontent);
        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincontent.animate().translationX(0);
                mainmenu.animate().translationX(0);

                btnpro.startAnimation(frombottom);
                btnlo.startAnimation(frombottom);

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

    }
}
