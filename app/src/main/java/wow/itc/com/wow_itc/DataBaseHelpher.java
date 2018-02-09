package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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
    private static final String STU_TABLE = "create table "+STUDENT_TABLE +"(personame TEXT,email TEXT,phoneNumber TEXT,schoolname TEXT,schoolconfirmed TEXT,strength TEXT,area TEXT,empname TEXT)";
private static  final String Prop_table="create table propagation(areavisit TEXT,personame TEXT,schoolname TEXT,phonenumber TEXT,email TEXT,schoolconfirmed " +
        "TEXT,strength TEXT,attened TEXT,IEC TEXT,otherbenifits TEXT,tree TEXT,water TEXT,wowclub TEXT,numofstudents TEXT,empname TEXT,TypeofProp TEXT)";
    Context context;

    public DataBaseHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(Prop_table);
        db.execSQL(STU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS propagation");
        // Create tables again
        onCreate(db);
    }
    void propinsert(String areavisit,String personame,String schoolname,String phonenumber,String email,String schoolconfirmed,String strength,String attened,String IEC,String otherbenifits,String tree,String water,String wowclub,String numofstudents,String ngoempname,String TypeofProp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("areavisit", areavisit);
        values.put("personame", personame);
        values.put("schoolname", schoolname);
        values.put("phoneNumber", phonenumber);
        values.put("email", email);
        values.put("schoolconfirmed", schoolconfirmed);
        values.put("strength", strength);

        values.put("attened",attened);
        values.put("IEC",IEC);
        values.put("otherbenifits",otherbenifits);
        values.put("tree",tree);
        values.put("water",water);
        values.put("wowclub",wowclub);
        values.put("numofstudents",numofstudents);
        values.put("empname",ngoempname);
values.put("TypeofProp",TypeofProp);

        // 3. insert
        db.insert("propagation", null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG).show();
        Log.i("insert into DB", "After insert");
    }

    /* Insert into database*/
    void insertIntoDB(String name, String email, String roll, String address, String schoolconfirmed, String branch, String areavisit,String ngoempname){
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
        values.put("area", areavisit);
        values.put("empname", ngoempname);

        // 3. insert
        db.insert(STUDENT_TABLE, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG).show();
        Log.i("insert into DB", "After insert");
    }
    /* Retrive  data from database */
    List<Propagation> propdata()
    {
List<Propagation> modelList= new ArrayList<>();
String query ="select * from propagation";
SQLiteDatabase db= this.getWritableDatabase();
 Cursor cursor= db.rawQuery(query,null);
 Log.e("Error", String.valueOf(cursor));
        if (cursor.moveToFirst()){
            do {
                Propagation model = new Propagation();
                model.setAreavisit(cursor.getString(0));
                model.setPersoname(cursor.getString(1));
                model.setSchoolname(cursor.getString(2));
                model.setPhonenumber(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setSchoolconfirmed(cursor.getString(5));
                model.setStrength(cursor.getString(6));
                model.setAttened(cursor.getString(7));
                model.setIEC(cursor.getString(8));
                model.setOtherbenifits(cursor.getString(9));
                model.setTree(cursor.getString(10));
                model.setWater(cursor.getString(11));
                model.setWowclub(cursor.getString(12));
                model.setNumofstudents(cursor.getString(13));
                model.setNgoempname(cursor.getString(14));

                model.setTypeofprop(cursor.getString(15));
                Log.e("student data", DatabaseUtils.dumpCursorToString(cursor));
                modelList.add(model);
            }while (cursor.moveToNext());
        }
  //      Log.d("student data", modelList.toString());
        return modelList;
    }
     List<Visit> getDataFromDB(){
        List<Visit> modelList = new ArrayList<Visit>();
        String query = "select * from "+STUDENT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query,null);
        Log.e("Error", String.valueOf(cursor));
        if (cursor.moveToFirst()){
            do {
                Visit model = new Visit();
                model.setPersoname(cursor.getString(0));
                model.setEmail(cursor.getString(1));
                model.setPhonenumber(cursor.getString(2));
                model.setSchoolname(cursor.getString(3));
                model.setSchoolconfirmed(cursor.getString(4));
                model.setStrength(cursor.getString(5));
                model.setAreavisit(cursor.getString(6));
                Log.e("student data", DatabaseUtils.dumpCursorToString(cursor));
                modelList.add(model);
            }while (cursor.moveToNext());
        }





        return modelList;
    }




}
