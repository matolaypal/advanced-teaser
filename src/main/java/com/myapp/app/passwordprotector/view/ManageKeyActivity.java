package com.myapp.app.passwordprotector.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.myapp.app.passwordprotector.R;
import com.myapp.app.passwordprotector.controller.StorageController;
import com.myapp.app.passwordprotector.model.Account;

/**
 * This activity start when the user clicked the "add key" button in the menu.
 * Responsible for managing views on the screen
 */
public class ManageKeyActivity extends Activity {
    /**
     * Tag for logging.
     */
    private final String TAG = ManageKeyActivity.class.getSimpleName()+"<> ";

    /**
     * Define input fields.
     */
    private EditText mNewAccount;
    private EditText mNewUserNAme;
    private EditText mNewPassword;
    private Button mAddButton;

    /**
     * Find views, then set a listener on {@link #mAddButton},
     * what create a new key:
     * @see Account
     * Load the stored key quantity,
     * then save the new key and increment quantity
     * @see StorageController
     *
     * @param savedInstanceState get by system
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keymanage);

        Log.i(TAG, "Activity created!");

        mNewAccount = (EditText) findViewById(R.id.newAccountText);
        mNewUserNAme = (EditText) findViewById(R.id.newUserText);
        mNewPassword = (EditText) findViewById(R.id.newPasswordText);
        mAddButton = (Button) findViewById(R.id.addButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Account account = new Account(
                        mNewAccount.getText().toString(),
                        mNewUserNAme.getText().toString(),
                        mNewPassword.getText().toString());

                // Save/load ~~~
                StorageController storageController = new StorageController(ManageKeyActivity.this);
                Integer i = storageController.getAccountQuantity(0)+1;
                String key = "ACC_KEY" + i;
                storageController.setStoredAccount(key, account);
                account.setStoreKey(key);
                storageController.setAccountQuantity(i);
                finish();
            }
        });
    }

    /**
     * Implemented just for logging, yet!
     * (Because it necessary.)
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Activity paused!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Activity stopped!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Activity resumed!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Activity destroyed!");
    }
}
