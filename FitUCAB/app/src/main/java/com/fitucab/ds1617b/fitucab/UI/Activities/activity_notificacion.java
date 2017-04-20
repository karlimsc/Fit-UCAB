package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fitucab.ds1617b.fitucab.R;

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

    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        correo= "aagilazer@gmail.com";
        contraseña="25216467";

        enviar=(Button)findViewById(R.id.enviar); //obteniendo la id del boton
        enviar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v){

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
                        message.setSubject("ESTE ES EL ASUNTO"); //aqui se envia el asunto
                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("aagilazer_95@hotmail.com"));//Receptor: el correo que le llegara el mensaje  min 11:46
                        message.setContent("hola este es un mensaje", "text/html; charset=utf-8");//Aqui se coloca el mensaje. despues de la coma va el  formato del mensaje

                    //Enviar correo:
                        Transport.send(message);

                    }

                }

                catch (Exception e){
                    e.printStackTrace(); }



            }
        });




    }

}

