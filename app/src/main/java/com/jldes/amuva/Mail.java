package com.jldes.amuva;

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
        TextView textView = (TextView)contacto.findViewById(R.id.nombre);
        
    }
}
