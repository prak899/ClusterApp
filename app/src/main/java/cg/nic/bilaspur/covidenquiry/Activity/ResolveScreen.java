package cg.nic.bilaspur.covidenquiry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import cg.nic.bilaspur.covidenquiry.R;

public class ResolveScreen extends AppCompatActivity {

    String Address, Type, Description, Location, createdAt, Status, Image;
    TextView ResAddress, ResType, ResDescription, ResLocation, ResCreatedAt, ResStatus;
    ImageView ResImage;
    CheckBox checkBox;
    TextView Resolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolve_screen);
        init();
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Intent intent = getIntent();
        Address = intent.getStringExtra("address");
        Type = intent.getStringExtra("type");
        Description = intent.getStringExtra("description");
        Location = intent.getStringExtra("location");
        createdAt = intent.getStringExtra("createdAt");
        Status = intent.getStringExtra("status");
        Image = intent.getStringExtra("image");


        checkBox.setOnClickListener(v-> {
            if (checkBox.isChecked()) vibe.vibrate(100);

        });
        Resolve.setOnClickListener(v-> {
            startActivity(new Intent(this, SolveScreen.class));

        });
        setValue();
    }

    private void setValue() {
        ResAddress.setText(Address);
        ResType.setText(Type);
        ResDescription.setText(Description);
        ResLocation.setText(Location);
        ResCreatedAt.setText(createdAt);
        ResStatus.setText(Status);

        Glide.with(this).load(Image).into(this.ResImage);
    }

    private void init(){
        ResAddress = findViewById(R.id.address);
        ResType = findViewById(R.id.type);
        ResDescription = findViewById(R.id.description);
        ResLocation = findViewById(R.id.location);
        ResCreatedAt = findViewById(R.id.created_at);
        ResStatus = findViewById(R.id.status);

        ResImage = findViewById(R.id.app_bar_image);

        checkBox = findViewById(R.id.checkbox);
        Resolve = findViewById(R.id.resolve);
    }
}