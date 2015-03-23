package com.jldes.amuva;

import android.content.Intent;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by programador10 on 4/03/15.
 */
public class Mail {
    Contacto contacto;
    public Mail(Contacto contacto) {
        this.contacto = contacto;
    }
    public void enviarMail(){
        String nombre, telefono, email, mensaje;
        TextView t_nombre = (TextView)contacto.findViewById(R.id.nombre);
        TextView t_telefono = (TextView)contacto.findViewById(R.id.telefono);
        TextView t_mensaje = (TextView)contacto.findViewById(R.id.mensaje);
        Spinner spinner = (Spinner)contacto.findViewById(R.id.spinner);
        nombre=t_nombre.getText().toString();
        telefono=t_telefono.getText().toString();
        mensaje=t_mensaje.getText().toString();
        String[] a = {"info@amuva.es","robolid@amuva.es","talleresinternos@amuva.es","talleresexternos@amuva.es"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        String[] to = {""};
        to[0]=a[spinner.getSelectedItemPosition()];
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email enviado desde la app de amuva");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Nombre:\n"+nombre+"\nTeléfono:\n"+telefono+"\nMensaje:\n"+mensaje);
        emailIntent.setType("message/rfc822");
        contacto.startActivity(Intent.createChooser(emailIntent, "Email "));
    }
}
