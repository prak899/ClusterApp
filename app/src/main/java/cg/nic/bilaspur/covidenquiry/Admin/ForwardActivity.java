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

    Spinner spinner_cluster, spinner_ward, spinner_officer_name;
    EditText editText_cluster, editText_ward, editText_officer_name;
    TextView Forward;

    String SpinnerDistrict, SpinnerWard, SpinnerOfficerName;
    ArrayList<String> getDistrict;
    ArrayList<String> getWard;
    ArrayList<String> getOfficerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward);
        getDistrict= new ArrayList<>();
        getWard= new ArrayList<>();
        getOfficerName= new ArrayList<>();
        init();
        GetDistrict();


        editText_cluster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner_cluster.performClick();
            }
        });
        editText_ward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner_ward.performClick();
            }
        });
        editText_officer_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner_officer_name.performClick();
            }
        });

       /* ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, categorydrop);
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
        });*/
        spinner_cluster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerDistrict = parent.getItemAtPosition(position).toString();
                editText_cluster.setText(SpinnerDistrict);
                GetWard(SpinnerDistrict);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerWard = parent.getItemAtPosition(position).toString();
                editText_ward.setText(SpinnerWard);
                GetOfficerName("urban", "BILASPUR", "BAJRANG NAGAR WARD");
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_officer_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerOfficerName = parent.getItemAtPosition(position).toString();
                editText_officer_name.setText(SpinnerOfficerName);
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
        //Spinner
        spinner_cluster = findViewById(R.id.spinner_cluster);
        spinner_ward = findViewById(R.id.spinner_ward);
        spinner_officer_name = findViewById(R.id.spinner_offname);

        //EditText Spinner
        editText_cluster = findViewById(R.id.editText);
        editText_ward = findViewById(R.id.editText1);
        editText_officer_name = findViewById(R.id.editText2);


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
    public void GetWard(String clusterName){
        getWard.clear();
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url ="https://cg.nic.in/bilaspur/animals/api/clusterlist.php?area_type=urban&districts_name="+clusterName;
        Log.d("XwardX", "GetWard: "+url);

        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Xnew_addressX" ,"sons=="+response);

                try {

                    JSONArray contacts = new JSONArray(response);
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("local_body_name");
                        Log.d("DisName", "onResponse: "+id);

                        // adding contact to contact list
                        getWard.add(id);



                    }
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, getWard);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner_ward.setAdapter(adapter);


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
    public void GetOfficerName(String localType, String clusterName, String wardName){
        getOfficerName.clear();
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);

        String url ="https://cg.nic.in/bilaspur/animals/api/officername.php?district="+clusterName+"&area="
                +localType+"&cluster="+clusterName+"&ward="+wardName;
        Log.d("XwardX", "GetWard: "+url);

        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Xnew_addressX" ,"sons=="+response);

                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonarray = json.getJSONArray("data");

                    //JSONObject c = jsonarray.getJSONObject(0);
                    //String emil= c.getString("email");

                    // looping through All Contacts
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject c = jsonarray.getJSONObject(i);

                        String id = c.getString("name");
                        Log.d("DisName", "onResponse: "+id);

                        // adding contact to contact list
                        getOfficerName.add(id);



                    }
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, getOfficerName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner_officer_name.setAdapter(adapter);


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