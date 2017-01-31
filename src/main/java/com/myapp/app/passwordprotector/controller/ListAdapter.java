package com.myapp.app.passwordprotector.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myapp.app.passwordprotector.R;
import com.myapp.app.passwordprotector.model.Account;

import java.util.ArrayList;

/**
 * Control data transfer between views and define one row in a line.
 */
public class ListAdapter extends BaseAdapter{
    /**
     * Tag for logging.
     */
    private final String TAG = ListAdapter.class.getSimpleName()+"<> ";

    /**
     * Define necessary fields.
     */
    private Context mContext;
    private ArrayList<Account> mAccountsList;

    /**
     * @param context "God" object from an activity
     * @param accountsList group of accounts
     */
    public ListAdapter(Context context, ArrayList<Account> accountsList) {
        mContext = context;
        mAccountsList = accountsList;
    }

    /**
     * @return accounts group size
     */
    @Override
    public int getCount() {
        return mAccountsList.size();
    }

    /**
     * @param i like index
     * @return a list element, based on i
     */
    @Override
    public Account getItem(int i) {return mAccountsList.get(i);}


    /**
     * *<b><u>Not implemented yet!</u></b>
     * Must to override, but doesn't necessary for the app.
     *
     * @param i int
     * @return 0
     */
    @Override
    public long getItemId(int i) {return 0;}


    /**
     * Remove an element based on given index.
     * @param i index
     */
    public void removeItem(int i) {
        mAccountsList.remove(i);
        Log.i(TAG, "Removed element with this index: "+i);
    }

    /**
     * Link between the {@link StorageController} and list_item layout.
     * Create a holder for view {@link ViewHolder}.
     * Find element on layout for views.
     * Delete an element (just) from the screen.
     *
     * @param itemPosition index in {@link #mAccountsList}
     * @param view getting from system
     * @param viewGroup system data
     * @return a view
     */
    @Override
    public View getView(final int itemPosition, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.accountNameView = (TextView) view.findViewById(R.id.accountNameView);
            holder.userNameView = (TextView) view.findViewById(R.id.userNameView);
            holder.passwordView = (TextView) view.findViewById(R.id.passwordView);
            holder.deleteButton = (Button) view.findViewById(R.id.deleteButton);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.accountNameView.setText(getItem(itemPosition).getAccountName());
        holder.userNameView.setText(getItem(itemPosition).getAccountUserName());
        holder.passwordView.setText(getItem(itemPosition).getAccountPassword());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(itemPosition);
                Log.d(TAG, String.format("Item with %s index deleted from the screen!", itemPosition));
                /**<b><u>!DEAD_CODE!</u></b> because of {@link StorageController#removeAccount(String)} */
//                StorageController storageController = new StorageController(mContext);
//                storageController.removeAccount(getItem(itemPosition).getStoreKey());
//                storageController.setAccountQuantity(storageController.getAccountQuantity(0)-1);
                Toast.makeText(mContext, "Nice try!", Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });

        return view;
    }

    /**
     * This contains every view in a row (based on list_item.xml it in the layout folder)
     */
    private static class ViewHolder {
        TextView accountNameView;
        TextView userNameView;
        TextView passwordView;
        Button deleteButton;
    }
}








