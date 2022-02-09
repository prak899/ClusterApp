package cg.nic.bilaspur.covidenquiry.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cg.nic.bilaspur.covidenquiry.Adapter.AccidentAdapter;
import cg.nic.bilaspur.covidenquiry.Model.Accident;
import cg.nic.bilaspur.covidenquiry.R;

interface levels{
    void lowLevel();
}

public class CovidEntry extends AppCompatActivity implements levels{

    ProgressDialog dialog_pd = null;

    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Accident> accidentList;
    private RecyclerView.Adapter adapter;

    ProgressBar progressBar;
    String Address, Type, Description, Location, createdAt, Status, Image;

    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_entry);


        init();

        getData("8847845697", "Pending");

/*        if (!Address.isEmpty() || !Type.isEmpty() || !Description.isEmpty() || !Location.isEmpty() || !createdAt.isEmpty() || !Status.isEmpty() || !Image.equals("")) {
            Intent intent = getIntent();
            Address = intent.getStringExtra("address");
            Type = intent.getStringExtra("type");
            Description = intent.getStringExtra("description");
            Location = intent.getStringExtra("location");
            createdAt = intent.getStringExtra("createdAt");
            Status = intent.getStringExtra("status");
            Image = intent.getStringExtra("image");
        }else Toast.makeText(this, "Kindly login with admin", Toast.LENGTH_SHORT).show();*/
    }

    private void init() {
        recyclerView= findViewById(R.id.layout_status);


        accidentList = new ArrayList<>();
        adapter = new AccidentAdapter(getApplicationContext(),accidentList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        progressBar= findViewById(R.id.progressBar);
        lottieAnimationView = findViewById(R.id.lottie_main);
    }

    @Override
    public void lowLevel() {
        Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
    }

    private void getData(String number, String Status_type) {
        accidentList.clear();
        //progressBar.setVisibility(View.VISIBLE);
        lottieAnimationView.setVisibility(View.VISIBLE);

        String url = "https://cg.nic.in/bilaspur/animals/api/statusType.php?mobile_no="+number+"&status_type="+Status_type;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(JSONArray response) {
                accidentList.clear();
                for (int i = 0; i < response.length(); i++) {

                    try {
                        Log.d("XlogX", url);
                        //JSONArray jsonArray = response.getJSONArray(i);
                        JSONObject jsonObject = response.getJSONObject(i);
                        Accident status =new Accident();


                        status.setAddress(jsonObject.getString("address"));
                        status.setType(jsonObject.getString("type"));
                        status.setDescription(jsonObject.getString("description"));
                        status.setLocation(jsonObject.getString("landmark"));
                        status.setCreatedAt(jsonObject.getString("created_at"));
                        status.setStatus(jsonObject.getString("status"));

                        String photo = jsonObject.getString("photo");
                        byte[] data = Base64.decode(photo, Base64.DEFAULT);

                        status.setPhoto(data);

                        accidentList.add(status);
                        Log.d("XlogX", String.valueOf(status));
                    } catch (JSONException e) {
                        Log.e("CatcjhXxx", e.getMessage());
                        //progressBar.setVisibility(View.GONE);
                        lottieAnimationView.setVisibility(View.GONE);
                        //progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                //progressBar.setVisibility(View.GONE);
                lottieAnimationView.setVisibility(View.GONE);
                //progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                accidentList.clear();
                Log.e("Volley", error.toString());
                //progressBar.setVisibility(View.GONE);
                lottieAnimationView.setVisibility(View.GONE);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    public void adminForwardAccident(){
        /*LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);



        TextView forwardAddress = (TextView) alertLayout.findViewById(R.id.address);
        TextView forwardType = (TextView) alertLayout.findViewById(R.id.type);
        TextView forwardDescription = (TextView) alertLayout.findViewById(R.id.description);
        TextView forwardLocation = (TextView) alertLayout.findViewById(R.id.location);
        TextView forwardCreatedAt = (TextView) alertLayout.findViewById(R.id.created_at);
        TextView forwardStatus = (TextView) alertLayout.findViewById(R.id.status);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Forward Accident");
        alert.setView(alertLayout);
        alert.setCancelable(false);




        forwardAddress.setText(Address);
        forwardType.setText(Type);
        forwardDescription.setText(Description);
        forwardLocation.setText(Location);
        forwardCreatedAt.setText(createdAt);
        forwardStatus.setText(Status);



        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("Forward now!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog_pd = ProgressDialog.show(CovidEntry.this, "",
                        "Please wait...", true);
                new Thread(new Runnable() {
                    public void run() {
                        //feedbackNIC(feedback.getText().toString(), name, number);
                    }
                }).start();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();*/
        Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
    }
}