package fr.canm.cyrilstern1.threadcnamtp6;

import android.content.Context;
import android.view.View;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by cyrilstern1 on 21/04/2016.
 */
public class ArrayAdapter extends android.widget.ArrayAdapter{

    public ArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        Inflater inflater = new Inflater();
       // View V = new View();
    }
   // public View getView
}
