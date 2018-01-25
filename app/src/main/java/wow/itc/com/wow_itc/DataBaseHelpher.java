package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthik on 1/20/2018.
 */

public class DataBaseHelpher extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ITC_WOW";
    private static final int DATABASE_VERSION = 1;
    private static final String STUDENT_TABLE = "Visit";
    private static final String STU_TABLE = "create table "+STUDENT_TABLE +"(personame TEXT,email TEXT,phoneNumber TEXT,schoolname TEXT,schoolconfirmed TEXT,strength TEXT)";

    Context context;

    public DataBaseHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(STU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);

        // Create tables again
        onCreate(db);
    }
    /* Insert into database*/
    public void insertIntoDB(String name,String email,String roll,String address,String schoolconfirmed,String branch){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("personame", name);
        values.put("email", email);
        values.put("phoneNumber", roll);
        values.put("schoolname", address);
        values.put("schoolconfirmed", schoolconfirmed);
        values.put("strength", branch);


        // 3. insert
        db.insert(STUDENT_TABLE, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG).show();
        Log.i("insert into DB", "After insert");
    }
    /* Retrive  data from database */
    public List<Visit> getDataFromDB(){
        List<Visit> modelList = new ArrayList<Visit>();
        String query = "select * from "+STUDENT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                Visit model = new Visit();
                model.setPersoname(cursor.getString(0));
                model.setEmail(cursor.getString(1));
                model.setPhonenumber(cursor.getString(2));
                model.setSchoolname(cursor.getString(3));
                model.setSchoolconfirmed(cursor.getString(4));
                model.setStrength(cursor.getString(4));
                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("student data", modelList.toString());


        return modelList;
    }


    /*delete a row from database*/

    public void deleteARow(String email){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(STUDENT_TABLE, "email" + " = ?", new String[] { email });
        db.close();
    }
}
