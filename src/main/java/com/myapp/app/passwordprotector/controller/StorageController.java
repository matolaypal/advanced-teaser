package com.myapp.app.passwordprotector.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import com.myapp.app.passwordprotector.model.Account;

/**
 * Responsible for the storing in {@link SharedPreferences}.
 */
public class StorageController {
    /**
     * Tag for logging.
     */
    private final static String TAG = StorageController.class.getSimpleName() + "<> ";
    /**
     * Initializing the storage.
     */
    private final static String PREFS_KEY = "com.myapp.app.passwordprotector.preferences";
    private static SharedPreferences mSharedPreferences;

    /**
     * Setting the storage.
     *
     * @param context "God" object from an activity.
     */
    public StorageController(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Load a string.
     *
     * @param key reference for the required String
     * @param defaultReturnValue when the key doesn't exist
     * @return result from storage
     */
    public  String getStoredString(String key, String defaultReturnValue) {
        return mSharedPreferences.getString(key, defaultReturnValue);
    }

    /**
     * Save a string.
     *
     * @param key reference for the data
     * @param data String value
     */
    public void setStoringString(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
        Log.i(TAG, "String saved successfully with this key: " + key);
    }

    /**
     * Load the stored keys ({@link Account}) quantity.
     *
     * @param defaultReturnValue when the key doesn't exist
     * @return result from storage
     */
    public Integer getAccountQuantity(Integer defaultReturnValue) {
        return mSharedPreferences.getInt("COUNT_KEY", defaultReturnValue);
    }

    /**
     * Save the given keys quantity.
     *
     * @param data Integer value
     */
    public void setAccountQuantity(Integer data) {
        mSharedPreferences.edit().putInt("COUNT_KEY", data).apply();
        Log.i(TAG, "Stored accounts quantity: " + data);
    }

    /**
     * Load a key ({@link Account}).
     *
     * @param key reference for the required String.
     * @param defaultReturnValue when the key doesn't exist
     * @return result from storage
     */
    public Account getStoredAccount(String key, String defaultReturnValue) {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(key, defaultReturnValue);
        return gson.fromJson(json, Account.class);
    }

    /**
     * Save a key ({@link Account}).
     *
     * @param key reference for the required Account
     * @param account Account object
     */
    public void setStoredAccount(String key, Account account) {
        Gson gson = new Gson();
        String json = gson.toJson(account);
        mSharedPreferences.edit().putString(key, json).apply();
        Log.i(TAG, "Account saved successfully with this key: " + key);
    }

    /**
     * !NOTE! Recently this is dead code.
     * TODO: Fixing bug!
     *
     * Delete a key ({@link Account}).
     *
     * @param key reference for the required Account
     */
    public void removeAccount(String key) {
        mSharedPreferences.edit().remove(key).apply();
        Log.i(TAG, "Account removed successfully with this key: " + key);
    }
}
