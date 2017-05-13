/**
 * Created by andres on 17/04/17.
 */

package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.Model.UsersAdapter;
import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;


public class Fragment_Amigos extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_M03_amigos, container, false);

        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        UsersAdapter adapter = new UsersAdapter(rootView.getContext(), arrayOfUsers);
        ListView listView = (ListView) rootView.findViewById(R.id.listaAmigos);
        listView.setAdapter(adapter);
        ArrayList<User> usuarios = new ArrayList<User>();
        usuarios.add(new User("Pedro Perez", 650));
        usuarios.add(new User("Andres Rodriguez", 200));
        usuarios.add(new User("Ernesto Lara", 1630));
        adapter.addAll(usuarios);

        return rootView;
    }
}

