package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Model.Notification_Settings;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M04.M04NotificationFragment;

import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fitucab.ds1617b.fitucab.Helper.ManagePreferences.getIdUser;


/**
 *  Declaracion de la Clase M04NotificationActivity
 *  @author Anderson Gomez
 *  @author Daniel Da Silva
 *  @author Luis Matinez
 *
 */

public class M04NotificationActivity extends AppCompatActivity {

    private final String CORREO="fitucab@gmail.com";
    private final String CONTRASEÑA="desarrolloucab";

    Session session;
    Configuration config = new Configuration();
    Locale locale;

    Button enviar, lang;
    Button btncambios;
    SeekBar seekbar;
    TextView textview, _textMi, _textKms;

    int    _sendcorrect;
    int radio;
    int w;

    private Switch _swAmigos;
    private Switch _swActividad;
    private Switch _swEntrenamiento;
    private Switch _swRetos;
    private Switch _swHidratacion;
    private Switch _swCalorias;
    private Switch _swGamificacion;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_m04_notification );

        _swAmigos = (Switch) findViewById( R.id.sw_m04_amigos );
        _swActividad = (Switch) findViewById (R.id.sw_m04_actividad);
        _swEntrenamiento = (Switch) findViewById(R.id.sw_m04_entrenamiento);
        _swRetos = (Switch) findViewById(R.id.sw_m04_retos);
        _swHidratacion = (Switch) findViewById(R.id.sw_m04_hidratacion);
        _swCalorias = (Switch) findViewById(R.id.sw_m04_calorias);
        _swGamificacion = (Switch) findViewById(R.id.sw_m04_gamificacion);



        /////////////////////////////////////////////////////  RADIO UBICACION
        seekbar = (SeekBar) findViewById(R.id.tv_m04_radio2);
        textview = (TextView) findViewById(R.id.tv_m04_distancia);


        radio = getRadio();

        ///////////////////////////////////////////////////

        //////////////////////////////////////////////////////////// REFERENCIAS DE LOS CHECKS
        loadReference(); //CARGA PREFERENCIAS POR DEFECTO

        btncambios= (Button) findViewById(R.id.button_guardacambios);
        btncambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReference(); //GUARDA LAS REFERENCIAS EDITADAS
            }
        });
    }



    /**
     * Metodo que:  verifica si esta marcado el switch y envia el correo
     *
     * posee 4 parametros
     * @param receptor correo el cual le llegara el mensaje
     * @param asunto asunto del correo
     * @param mensaje mensaje del correo
     * @param identificacion  identificacion de cual switch pertenece
     */
    public void sendMail ( String receptor, String asunto, String mensaje, int identificacion )
    {
        switch (identificacion)
        {
            case 3:
                if (_swAmigos.isChecked())
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case 7:
                if (_swActividad.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case 6:
                if ( _swEntrenamiento.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case 8:
                if ( _swRetos.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case 10:
                if ( _swHidratacion.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case 11:
                if ( _swCalorias.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case 9:
                if ( _swGamificacion.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            default:

                break;
        }


    }


    /**
     * Metodo que:  envia el correo estableciendo primero que nada las propiedades de la conexión, seguridad, autenticacion
     *
     * posee 3 parametros
     * @param receptor correo el cual le llegara el mensaje
     * @param asunto asunto del correo
     * @param mensaje mensaje del correo
     *
     */
    public int checkMail(String receptor, String asunto, String mensaje) {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
        //StrictMode.setThreadPolicy(policy); //agregando la politica

        Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
        properties.put("mail.smtp.host", "smtp.googlemail.com");//se coloca el servidor de correo electronico
        properties.put("mail.smtp.socketFactory.port", "465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
        properties.put("mail.smtp.auth", "true"); //autenticas
        properties.put("mail.smtp.port", "465"); //socket puerto de gmail
        _sendcorrect = 0;
            /*Autenticar correo:*/
        try {
            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO, CONTRASEÑA);
                }
            });
                /*Verificar que la sesion no sea nula*/
            if (session != null)
            {
                //Crear el objeto que contiene al mensaje
                MimeMessage message = new MimeMessage(session);

                //Emisor: quien enviara el correo
                message.setFrom(new InternetAddress(CORREO));

                //AQUI SE ENVIA EL  ASUNTO
                message.setSubject(asunto);

                //Receptor: el correo que le llegara el mensaje
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));

                //AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje
                message.setContent(mensaje, "text/html; charset=utf-8");

                //Envia el correo
                Transport.send(message);

                _sendcorrect=1; //verifico que lo haya mandado

            }

        }
        catch (AddressException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return _sendcorrect; //_sendcorrect;

    }


    /**
     * Muestra una ventana de dialogo para elegir el nuevo idioma de la aplicacion
     * Cuando se hace clic en uno de los idiomas, se cambia el idioma de la aplicacion
     * y se recarga la actividad para ver los cambios
     * */
    public void showDialog(View v)
    {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.lang_btn));

        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch(which)
                {
                    case 0:
                        locale = new Locale("en");
                        config.locale =locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale =locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(M04NotificationActivity.this, M04NotificationActivity.class);
                startActivity(refresh);
                finish();
            }

        });
        b.show();
    }


    /**
     * Metodo que asigna valores los Switch al momento de entrar a la vista de notificaciones conectandose con el WS para obtener la configuración del usuario
     *
     * Usando SharedPreferences hace que dentro del xml, cree  su indicador y valor
     *
     */
    public void loadReference()
    {
        ApiEndPointInterface apiService= ApiClient.getClient().create(ApiEndPointInterface.class);
        int id = getIdUser(getApplicationContext());
        Call<Notification_Settings> call= apiService.getSetting(id);
        call.enqueue(new Callback<Notification_Settings>() {

            @Override
            public void onResponse(Call<Notification_Settings> call, Response<Notification_Settings> response) {

                try{
                    Notification_Settings settings = response.body();
                    locale = new Locale(settings.get_preferenceLanguage());
                    config.setLocale(locale);
                    getResources().updateConfiguration(config, null);
                    _swAmigos.setChecked(settings.is_preferenceFriends());
                    _swActividad.setChecked(settings.is_preferenceActivity());
                    _swEntrenamiento.setChecked(settings.is_preferenceTraining());
                    _swRetos.setChecked(settings.is_preferenceChallenges());
                    _swHidratacion.setChecked(settings.is_preferenceHydration());
                    _swCalorias.setChecked(settings.is_preferenceCalories());
                    _swGamificacion.setChecked(settings.is_preferenceGamification());
                    seekbar.setProgress(settings.get_preferenceRadius());
                    TextView tvkm = (TextView) findViewById(R.id.tv_m04_km);
                    TextView tvmi = (TextView) findViewById(R.id.tv_m04_mi);
                    if(settings.get_preferenceUnit().equals("km")){
                        cambiarColores(tvmi,tvkm);
                    }
                    else{
                        cambiarColores(tvkm,tvmi);
                    }
                    System.out.println("Hice bien la consulta");
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Hice MAL la consulta");

                }

            }

            @Override
            public void onFailure(Call<Notification_Settings> call, Throwable t) {

                System.out.println("FALLO TODO");

            }
        });


    }
///

    public void insert(boolean amigo, boolean actividades, boolean entrenar, boolean reto, boolean hidrata, boolean caloria, boolean gamifica, String idioma, String unidad, int radio, int id){
        ApiEndPointInterface apiService= ApiClient.getClient().create(ApiEndPointInterface.class);
        Call<Notification_Settings> call= apiService.insertSetting(amigo, actividades, entrenar, reto, hidrata, caloria, gamifica, idioma, unidad, radio, id);
        call.enqueue(new Callback<Notification_Settings>() {

            @Override
            public void onResponse(Call<Notification_Settings> call, Response<Notification_Settings> response) {

                try{
                    Notification_Settings settings = response.body();
                    System.out.println("Hice bien el insert");
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Hice MAL el insert");

                }

            }

            @Override
            public void onFailure(Call<Notification_Settings> call, Throwable t) {

                System.out.println("FALLO TODO");

            }
        });

    }
    /**
     * Metodo que:  al momento de cerrar la aplicacion, guarda los nuevos  valores editados de los Switch de
     * la vista de notificaciones en la app y llama al web service para hacer el update
     *
     */

    public void saveReference ()
    {
            /*el SharedPreferences crea un archivo xml que solo sera accedido solo para esta aplicacion*/
        SharedPreferences mispreferencias = getSharedPreferences( "PreferenciasUsuario", Context.MODE_PRIVATE );
        final SharedPreferences.Editor editor = mispreferencias.edit(); //empezar a editar al archivo xml

        final boolean amigo = _swAmigos.isChecked();
        final boolean actividades = _swActividad.isChecked();
        final boolean entrenar = _swEntrenamiento.isChecked();
        final boolean reto = _swRetos.isChecked();
        final boolean hidrata = _swHidratacion.isChecked();
        final boolean caloria = _swCalorias.isChecked();
        final boolean gamifica = _swGamificacion.isChecked();
        final String idioma = locale.getDefault().toString();
        final int radio = seekbar.getProgress();
        TextView tvkm = (TextView) findViewById(R.id.tv_m04_km);
        String unidad;
        if(tvkm.isClickable()){
            unidad = "mi";
        }
        else{
            unidad = "km";
        }

        ApiEndPointInterface apiService= ApiClient.getClient().create(ApiEndPointInterface.class);
        int id = getIdUser(getApplicationContext());
        Call<Notification_Settings> call= apiService.updateSetting(amigo, actividades, entrenar, reto, hidrata, caloria, gamifica, idioma, unidad, radio, id);
        call.enqueue(new Callback<Notification_Settings>() {

            @Override
            public void onResponse(Call<Notification_Settings> call, Response<Notification_Settings> response) {

                try{
                    Notification_Settings settings = response.body();
                    editor.putBoolean( "check_amigo", amigo ); //del indicador guardar el nuevo valor
                    editor.putBoolean( "check_actividad",actividades );
                    editor.putBoolean( "check_entrenamiento",entrenar );
                    editor.putBoolean( "check_reto",reto );
                    editor.putBoolean( "check_hidratacion",hidrata );
                    editor.putBoolean( "check_calorias",caloria );
                    editor.putBoolean( "check_gamificacion",gamifica );
                    editor.putString("check_locale", idioma);
                    editor.putInt("check_radio",radio);

                    editor.commit(); //Guardar los cambios
                    System.out.println("Hice bien el update");
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Hice MAL el insert");

                }

            }

            @Override
            public void onFailure(Call<Notification_Settings> call, Throwable t) {

                System.out.println("FALLO TODO");

            }
        });
    }


    /**
     * Metodo que: devuelve el valor del seekbar que el usuario eligió, y  guarde su valor al momento de salir de la aplicacion
     *
     */
    public int getRadio()
    {

        int w= seekbar.getProgress();

        seekbar.setMax(10); //Maxima distancia del seekbar
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override

            public void onProgressChanged(SeekBar seekBar,int i,boolean b)
            {
                String progreso = "KM: " + seekBar.getProgress();
                textview.setText(progreso);

                  /*el SharedPreferences crea un archivo xml que solo sera accedido solo para esta aplicacion*/
                SharedPreferences mispreferencias = getSharedPreferences( "PreferenciasUsuario", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = mispreferencias.edit();

                int savedProgress1 = seekBar.getProgress();
                editor.putInt("Progreso radio", savedProgress1);
                editor.commit();//Guardar los cambios


            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {


            }
        }); return w;}

    public int converterMiles(double distanciakm)
    {
        double distanciamilla = distanciakm * 0.6213;
        return (int) (distanciamilla);
    }

    public int converterKm (double distanciamiles)
    {
        double distanciakm = distanciamiles * 1.609;
        return (int) distanciakm;
    }

    /**Metodo para cambiar los colores de entre las opciones
     *
     * @param viejo es la opcion que estaba elegida anteriormente
     * @param nuevo es la nueva opcion que el usuario elige
     */
    public void cambiarColores( TextView viejo, TextView nuevo){
        nuevo.setBackgroundColor(getResources().getColor(R.color.softgrey));
        nuevo.setClickable(false);

        viejo.setBackgroundColor(getResources().getColor(R.color.transparent));
        viejo.setClickable(true);
    }



    public void cambiaAKm (View view){
        TextView tvkm = (TextView) findViewById(R.id.tv_m04_km);
        TextView tvmi = (TextView) findViewById(R.id.tv_m04_mi);

        cambiarColores(tvmi,tvkm);
    }


    public void cambiaAMi (View view){
        TextView tvkm = (TextView) findViewById(R.id.tv_m04_km);
        TextView tvmi = (TextView) findViewById(R.id.tv_m04_mi);

        cambiarColores(tvkm,tvmi);
    }


}