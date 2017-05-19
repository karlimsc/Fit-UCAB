/**
 * Created by andres on 17/04/17.
 */

package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContentResolverCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fitucab.ds1617b.fitucab.Model.ArrayAuxiliar;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.Model.UsersAdapter;
import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;
import java.util.List;

import static com.fitucab.ds1617b.fitucab.R.id.textView;


public class M03FragmentAmigos extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_m03_friends, container, false);
        ArrayList<ArrayAuxiliar> arrayOfUsers = new ArrayList<ArrayAuxiliar>();
        UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
        ListView listView = (ListView) rootView.findViewById(R.id.friendsList);
        listView.setAdapter(adapter);


                ArrayList<ArrayAuxiliar> usuarios = new ArrayList<ArrayAuxiliar>();
                usuarios.add(new ArrayAuxiliar("Raul Rubio", 650, 0));
                adapter.addAll(usuarios);



        return rootView;
    }








}





