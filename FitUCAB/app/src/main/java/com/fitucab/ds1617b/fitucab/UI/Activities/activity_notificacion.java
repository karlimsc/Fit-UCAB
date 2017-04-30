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
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class activity_notificacion extends AppCompatActivity {

    String correo;
    String contraseña;
    Session session;
    Configuration config = new Configuration();
    Locale locale;
    Button enviar, lang;
    Switch amigos;
    Switch actividad;
    Switch entrenamiento;
    Switch retos;
    Switch hidratacion;
    Switch calorias;
    Switch gamificacion;

    Button btncambios;

    SeekBar seekbar;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        correo = "aagilazer@gmail.com";
        contraseña = "25216467";
        amigos = (Switch) findViewById(R.id.switch1);
        actividad = (Switch) findViewById(R.id.switch2);
        entrenamiento = (Switch) findViewById(R.id.switch3);
        retos = (Switch) findViewById(R.id.switch4);
        hidratacion = (Switch) findViewById(R.id.switch5);
        calorias = (Switch) findViewById(R.id.switch6);
        gamificacion = (Switch) findViewById(R.id.switch7);

       /////////////////////////////////////////////////////  RADIO UBICACION
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        textview = (TextView) findViewById(R.id.textView);
        int radio = 0;
        radio = obtener_radio();
      ///////////////////////////////////////////////////

      //////////////////////////////////////////////////////////// REFERENCIAS DE LOS CHECKS
        cargar_referencias(); //CARGA PREFERENCIAS POR DEFECTO

        btncambios= (Button) findViewById(R.id.button_guardacambios);
        btncambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar_referencias(); //GUARDA LAS REFERENCIAS EDITADAS
            }
        });
        ///////////////////////////////////////////////////

        //////////////////////////////////////////////////////// ENVIAR CORREO
        enviar = (Button) findViewById(R.id.enviar); //obteniendo la id del boton
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviar_correo("aagilazer@gmail.com", "mi asunto", "este es mi mensaje para ti", "ami");

            }});
        //////////////////////////////////////////////////////////7

        ///////////////////////////////////////////////////////// CABMBIAR IDIOMA
            lang = (Button)findViewById(R.id.idioma);//obteniendo la id del boton
            lang.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View view) {
                            showDialog();
                        }});
        //////////////////////////////////////////////////////////////////////////

    }
            /*verificacion que los checks esten marcados para enviar el correo:*/
            private void enviar_correo(String receptor, String asunto, String mensaje, String identificacion) {
                switch (identificacion) {
                    case "ami":
                        if (amigos.isChecked() )
                        {
                            send_mail(receptor, asunto, mensaje);
                        }
                        break;
                    case "acti":
                        if (actividad.isChecked() )
                        {
                            send_mail(receptor, asunto, mensaje);
                        }
                        break;
                    case "entrena":
                        if ( entrenamiento.isChecked() )
                        {
                            send_mail(receptor, asunto, mensaje);
                        }
                        break;
                    case "reto":
                        if ( retos.isChecked() )
                        {
                            send_mail(receptor, asunto, mensaje);
                        }
                        break;
                    case "hidra":
                        if ( hidratacion.isChecked() )
                        {
                            send_mail(receptor, asunto, mensaje);
                        }
                        break;
                    case "calori":
                        if ( calorias.isChecked() )
                        {
                            send_mail(receptor, asunto, mensaje);
                        }
                        break;
                    case "gami":
                        if ( gamificacion.isChecked() )
                        {
                            send_mail(receptor, asunto, mensaje);
                        }
                        break;
                    default:

                        break;
                }


            }



    public void send_mail(String receptor, String asunto, String mensaje) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
        StrictMode.setThreadPolicy(policy); //agregando la politica

        Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
        properties.put("mail.smtp.host", "smtp.googlemail.com");//se coloca el servidor de correo electronico
        properties.put("mail.smtp.socketFactory.port", "465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
        properties.put("mail.smtp.auth", "true"); //autenticas
        properties.put("mail.smtp.port", "465"); //socket puerto de gmail

        //Autenticar correo:
        try {
            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correo, contraseña);
                }
            });
            //Verificar que la sesion no sea nula
            if (session != null) {
                MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                //Enviar correo:
                Transport.send(message);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    /**
     * Muestra una ventana de dialogo para elegir el nuevo idioma de la aplicacion
     * Cuando se hace clic en uno de los idiomas, se cambia el idioma de la aplicacion
     * y se recarga la actividad para ver los cambios
     * */
    private void showDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.lang_btn));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch(which){
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
                Intent refresh = new Intent(activity_notificacion.this, activity_notificacion.class);
                startActivity(refresh);
                finish();
            }

        });
        b.show();
    }


    /*Cargar Preferencias.*/
        public void cargar_referencias()
        {
            SharedPreferences mispreferencias = getSharedPreferences( "PreferenciasUsuario", Context.MODE_PRIVATE ); //el SharedPreferences crea un archivo xml que solo sera accedido solo para esta aplicacion

            amigos.setChecked(mispreferencias.getBoolean( "check_amigo",true )); // dentro del xml el indicador del check tendra su indicador y valor
            actividad.setChecked(mispreferencias.getBoolean( "check_actividad",true ));
            entrenamiento.setChecked(mispreferencias.getBoolean("check_entrenamiento",true));
            retos.setChecked(mispreferencias.getBoolean( "check_reto",true ));
            hidratacion.setChecked(mispreferencias.getBoolean( "check_hidratacion",true ));
            calorias.setChecked(mispreferencias.getBoolean( "check_calorias",true ));
            gamificacion.setChecked(mispreferencias.getBoolean( "check_gamificacion",true ));



        }

    /*Guardar Preferencias de los CheckBox*/
        public void guardar_referencias ()
        {
            SharedPreferences mispreferencias = getSharedPreferences( "PreferenciasUsuario", Context.MODE_PRIVATE ); //el SharedPreferences crea un archivo xml que solo sera accedido solo para esta aplicacion
            SharedPreferences.Editor editor = mispreferencias.edit(); //empezar a editar al archivo xml

            boolean amigo = amigos.isChecked();
            boolean actividades = actividad.isChecked();
            boolean entrenar = entrenamiento.isChecked();
            boolean reto = retos.isChecked();
            boolean hidrata = hidratacion.isChecked();
            boolean caloria = calorias.isChecked();
            boolean gamifica = gamificacion.isChecked();

            editor.putBoolean( "check_amigo",amigo ); //del indicador guardar el nuevo valor
            editor.putBoolean( "check_actividad",actividades );
            editor.putBoolean( "check_entrenamiento",entrenar );
            editor.putBoolean( "check_reto",reto );
            editor.putBoolean( "check_hidratacion",hidrata );
            editor.putBoolean( "check_calorias",caloria );
            editor.putBoolean( "check_gamificacion",gamifica );

            editor.commit(); //Guardar los cambios
        }

    //metodo
    public int obtener_radio(){

        int w= seekbar.getProgress();

        //dar valor maximo a seekbar
        seekbar.setMax(10);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override

           public void onProgressChanged(SeekBar seekBar,int i,boolean b)
           {
               textview.setText("KM:" + seekBar.getProgress());

               SharedPreferences mispreferencias = getSharedPreferences( "PreferenciasUsuario", Context.MODE_PRIVATE );
               SharedPreferences.Editor editor = mispreferencias.edit();
               int savedProgress1 = seekBar.getProgress();
               editor.putInt("Progreso radio", savedProgress1);
               editor.commit();
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

}

