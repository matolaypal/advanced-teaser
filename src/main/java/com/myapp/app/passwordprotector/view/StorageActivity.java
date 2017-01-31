package com.myapp.app.passwordprotector.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.myapp.app.passwordprotector.R;
import com.myapp.app.passwordprotector.controller.ListAdapter;
import com.myapp.app.passwordprotector.controller.StorageController;
import com.myapp.app.passwordprotector.model.Account;

import java.util.ArrayList;

/**
 * Second activity on the row, responsible for the view of key:
 * @see Account
 * Get the stored keys from:
 * @see StorageController
 * Set the adapter to this activity
 * @see ListAdapter
 *
 */
public class StorageActivity extends ListActivity {
    /**
     * Tag for logging.
     */
    private final String TAG = StorageActivity.class.getSimpleName()+"<> ";

    /**
     * Define input fields.
     */
    public ArrayList<Account> mUserAccounts;
    public ListAdapter mListAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Activity resumed!");

        StorageController storageController = new StorageController(StorageActivity.this);
        Integer accountQuantity = storageController.getAccountQuantity(0);

        mUserAccounts = new ArrayList<>();
        if (accountQuantity>0) {
            for (int i = 1; i < accountQuantity+1; i++) {
                Account account = storageController.getStoredAccount("ACC_KEY"+i, null);
                if (!(account == null)) {
                    mUserAccounts.add(account);
                }
            }
        }

        //TODO: DELETE BEFORE 1.0 RELEASE!
        Log.d(TAG, "STORED ACC QOUNT: "+accountQuantity);
        Log.d(TAG, "ACCOUNTS FOR ADAPTER: "+mUserAccounts.size());
        Log.d(TAG, "ACCOUNTS FOR ADAPTER: "+mUserAccounts.toString());

        mListAdapter = new ListAdapter(this, mUserAccounts);
        setListAdapter(mListAdapter);
    }

    /**
     * Responsible for menu view
     *
     * @param menu Get by system
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Responsible for menu view
     *
     * @param item Get by system
     * @return true (if clicked(touched) "add key" button in the menu) / system option
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addMenuButton) {
            Intent intent = new Intent(StorageActivity.this, ManageKeyActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
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
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Activity destroyed!");
    }
}
