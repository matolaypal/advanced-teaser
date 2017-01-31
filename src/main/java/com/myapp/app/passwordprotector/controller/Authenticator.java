package com.myapp.app.passwordprotector.controller;

import android.content.Context;
import android.util.Log;

/**
 * Responsible for (someday secured) password storing and
 * authorizations management inside the app.
 * TODO: Focus better and more security!
 */
public class Authenticator {

    /**
     * Tag for logging.
     */
    private final static String TAG = Authenticator.class.getSimpleName()+"<> ";

    /**
     * Define necessary fields.
     */
    private String userName;
    private String password;
    private StorageController storageController;
    private String storedPassword;

    /**
     * The ultimate guardian.
     * Work together with {@link #storageController}
     *
     * @param context "God" object from an activity
     * @param userName login data to app
     * @param password login data to app
     */
    public Authenticator(Context context, String userName, String password) {
        storageController = new StorageController(context);
        storedPassword = storageController.getStoredString( "USER_PW", null);
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return result from the right method
     */
    public Boolean authenticate(){
        if (storedPassword==null){
            return registration();
        } else {
            return login();
        }
    }

    /**
     * @return <b>null</b> (if some input field is empty) or <b>true</b> (if login data stored)
     * Work with {@link #storageController}
     */
    private Boolean registration(){
        if (userName.equals("") || password.equals("")) {
            Log.i(TAG, "Registration was unsuccessful!");
            return null;
        }
        storageController.setStoringString("USER_NAME", userName);
        storageController.setStoringString("USER_PW", password);
        Log.i(TAG, "Registration was successful!");
        return true;
    }

    /**
     * @return <b>null</b> (if input is empty) or <b>true/false</b> (compared stored and given password)
     */
     private Boolean login() {
         if (password.equals("")) {
             return null;
         }
         return storedPassword.equals(password);
     }

    /**
     * Encrypt any string.
     * <b><u>DEAD CODE!</u></b>
     * TODO: Rebirth!
     * @param s any String data
     * @return null
     */
    public static String encrypt(String s) {
        return null;
    }
}
