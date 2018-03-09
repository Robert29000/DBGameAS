package ru.samsung.itschool.dbgame;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	/*
	 * TABLES: ------- RESULTS SCORE INTEGER USER VARCHAR
	 */
	private Context context;
	private String DB_NAME = "game.db";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe(); 
	}

	void addProResult(String username, int score) {
		Cursor cursor=db.query("RESULTS",new String[]{"USERNAME"},"USERNAME=?",new String[]{username},null,null,null);
		if(!cursor.moveToFirst()) {
			db.execSQL("INSERT INTO RESULTS VALUES ('" + username + "', " + score
					+ ");");
		}else db.execSQL("UPDATE RESULTS SET SCORE=? WHERE USERNAME=?",new Object[]{score,username});
	}

	void addResult(String username,int score){
		db.execSQL("INSERT INTO RESULTS VALUES ('" + username + "', " + score
				+ ");");
	}

	void Clear(){
		db.execSQL("DELETE  FROM RESULTS;");
	}

	double average(){
		Cursor cursor=db.rawQuery("SELECT AVG(SCORE) FROM RESULTS;",null);
		cursor.moveToFirst();
		return cursor.getDouble(0);
	}

	double eventFrecents(){
		Cursor cursor=db.rawQuery("SELECT COUNT(SCORE) FROM RESULTS WHERE SCORE % 2=0;",null);
		cursor.moveToFirst();
		double events=cursor.getDouble(0);
		cursor=db.rawQuery("SELECT COUNT(*) FROM RESULTS;",null);
		cursor.moveToFirst();
		return events/cursor.getDouble(0)*100;
	}

	ArrayList<Result> getAllResults() {

		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS;", null);
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = cursor.getInt(cursor.getColumnIndex("SCORE"));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	private void createTablesIfNeedBe() {
		db.execSQL("CREATE TABLE IF NOT EXISTS RESULTS (USERNAME TEXT, SCORE INTEGER);");
	}

	private boolean dbExist() {
		File dbFile = context.getDatabasePath(DB_NAME);
		return dbFile.exists();
	}
	ArrayList<Result> getNumberOfgames(){
		ArrayList<Result> data=new ArrayList<>();
		Cursor cursor=db.rawQuery("SELECT COUNT(SCORE) AS C,USERNAME FROM RESULTS GROUP BY USERNAME;",null);
		boolean hasmoredata=cursor.moveToFirst();
		while(hasmoredata){
			String name=cursor.getString(cursor.getColumnIndex("USERNAME"));
			int c=cursor.getInt(cursor.getColumnIndex("C"));
			data.add(new Result(name,c));
			hasmoredata=cursor.moveToNext();
		}
		return data;
	}

}
