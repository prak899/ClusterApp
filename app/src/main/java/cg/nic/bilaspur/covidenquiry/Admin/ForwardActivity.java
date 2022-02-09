package cg.nic.bilaspur.covidenquiry.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cg.nic.bilaspur.covidenquiry.Activity.SolveScreen;
import cg.nic.bilaspur.covidenquiry.R;

public class ForwardActivity extends AppCompatActivity {

    Spinner spinner, spinner_cluster;
    EditText editText, editText1;
    String[] categorydrop = {"MALE", "FEMALE"};
    TextView Forward;

    String SpinnerDistrict;
    ArrayList<String> getDistrict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward);
        getDistrict= new ArrayList<>();
        init();
        GetDistrict();


        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.performClick();
            }
        });
        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner_cluster.performClick();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, categorydrop);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String catagory= spinner.getSelectedItem().toString();
                editText.setText(catagory);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_cluster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerDistrict = parent.getItemAtPosition(position).toString();
                editText1.setText(SpinnerDistrict);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Forward.setOnClickListener(v-> {
            startActivity(new Intent(this, SolveScreen.class));

        });
    }

    private void init() {
        spinner = findViewById(R.id.spinner_cluster);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);

        spinner_cluster = findViewById(R.id.spinner_ward);
        Forward = findViewById(R.id.forward);


    }

    public void GetDistrict(){
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);

        String url = "https://cg.nic.in/bilaspur/animals/api/districts.php";
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Xnew_addressX" ,"sons=="+response);

                try {

                    JSONArray contacts = new JSONArray(response);
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("district_name");
                        Log.d("DisName", "onResponse: "+id);

                        // adding contact to contact list
                        getDistrict.add(id);



                    }
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, getDistrict);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    /*ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getDistrict);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                    spinner_cluster.setAdapter(adapter);


                } catch (final JSONException e) {
                    Log.e("TAG", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        ExampleRequestQueue.add(ExampleStringRequest);

    }
}