package cg.nic.bilaspur.covidenquiry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cg.nic.bilaspur.covidenquiry.R;

public class SolveScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_screen);
    }
    public void DONE(View v){
        startActivity(new Intent(this, LoginScreen.class));

    }
}