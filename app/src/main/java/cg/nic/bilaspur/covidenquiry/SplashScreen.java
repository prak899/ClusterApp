package cg.nic.bilaspur.covidenquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cg.nic.bilaspur.covidenquiry.Activity.LoginScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView= findViewById(R.id.tv_splash_app_title);
        TextView textView1= findViewById(R.id.tv_splash_app_version);

        try{
            String versionName = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0).versionName;
            textView1.setText("V "+ versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            int count = 0;

            @Override
            public void run() {
                count++;

                if (count == 1)
                {
                    textView.setText("Loading.");
                }
                else if (count == 2)
                {
                    textView.setText("Loading..");
                }
                else if (count == 3)
                {
                    textView.setText("Loading...");
                }

                if (count == 3)
                    Load();

                handler.postDelayed(this, 2 * 1000);
            }
        };
        handler.postDelayed(runnable, 1 * 1000);

    }

    public void Load(){
        /*if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(Spalsh.this, NearBy.class));
            Spalsh.this.finish();
        } else {
            startActivity(new Intent(Spalsh.this, DashBoard.class));
        }*/
        startActivity(new Intent(SplashScreen.this, LoginScreen.class));
    }
}