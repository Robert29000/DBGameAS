package ru.samsung.itschool.dbgame;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StatActivity extends Activity {
    static DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        dbManager=DBManager.getInstance(this);
        double easy=dbManager.average();
        Log.d("EASY",easy+" ");
        TextView text=(TextView)findViewById(R.id.sum);
        text.setText(easy+"");
        text.setText(text.getText().toString()+"    "+dbManager.eventFrecents());
    }
}
