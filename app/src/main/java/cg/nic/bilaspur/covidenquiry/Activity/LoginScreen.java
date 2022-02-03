package cg.nic.bilaspur.covidenquiry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cg.nic.bilaspur.covidenquiry.Class.PM;
import cg.nic.bilaspur.covidenquiry.R;

public class LoginScreen extends AppCompatActivity{

    PM pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        init();


    }
    public void signIn(View v){
        startActivity(new Intent(this, CovidEntry.class));
        /*if (pm.checkInfo().equals("nameismajhi"))
            pm.startSound();
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();*/
    }
    public void init(){
        pm = new PM();
    }

}
