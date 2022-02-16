package cg.nic.bilaspur.covidenquiry.Class;

/**
 * Created by Prakash on 2/16/2022.
 */
public class Config {
    //URL to our login.php file
    public static final String LOGIN_URL = "https://cg.nic.in/bilaspur/animals/api/officerlogin.php?officerid=bilaspur_controller1&officer_pass=314231c56ffa234f6c8ac6d661e3784a";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
