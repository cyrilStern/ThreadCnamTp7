package fr.canm.cyrilstern1.threadcnamtp6;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import fr.canm.cyrilstern1.threadcnamtp6.tacheAsynk.AsynkTaskLoad;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Integer DO_THREAD;
    public static ArrayAdapter arrayAdapter;
    private static List <String> cantonList = new ArrayList<>();
    public  static ListView listCanon;
    private ProgressBar progressBar;
    private TextView tv;
    private Button buttonLoad;
    private Button buttonStop;
    private Button buttonContinue;
    private AsynkTaskLoad asynk;
    private  Integer line =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }



    protected void init(){
        buttonLoad =(Button) findViewById(R.id.loadBtnId);
        buttonLoad.setOnClickListener(this);
        buttonContinue =(Button) findViewById(R.id.resumeBtnId);
        buttonContinue.setOnClickListener(this);
        buttonStop =(Button) findViewById(R.id.stopBtnId);
        buttonStop.setOnClickListener(this);
        buttonStop.setEnabled(false);
        buttonContinue.setEnabled(false);
        listCanon = (ListView) findViewById(R.id.lsitCanton);
        progressBar = (ProgressBar) findViewById(R.id.progressBarId);
        tv = (TextView) findViewById(R.id.progressionText);
        listCanon.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,cantonList);
        listCanon.setAdapter(arrayAdapter);
    }


        public static void setLine(Integer line){
            DO_THREAD = line;
        }

        public static Integer getLine(){
            return DO_THREAD;
        }
    @Override
    public void onSaveInstanceState(Bundle savedState) {

        super.onSaveInstanceState(savedState);
        List ar = cantonList;
        savedState.putStringArrayList("myKey", (ArrayList<String>) ar);
        if(asynk != null){
         asynk.cancel(true);
        }
        boolean[] tabButton =  { buttonContinue.isEnabled(), buttonLoad.isEnabled(),buttonStop.isEnabled()};
        savedState.putBooleanArray("buttonKey",tabButton );

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
            boolean[] tabButton = savedInstanceState.getBooleanArray("buttonKey");
            buttonStop.setEnabled(tabButton[2]);
            buttonLoad.setEnabled(tabButton[1]);
            buttonContinue.setEnabled(tabButton[0]);
            ArrayList<Parcelable> liste = new ArrayList<>(savedInstanceState.getParcelableArrayList("myKey"));
            if (liste != null) {
                listCanon = (ListView) findViewById(R.id.lsitCanton);
                arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,liste);
                listCanon.setAdapter(arrayAdapter);
            }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loadBtnId :
                try {
                    asynk = new AsynkTaskLoad(this,listCanon, progressBar,arrayAdapter,cantonList,tv,line);
                    asynk.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                buttonStop.setEnabled(true);
                buttonLoad.setEnabled(false);
                break;

            case R.id.stopBtnId :
                try {
                    asynk.cancel(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                buttonContinue.setEnabled(true);
                buttonStop.setEnabled(false);

                break;

            case R.id.resumeBtnId :
                Toast toast =  Toast.makeText(this,"resume",Toast.LENGTH_SHORT);
                toast.show();
                try {
                    Integer line2 = getLine();

                    asynk = new AsynkTaskLoad(this,listCanon, progressBar,arrayAdapter,cantonList,tv,line2);
                    asynk.execute();
                    buttonContinue.setEnabled(false);
                    buttonStop.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }

}
