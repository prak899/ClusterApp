package cg.nic.bilaspur.covidenquiry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import cg.nic.bilaspur.covidenquiry.Admin.ForwardActivity;
import cg.nic.bilaspur.covidenquiry.Class.Config;
import cg.nic.bilaspur.covidenquiry.Class.PM;
import cg.nic.bilaspur.covidenquiry.R;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{

    PM pm;

    //Defining views
    private EditText editTextUser;
    private EditText editTextPassword;
    private TextView buttonLogin;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    public static final String LOGIN_URL = "https://cg.nic.in/bilaspur/animals/api/officerlogin.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        init();


    }
    public void signIn(View v){
        startActivity(new Intent(this, CovidEntry.class));
    }
    public void signIn1(View v){
        startActivity(new Intent(this, ForwardActivity.class));
    }
    public void init(){
        pm = new PM();

        //Initializing views
        editTextUser = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        buttonLogin = (TextView) findViewById(R.id.tv_btn_login1);


        //Adding click listener
        //buttonLogin.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(this, CovidEntry.class);
            startActivity(intent);
        }
    }

    private void login(){
        //Getting values from edit texts
        final String userName = editTextUser.getText().toString().trim();
        final String password = md5(editTextPassword.getText().toString().trim());

        //md5(editTextPassword.getText().toString().trim());
        Log.d("Xmd5X", "login: "+password);
        String url = "https://cg.nic.in/bilaspur/animals/api/officerlogin.php?officerid="+userName+"&officer_pass="+"C473D8FE60227ECF9EDE3D8FE96E9DAF";
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("XurlX", "onResponse: "+url);
                        JSONObject json = null;
                        try {
                            json = new JSONObject(response);
                            //String status= json.getString("status");
                            JSONArray jsonarray = json.getJSONArray("data");

                            JSONObject c = jsonarray.getJSONObject(0);
                            String status= c.getString("status");

                            if(status.equalsIgnoreCase("success")){

                                SharedPreferences sharedPreferences = LoginScreen.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(Config.EMAIL_SHARED_PREF, userName);

                                editor.commit();
                                String PrivilegeLevel= c.getString("privilege_level");
                                if (PrivilegeLevel.equalsIgnoreCase("1")) {
                                    //Starting profile activity
                                    Intent intent = new Intent(LoginScreen.this, CovidEntry.class);
                                    startActivity(intent);
                                } else if (PrivilegeLevel.equalsIgnoreCase("0")){
                                    Intent intent = new Intent(LoginScreen.this, ForwardActivity.class);
                                    startActivity(intent);
                                }
                            }else{

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.d("XerrorX", "onErrorResponse: "+error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_EMAIL, userName);
                params.put(Config.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        //Calling the login function
        //login();
    }
    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
