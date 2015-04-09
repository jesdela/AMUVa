package com.jldes.amuva;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentClasificacion extends IntentService {

    private SQLiteDatabase db;

    public IntentClasificacion() {
        super("IntentClasificacion");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SQLClasificaciones clasificaciones = new SQLClasificaciones(this,"Clas",null,1);
        db = clasificaciones.getWritableDatabase();
        tareaLarga();
    }
    private String obtenertexto(Node item) {
        StringBuilder builder = new StringBuilder();
        NodeList fragmentos = item.getChildNodes();
        for (int i = 0; i < fragmentos.getLength(); i++) {
            builder.append(fragmentos.item(i).getNodeValue());
        }
        return builder.toString();
    }

    private void tareaLarga() {
        String url = "http://192.168.1.99/appAmuva/base_de_datos.php";
        URL url2;
        Log.d("intent", url);
        Document doc = null;
        try {
            url2 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url2
                    .openConnection();
            urlConnection.addRequestProperty("Content-type", "text/xml");
            urlConnection.connect();
            InputStream stream = urlConnection.getInputStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbr = dbf.newDocumentBuilder();
            doc = dbr.parse(stream);
            if (doc != null) {
                Element root = doc.getDocumentElement();
                NodeList list = root.getElementsByTagName("Robot");
                Log.d("etiqueta",list.getLength()+"");
                for (int i = 0; i < list.getLength(); i++) {
                    Node robot = list.item(i);
                    NodeList list1 = robot.getChildNodes();
                    Log.d("etiqueta","Otro robot");
                    Robot robot1 = new Robot();
                    for (int j = 0; j < list1.getLength(); j++) {
                        Node nodo = list1.item(j);
                        String etiqueta = nodo.getNodeName();
                        if (etiqueta.equalsIgnoreCase("nombre")){
                            robot1.setNombre(obtenertexto(nodo));
                        } else if (etiqueta.equalsIgnoreCase("categoria")){
                            robot1.setCategoria(obtenertexto(nodo));
                        } else if (etiqueta.equalsIgnoreCase("posicion")){
                            robot1.setCategoria(obtenertexto(nodo));
                        }
                    }
                    insertarDB(robot1);

                }
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertarDB(Robot robot1) {
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nombre",robot1.getNombre());
        nuevoRegistro.put("categoria",robot1.getCategoria());
        nuevoRegistro.put("posicion",robot1.getPosicion());
        nuevoRegistro.put("constructor","ninguno");
        db.insert("Clas",null,nuevoRegistro);
        nuevoRegistro.clear();
    }

}
