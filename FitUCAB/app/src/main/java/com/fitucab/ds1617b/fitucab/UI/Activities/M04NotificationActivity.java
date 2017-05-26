package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.R;

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
        ///////////////////////////////////////////////////

        //////////////////////////////////////////////////////// ENVIAR CORREO
        enviar = (Button) findViewById(R.id.enviar); //obteniendo la id del boton
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMail("aagilazer@gmail.com", "mi asunto", "este es mi mensaje para ti", "ami");

            }});
        //////////////////////////////////////////////////////////7

        ///////////////////////////////////////////////////////// CABMBIAR IDIOMA
        /*lang = (Button)findViewById(R.id.idioma);//obteniendo la id del boton
        lang.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }});*/
        //////////////////////////////////////////////////////////////////////////

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
    public void sendMail ( String receptor, String asunto, String mensaje, String identificacion )
    {
        switch (identificacion)
        {
            case "ami":
                if (_swAmigos.isChecked())
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case "acti":
                if (_swActividad.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case "entrena":
                if ( _swEntrenamiento.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case "reto":
                if ( _swRetos.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case "hidra":
                if ( _swHidratacion.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case "calori":
                if ( _swCalorias.isChecked() )
                {
                    checkMail(receptor, asunto, mensaje);
                }
                break;
            case "gami":
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
        StrictMode.setThreadPolicy(policy); //agregando la politica

        Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
        properties.put("mail.smtp.host", "smtp.googlemail.com");//se coloca el servidor de correo electronico
        properties.put("mail.smtp.socketFactory.port", "465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
        properties.put("mail.smtp.auth", "true"); //autenticas
        properties.put("mail.smtp.port", "465"); //socket puerto de gmail

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
                MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(CORREO)); //Emisor: quien enviara el correo
                message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                    /*Envia el correo:*/
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
        return 0; //_sendcorrect;

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
     * Metodo que asigna de forma  predeterminada los valores de los Switch al momento de entrar a la vista de notificaciones
     *
     * Usando SharedPreferences hace que dentro del xml, cree  su indicador y valor
     *
     */
    public void loadReference()
    {
        SharedPreferences mispreferencias = getSharedPreferences( "PreferenciasUsuario", Context.MODE_PRIVATE );
        //el SharedPreferences crea un archivo xml que solo sera accedido solo para esta aplicacion

        locale = new Locale(mispreferencias.getString("check_locale","es" ));
        config.setLocale(locale);
        getResources().updateConfiguration(config, null);

        _swAmigos.setChecked(mispreferencias.getBoolean( "check_amigo",true ));
        _swActividad.setChecked(mispreferencias.getBoolean( "check_actividad",true ));
        _swEntrenamiento.setChecked(mispreferencias.getBoolean("check_entrenamiento",true));
        _swRetos.setChecked(mispreferencias.getBoolean( "check_reto",true ));
        _swHidratacion.setChecked(mispreferencias.getBoolean( "check_hidratacion",true ));
        _swCalorias.setChecked(mispreferencias.getBoolean( "check_calorias",true ));
        _swGamificacion.setChecked(mispreferencias.getBoolean( "check_gamificacion",true ));

        seekbar.setProgress(mispreferencias.getInt("check_radio",5));

    }
///

    /**
     * Metodo que:  al momento de cerrar la aplicacion, guarda los nuevos   valores editados de los Switch de
     * la vista de notificaciones
     *
     */
    public void saveReference ()
    {
            /*el SharedPreferences crea un archivo xml que solo sera accedido solo para esta aplicacion*/
        SharedPreferences mispreferencias = getSharedPreferences( "PreferenciasUsuario", Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = mispreferencias.edit(); //empezar a editar al archivo xml

        boolean amigo = _swAmigos.isChecked();
        boolean actividades = _swActividad.isChecked();
        boolean entrenar = _swEntrenamiento.isChecked();
        boolean reto = _swRetos.isChecked();
        boolean hidrata = _swHidratacion.isChecked();
        boolean caloria = _swCalorias.isChecked();
        boolean gamifica = _swGamificacion.isChecked();
        String idioma = locale.getCountry();
        int radio = seekbar.getProgress();

        editor.putBoolean( "check_amigo",amigo ); //del indicador guardar el nuevo valor
        editor.putBoolean( "check_actividad",actividades );
        editor.putBoolean( "check_entrenamiento",entrenar );
        editor.putBoolean( "check_reto",reto );
        editor.putBoolean( "check_hidratacion",hidrata );
        editor.putBoolean( "check_calorias",caloria );
        editor.putBoolean( "check_gamificacion",gamifica );
        editor.putString("check_locale",idioma);
        editor.putInt("check_radio",radio);

        editor.commit(); //Guardar los cambios
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
                textview.setText("KM:" + seekBar.getProgress());

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
        int z = (int)(distanciamilla);
        return z;
    }

    public int converterKm (double distanciamiles)
    {
        double distanciakm = distanciamiles * 1.609;
        int w = (int)(distanciakm);
        return w;
    }
}