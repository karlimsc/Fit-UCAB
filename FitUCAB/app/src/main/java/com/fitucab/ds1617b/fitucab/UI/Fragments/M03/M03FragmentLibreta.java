/**
 * Created by andres on 17/04/17.
 */

package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fitucab.ds1617b.fitucab.Model.ArrayAuxiliar;

import com.fitucab.ds1617b.fitucab.Model.UsersAdapter;
import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;

public class M03FragmentLibreta extends Fragment {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.READ_CONTACTS},PERMISSIONS_REQUEST_READ_CONTACTS
            );
        } else {
            Log.e("DB", "PERMISSION GRANTED");
        }

        View rootView = inflater.inflate(R.layout.fragment_m03_contacts, container, false);
        ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
        UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
        ListView listView = (ListView) rootView.findViewById(R.id.contactsList);
        listView.setAdapter(adapter);
        ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();

        usuarios.add(new ArrayAuxiliar("",0,2));
        usuarios.add(new ArrayAuxiliar("Pedro Perez", 650,0));
        usuarios.add(new ArrayAuxiliar("Andres Rodriguez", 200,0));

        usuarios.add(new ArrayAuxiliar("Ernesto Lara", 1630,0));
        usuarios.add(new ArrayAuxiliar("",0,3));
        usuarios.add(new ArrayAuxiliar("Raul Tuozzo", 650,1));
        usuarios.add(new ArrayAuxiliar("Andres Rubio", 200,1));
        usuarios.add(new ArrayAuxiliar("Raul goncalves", 1630,1));


        // Get the ContentResolver
        ContentResolver cr = rootView.getContext().getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // Move the cursor to first. Also check whether the cursor is empty or not.
        if (cursor.moveToFirst()) {
            // Iterate through the cursor
            do {
                // Get the contacts name
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                usuarios.add(new ArrayAuxiliar(name, 650, 1));
              //  adapter.addAll(usuarios);
            } while (cursor.moveToNext());

        }
        // Close the cursor

       adapter.addAll(usuarios);
        cursor.close();
        return rootView;
    }
}

