package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Switch;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        correo= "aagilazer@gmail.com";
        contraseña="25216467";
        amigos=(Switch) findViewById(R.id.switch1);
        actividad = (Switch) findViewById(R.id.switch2);
        entrenamiento = (Switch) findViewById(R.id.switch3);
        retos = (Switch) findViewById(R.id.switch4);
        hidratacion= (Switch) findViewById(R.id.switch5);
        calorias = (Switch) findViewById(R.id.switch6);
        gamificacion = (Switch) findViewById(R.id.switch7);

        enviar=(Button)findViewById(R.id.enviar); //obteniendo la id del boton
        enviar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

                enviar_correo("aagilazer@gmail.com","mi asunto","este es mi mensaje para ti","ami");

            }
            private void enviar_correo(String receptor,String asunto, String mensaje,String identificacion) {

                /////// verificacion de los check de los SWITCH:////////////////////////////////7

                ////////////// SWITCH AMIGOS:///////////////////////////////////////////////
                if(amigos.isChecked() && ("ami".equals(identificacion)) ){
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
                    StrictMode.setThreadPolicy(policy); //agregando la politica

                    Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
                    properties.put("mail.smtp.host","smtp.googlemail.com");//se coloca el servidor de correo electronico
                    properties.put("mail.smtp.socketFactory.port","465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
                    properties.put("mail.smtp.auth","true"); //autenticas
                    properties.put("mail.smtp.port","465"); //socket puerto de gmail

                    //Autenticar correo:
                    try{
                        session= Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correo,contraseña);
                            }
                        });
                        //Verificar que la sesion no sea nula
                        if(session!= null){
                            MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                            message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                            message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                            //Enviar correo:
                            Transport.send(message);

                        }

                    }

                    catch (Exception e){
                        e.printStackTrace(); }
                }
                ////////////// SWITCH ACTIVIDAD:///////////////////////////////////////////////

                if(actividad.isChecked() &&("acti".equals(identificacion)) ){
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
                    StrictMode.setThreadPolicy(policy); //agregando la politica

                    Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
                    properties.put("mail.smtp.host","smtp.googlemail.com");//se coloca el servidor de correo electronico
                    properties.put("mail.smtp.socketFactory.port","465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
                    properties.put("mail.smtp.auth","true"); //autenticas
                    properties.put("mail.smtp.port","465"); //socket puerto de gmail

                    //Autenticar correo:
                    try{
                        session= Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correo,contraseña);
                            }
                        });
                        //Verificar que la sesion no sea nula
                        if(session!= null){
                            MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                            message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                            message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                            //Enviar correo:
                            Transport.send(message);

                        }

                    }

                    catch (Exception e){
                        e.printStackTrace(); }
                }
                ////////////// SWITCH ENTRENAMIENTO:///////////////////////////////////////////////
                if(entrenamiento.isChecked() &&("entrena".equals(identificacion)) ){
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
                    StrictMode.setThreadPolicy(policy); //agregando la politica

                    Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
                    properties.put("mail.smtp.host","smtp.googlemail.com");//se coloca el servidor de correo electronico
                    properties.put("mail.smtp.socketFactory.port","465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
                    properties.put("mail.smtp.auth","true"); //autenticas
                    properties.put("mail.smtp.port","465"); //socket puerto de gmail

                    //Autenticar correo:
                    try{
                        session= Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correo,contraseña);
                            }
                        });
                        //Verificar que la sesion no sea nula
                        if(session!= null){
                            MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                            message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                            message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                            //Enviar correo:
                            Transport.send(message);

                        }

                    }

                    catch (Exception e){
                        e.printStackTrace(); }
                }
                ////////////// SWITCH RETOS:///////////////////////////////////////////////
                if(retos.isChecked() &&("reto".equals(identificacion)) ){
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
                    StrictMode.setThreadPolicy(policy); //agregando la politica

                    Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
                    properties.put("mail.smtp.host","smtp.googlemail.com");//se coloca el servidor de correo electronico
                    properties.put("mail.smtp.socketFactory.port","465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
                    properties.put("mail.smtp.auth","true"); //autenticas
                    properties.put("mail.smtp.port","465"); //socket puerto de gmail

                    //Autenticar correo:
                    try{
                        session= Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correo,contraseña);
                            }
                        });
                        //Verificar que la sesion no sea nula
                        if(session!= null){
                            MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                            message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                            message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                            //Enviar correo:
                            Transport.send(message);

                        }

                    }

                    catch (Exception e){
                        e.printStackTrace(); }
                }
                ////////////// SWITCH HIDRATACION:///////////////////////////////////////////////
                if(hidratacion.isChecked() &&("hidra".equals(identificacion)) ){
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
                    StrictMode.setThreadPolicy(policy); //agregando la politica

                    Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
                    properties.put("mail.smtp.host","smtp.googlemail.com");//se coloca el servidor de correo electronico
                    properties.put("mail.smtp.socketFactory.port","465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
                    properties.put("mail.smtp.auth","true"); //autenticas
                    properties.put("mail.smtp.port","465"); //socket puerto de gmail

                    //Autenticar correo:
                    try{
                        session= Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correo,contraseña);
                            }
                        });
                        //Verificar que la sesion no sea nula
                        if(session!= null){
                            MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                            message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                            message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                            //Enviar correo:
                            Transport.send(message);

                        }

                    }

                    catch (Exception e){
                        e.printStackTrace(); }
                }
                ////////////// SWITCH CALORIAS:///////////////////////////////////////////////
                if(calorias.isChecked() &&("calori".equals(identificacion)) ){
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
                    StrictMode.setThreadPolicy(policy); //agregando la politica

                    Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
                    properties.put("mail.smtp.host","smtp.googlemail.com");//se coloca el servidor de correo electronico
                    properties.put("mail.smtp.socketFactory.port","465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
                    properties.put("mail.smtp.auth","true"); //autenticas
                    properties.put("mail.smtp.port","465"); //socket puerto de gmail

                    //Autenticar correo:
                    try{
                        session= Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correo,contraseña);
                            }
                        });
                        //Verificar que la sesion no sea nula
                        if(session!= null){
                            MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                            message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                            message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                            //Enviar correo:
                            Transport.send(message);

                        }

                    }

                    catch (Exception e){
                        e.printStackTrace(); }
                }
                ////////////// SWITCH GAMIFICACION:///////////////////////////////////////////////
                if(gamificacion.isChecked() &&("gami".equals(identificacion)) ){
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //agregando politicas para que se envie el correo
                    StrictMode.setThreadPolicy(policy); //agregando la politica

                    Properties properties = new Properties(); // Ésta clase es la encargada de almacenar las propiedades de la conexión que vamos a establecer con el servidor de correo Saliente SMTP.
                    properties.put("mail.smtp.host","smtp.googlemail.com");//se coloca el servidor de correo electronico
                    properties.put("mail.smtp.socketFactory.port","465"); //aqui se agrega el socket para recibir respuesta del servidor de gmail
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //seguridad ssl potocolo para qu se envie de forma segurala informacion
                    properties.put("mail.smtp.auth","true"); //autenticas
                    properties.put("mail.smtp.port","465"); //socket puerto de gmail

                    //Autenticar correo:
                    try{
                        session= Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correo,contraseña);
                            }
                        });
                        //Verificar que la sesion no sea nula
                        if(session!= null){
                            MimeMessage message = new MimeMessage(session);//Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo)); //Emisor: quien enviara el correo
                            message.setSubject(asunto); //AQUI SE ENVIA EL  ASUNTO
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receptor));//Receptor: el correo que le llegara el mensaje
                            message.setContent(mensaje, "text/html; charset=utf-8");//AQUI SE COLOCA EL MENSAJE. despues de la coma va el  formato del mensaje

                            //Enviar correo:
                            Transport.send(message);

                        }

                    }

                    catch (Exception e){
                        e.printStackTrace(); }
                }

            }

        });

        lang = (Button)findViewById(R.id.idioma);

        lang.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }});
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
}

