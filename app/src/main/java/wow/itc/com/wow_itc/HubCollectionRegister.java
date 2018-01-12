package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class HubCollectionRegister extends AppCompatActivity {

    String[] ngodata={"NGO-1","NGO-2","NGO-3","NGO-4","NGO-5","NGO-6","Others Please Specify"};
    EditText wardno,nameofhub,collectionpointname,dmw,lvp,colorrec,petbot,milk,hardplastic,tetra,kraft,oldpapaer,oldmag,notebook,whiterec,iron,aluminium,tin,tinaluminium,coconut,materilaa,beerbottle,hubsupname;
    float fdmw,flvp,fcolorrec,fpetbot,fmilk,fhardplastic,ftetra,fkraft,foldpapaer,foldmag,fnotebook,fwhiterec,firon,faluminium,ftin,ftinaluminium,fcoconut,fmaterilaa,fbeerbottle;
    Spinner ngo,city;
    int x,y;
Button b;
    String[] citydata={"city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1","city-1"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_collection_register);
        ngo=findViewById(R.id.spinner);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,ngodata);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ngo.setAdapter(aa);
        b=findViewById(R.id.submit);
        city=findViewById(R.id.city);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,citydata);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);
        wardno=findViewById(R.id.ward);
        nameofhub=findViewById(R.id.hubname);
        collectionpointname=findViewById(R.id.collname);
        dmw=findViewById(R.id.dmw);
        lvp=findViewById(R.id.lvpmll);
        colorrec=findViewById(R.id.colorrecord);
        petbot=findViewById(R.id.pet);
        milk=findViewById(R.id.milk);
        hardplastic=findViewById(R.id.hardplstic);
        tetra=findViewById(R.id.tetra);
        kraft=findViewById(R.id.kraft);
        oldpapaer=findViewById(R.id.oldpaper);
        oldmag=findViewById(R.id.oldmag);
        notebook=findViewById(R.id.book);
        whiterec=findViewById(R.id.whiterec);
        iron=findViewById(R.id.iron);
        aluminium=findViewById(R.id.aluminium);
        tin=findViewById(R.id.tin);
        tinaluminium=findViewById(R.id.tinaluminuim);
        coconut=findViewById(R.id.coconut);
        materilaa=findViewById(R.id.materiala);
        beerbottle=findViewById(R.id.bottle);
 hubsupname=findViewById(R.id.supname);
        ngo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                x=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                y=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedsrat();
            }
        });
    }

    @SuppressLint("UseValueOf")
    private void sharedsrat() {
        SharedPreferences msharedpreferences=getApplicationContext().getSharedPreferences("HUB",MODE_PRIVATE);
        SharedPreferences.Editor editor=msharedpreferences.edit();
        editor.putInt("ngo",x);
        editor.putInt("city",y);
        editor.putString("nameofhub",nameofhub.getText().toString());
        editor.putString("collectioname",collectionpointname.getText().toString());
        fdmw= new Float(dmw.getText().toString());
        editor.putFloat("dmw",fdmw);
        flvp=  new Float(lvp.getText().toString());
        editor.putFloat("lvp",flvp);
        fcolorrec=  new Float(colorrec.getText().toString());
        editor.putFloat("colorec",fcolorrec);
        fpetbot=  new Float(petbot.getText().toString());
        editor.putFloat("petbot",fpetbot);
        fmilk=  new Float(milk.getText().toString());
        editor.putFloat("milk",fmilk);
        fhardplastic=  new Float(hardplastic.getText().toString());
        editor.putFloat("hardplastic",fhardplastic);
        ftetra=  new Float(tetra.getText().toString());
        editor.putFloat("tetra",ftetra);
        fkraft=  new Float(kraft.getText().toString());
        editor.putFloat("kraft",fkraft);
        foldpapaer=  new Float(oldpapaer.getText().toString());
        editor.putFloat("paper",foldpapaer);
        foldmag=  new Float(oldmag.getText().toString());
        editor.putFloat("oldmag",foldmag);
        fnotebook=  new Float(notebook.getText().toString());
        editor.putFloat("notebook",fnotebook);
        fwhiterec=  new Float(whiterec.getText().toString());
        editor.putFloat("whiterec",fwhiterec);
        firon=  new Float(iron.getText().toString());
        editor.putFloat("iron",firon);
        faluminium=  new Float(aluminium.getText().toString());
        editor.putFloat("alumini",faluminium);
        ftin=  new Float(tin.getText().toString());
        editor.putFloat("tim",ftin);
        ftinaluminium=  new Float(tinaluminium.getText().toString());
        editor.putFloat("tinalumini",ftinaluminium);
        fcoconut=  new Float(coconut.getText().toString());
        editor.putFloat("coconut",fcoconut);
        fmaterilaa=  new Float(materilaa.getText().toString());
        editor.putFloat("materiala",fmaterilaa);
        fbeerbottle=new Float(beerbottle.getText().toString());
        editor.putFloat("beerbottle",fbeerbottle);
        editor.putString("Supervisorname",hubsupname.getText().toString());
        editor.apply();
      String c=  String.valueOf(msharedpreferences.getAll());
        Log.e("XXX",c);
        Intent i = new Intent(getApplicationContext(),HouseCheck.class);
        startActivity(i);
    }
}
