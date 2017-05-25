package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M10.M10HistoyFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M10.M10WaterGlassFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class M10WaterGlassActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ImageButton _btnAdd;
    private ImageButton _btnLess;
    private EditText _EtnDate;
    private  View _view;
    private Calendar _cal ;
    private String _date;
    private SimpleDateFormat _sdf =new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_m10_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_m10_Hidratacion));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_m10_Control));
        tabLayout.setupWithViewPager(mViewPager);
        _cal=_cal.getInstance(TimeZone.getDefault());

        setupViewValues();







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.m02_perfil_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        try {
            _date =  _EtnDate.getText().toString();
            _cal.setTime(_sdf.parse(_date));
            _cal.add(Calendar.DATE,-1);
            _date = _sdf.format(_cal.getTime());
            _EtnDate.setText(_date.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;

        }


        @Override



        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //me cago en tu mierda deja esto asi y despues te explico
            M10WaterGlassFragment m10 = new M10WaterGlassFragment();
            M10HistoyFragment list = new M10HistoyFragment();





            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                View rootView = m10.onCreateView(inflater,container,null);
                return rootView;
            }

            else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                View rootView = list.onCreateView(inflater,container,null);
                //inflater.inflate(R.layout.fragment_m10_histoy, container, false);;
                return rootView;
            }

            else{
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                return rootView;

            }

        }
    }

    private  void setupViewValues()
    {
        try
        {
        _btnLess = (ImageButton) findViewById(R.id.btn_m10_lessDate);
        _btnAdd = (ImageButton) findViewById(R.id.btn_m10_AddDate);
        _EtnDate= (EditText) findViewById(R.id.et_m10_date);
        _EtnDate.setText(giveDate().toString());

         activarCalendario();
           // instanciarCalendario();
        addDate();
        lessDate();
        }
        catch (Exception e) {
        System.out.print(e);
        }

    }



    private void addDate()
    {
        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    _date =  _EtnDate.getText().toString();

                    _cal.setTime(_sdf.parse(_date));

                    _cal.add(Calendar.DATE, 1);
                    _date = _sdf.format(_cal.getTime());
                    _EtnDate.setText(_date.toString());



                } catch (ParseException e) {
                    e.printStackTrace();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });
    }

    private void lessDate()
    {
        _btnLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    _date =  _EtnDate.getText().toString();
                    _cal.setTime(_sdf.parse(_date));
                    _cal.add(Calendar.DATE,-1);
                    _date = _sdf.format(_cal.getTime());
                    _EtnDate.setText(_date.toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });
    }


    private void activarCalendario() {
        _EtnDate.setOnClickListener(new View.OnClickListener() {

        @Override
            public void onClick(View v) {

                try {
                    // _cal = Calendar.getInstance();
                    instanciarCalendario();
                }
                catch (Exception e){
                    System.out.print(e);
                }
            }
        });
    }



    public void instanciarCalendario(){
        // Create the DatePickerDialog instance
        DatePickerDialog datePicker = new DatePickerDialog(this,
                datePickerListener,_cal.get(Calendar.YEAR), _cal.get(Calendar.DAY_OF_MONTH),
                _cal.get(Calendar.MONTH));

        datePicker.setCancelable(false);
        datePicker.setTitle("Select the date");
        datePicker.show();
    }

    public String giveDate() {
        Calendar ab = Calendar.getInstance();
        SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
        Date Fecha = new Date();

        String FActual = a.format(Fecha);
        _EtnDate.setText(FActual);

        return  FActual;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            _EtnDate.setText(day1 + "/" + month1 + "/" + year1);

        }

    };

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HIDRATACION";
                case 1:
                    return "CONTROL";
            }
            return null;
        }
    }
}
