package fr.canm.cyrilstern1.threadcnamtp6.tacheAsynk;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import fr.canm.cyrilstern1.threadcnamtp6.Canton;
import fr.canm.cyrilstern1.threadcnamtp6.CantonArrayAdapter;
import fr.canm.cyrilstern1.threadcnamtp6.MainActivity;
import fr.canm.cyrilstern1.threadcnamtp6.R;

/**
 * Created by cyrilstern1 on 08/04/2016.
 */
public  class AsynkTaskLoad extends AsyncTask <List<String>, String, List> {

    private Context mContext;
    private Canton canton;
    private List <Canton> cityList;
    public ProgressBar progressBar;
    public static List<Canton> listCanton;
    public CantonArrayAdapter aaList;
    public TextView tv;
    public int total;
    public int intLu;
    private String s;


    public AsynkTaskLoad (Context context, ListView lv, ProgressBar progressBar, CantonArrayAdapter arrayAdapter,List<Canton> list, TextView text, Integer line){

        this.aaList = arrayAdapter;
        this.mContext = context;
        this.progressBar = progressBar;
        this.cityList = list;
        this.tv =text;
        this.intLu = line;
        aaList = new CantonArrayAdapter(mContext, R.layout.cantonrow, (ArrayList<Canton>) cityList);
    }
    public static List getList(){
        if(listCanton!=null){return listCanton;}
        else{return listCanton = new ArrayList<>();}
    }
    protected void onPreExecute() {
    }

    protected List doInBackground(List... params) {

            try {
                LineNumberReader ligne = new LineNumberReader(new InputStreamReader(mContext.getAssets().open("canton.csv")));
                ligne.skip(Long.MAX_VALUE);
                Log.i("ligne", String.valueOf(ligne.getLineNumber()
                        +
                        1));
                total = ligne.getLineNumber() - 1;
                ligne.close();
                InputStreamReader isr = new InputStreamReader(mContext.getAssets().open("canton.csv"),"ISO_8859_1");
                LineNumberReader lnr = new LineNumberReader(isr);

                if(intLu!=0){

                    for(int i =0; i<= intLu;i++){
                        lnr.readLine();
                    }
                }else{
                    lnr.readLine();
                }
                while ((s=lnr.readLine()) !=null)
                {
                    if(isCancelled()){
                            break;
                    }

                    String[] array = s.split(";");
                    SystemClock.sleep(5);
                    intLu ++;

                    MainActivity.setLine(intLu);
                    float percentage = ((float)intLu / (float)total) * total;
                    publishProgress(array[8] , array[9]);
                    aaList.notifyDataSetChanged();
                   // publishProgress(new Float(percentage).intValue());

                }

                lnr.close();
                isr.close();
            }catch(Exception e){


            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {
            if (progress[0] != null){
                canton = new Canton(progress[0],progress[1]);

            cityList.add(canton);
                aaList.notifyDataSetChanged();

                MainActivity.setLine(intLu);
            float percentage = ((float)intLu / (float)total) * total;
            progressBar.setProgress((int) percentage);
            tv.setText("chargement en cours..." +intLu+"/"+total);

            }

        }

    protected void onPostExecute(List result) {

            progressBar.setVisibility(RelativeLayout.INVISIBLE);
            tv.setVisibility(RelativeLayout.INVISIBLE);
    }


}


