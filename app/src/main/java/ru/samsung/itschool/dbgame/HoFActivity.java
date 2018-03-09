package ru.samsung.itschool.dbgame;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HoFActivity extends Activity {

	private DBManager dbManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ho_f);
		dbManager = DBManager.getInstance(this);
		final ArrayList<Result> results = dbManager.getNumberOfgames();
		final ListView listview=(ListView)findViewById(R.id.playerList);
		final ArrayAdapter adapter=new MyAdapter(this,results);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				ArrayList<Result> res=dbManager.getAllResults();
				String name=results.get(i).name;
				for(int j=0;j<res.size();j++){
					if(res.get(j).name.equals(name)){

					}else{
						res.remove(j);
					}
				}
				ArrayAdapter adapter=new MyAdapter(getBaseContext(),res);
				listview.setAdapter(adapter);
			}
		});
	}
}
