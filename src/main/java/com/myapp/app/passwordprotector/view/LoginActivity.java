package com.myapp.app.passwordprotector.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.myapp.app.passwordprotector.R;
import com.myapp.app.passwordprotector.controller.Authenticator;
import com.myapp.app.passwordprotector.controller.StorageController;

/**
 * This is the main activity of the app, the first look, what the user will see.
 *
 */
public class LoginActivity extends Activity {
    /**
     * Tag for logging.
     */
    private final String TAG = LoginActivity.class.getSimpleName()+"<> ";

    /**
     * Define input fields.
     */
    private EditText mUserNameText;
    private EditText mPasswordText;
    private TextView welcome;
    private TextView infoForNew;

    /**
     * Get views, then get the username from:
     * @see StorageController
     * Check the username is exist, and manage views based on this value.
     * When login button clicked(/touched), get the given strings form EditTexts.
     * Passed to them:
     * @see Authenticator
     * Manage the activities based on authenticator bool value.
     *
     * @param savedInstanceState get by system
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(TAG, "Activity created!");

        // Setting layout elements (Looking activity_login.xml)
        welcome = (TextView) findViewById(R.id.welcomeTextView);
        mUserNameText = (EditText) findViewById(R.id.userNameText);
        mPasswordText = (EditText) findViewById(R.id.passwordText);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        infoForNew = (TextView) findViewById(R.id.informTextView);

        StorageController storageController = new StorageController(LoginActivity.this);

        //TODO: DELETE BEFORE 1.0 RELEASE!
        Log.d(TAG, "user password: "+storageController.getStoredString("USER_PW", null));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Authenticator authenticator = new Authenticator(LoginActivity.this,
                        mUserNameText.getText().toString(),
                        mPasswordText.getText().toString());

                Boolean authenticated = authenticator.authenticate();

                // When user doesn't fill the inputs show a pop-up message.
                if(authenticated==null) {
                    Toast.makeText(LoginActivity.this, "Every input field is required!", Toast.LENGTH_SHORT).show();
                }
                // When everything gonna be all right start StorageActivity.
                else {
                    if (authenticated) {
                    Intent intent = new Intent(LoginActivity.this, StorageActivity.class);
                    startActivity(intent);
                    }
                    // Wrong password show a dialog.
                    //TODO: changing to EditText error message, dialogs (especially fragments) gone!
                    else if (!authenticator.authenticate()) {

                        // Build, create, then show an AlertDialog window
                       AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Invalid Authentication")
                                .setMessage("You give me a wrong password! Please, try it again!")
                                // this method wait a second onClickListener argument, what is null in this case
                                // it means, when the user tap this button, the dialog will be closed
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mPasswordText.setText("");
                                    }
                                });
                        builder.create().show();
                    }
                }
            }
        });
    }

    /**
     * Managing views based on user registration checking
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Activity resumed!");

        StorageController storageController = new StorageController(LoginActivity.this);
        String userName = storageController.getStoredString("USER_NAME", null);

        if(!(userName == null)) {
            welcome.setText(String.format("Welcome, dear %s!", userName));
            mUserNameText.setVisibility(View.INVISIBLE);
            infoForNew.setVisibility(View.INVISIBLE);
        }

        mUserNameText.setText("");
        mPasswordText.setText("");
    }

    /**
     * Implemented just for logging, yet!
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
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Activity destroyed!");
    }
}
