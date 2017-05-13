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
import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;

public class Fragment_Libreta extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_M03_libreta, container, false);

        ArrayList<User> arrayOfConts1 = new ArrayList<User>();
        AdapterContactosCon adapter = new AdapterContactosCon(rootView.getContext(), arrayOfConts1);
        ListView listView = (ListView) rootView.findViewById(R.id.listaCont1);
        listView.setAdapter(adapter);
        ArrayList<User> usuarios = new ArrayList<User>();
        usuarios.add(new User("Leopoldo Enriques", 2000));
        usuarios.add(new User("Juan Lorenzo", 25));
        usuarios.add(new User("Roberto Mendoza", 160));
        adapter.addAll(usuarios);

        ArrayList<User> arrayOfConts2 = new ArrayList<User>();
        AdapterContactosSin adapter2 = new AdapterContactosSin(rootView.getContext(), arrayOfConts2);
        ListView listView2 = (ListView) rootView.findViewById(R.id.listaCont2);
        listView2.setAdapter(adapter2);
        ArrayList<User> usuarios2 = new ArrayList<User>();
        usuarios2.add(new User("Loreta Martini", 0));
        usuarios2.add(new User("Luis Gonzales", 0));
        usuarios2.add(new User("Liliana Lopez", 0));
        adapter2.addAll(usuarios2);

        return rootView;
    }
}

