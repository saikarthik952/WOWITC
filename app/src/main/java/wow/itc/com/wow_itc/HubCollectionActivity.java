package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HubCollectionActivity extends AppCompatActivity  {
    float afdmw,aflvp,afcolorrec,afpetbot,afmilk,afhardplastic,aftetra,afkraft,afoldpapaer,afoldmag,afnotebook,afwhiterec,afiron,afaluminium,aftin,aftinaluminium,afcoconut,afmaterilaa,afbeerbottle;
    String[] ngodata={"NGO-1","NGO-2","NGO-3","NGO-4","NGO-5","NGO-6","Others Please Specify"};
    EditText wardno,nameofhub,vehiclenumber,materialrecieveename,collectionpointname,dmw,lvp,colorrec,petbot,milk,hardplastic,tetra,kraft,oldpapaer,oldmag,notebook,whiterec,iron,aluminium,tin,tinaluminium,coconut,materilaa,beerbottle,hubsupname;
    float fdmw,flvp,fcolorrec,fpetbot,fmilk,fhardplastic,ftetra,fkraft,foldpapaer,foldmag,fnotebook,fwhiterec,firon,faluminium,ftin,ftinaluminium,fcoconut,fmaterilaa,fbeerbottle;
    float xfdmw,xflvp,xfcolorrec,xfpetbot,xfmilk,xfhardplastic,xftetra,xfkraft,xfoldpapaer,xfoldmag,xfnotebook,xfwhiterec,xfiron,xfaluminium,xftin,xftinaluminium,xfcoconut,xfmaterilaa,xfbeerbottle,total,amount;
    Spinner ngo,city,segment;
    String swardno,sngo,snameofhub,scity,svehiclenumber,scollecpointname,shubname,ssegment,shubsupname,materialname;
    String lat,longitude;
    int x,y;
    String[] sg1={"PK","RagPicker","Commercial","Others Please Specify"};
    String[] citydata={"city-1","city-1","city-1","city-1","city-1","city-1","cit+y-1","city-1","city-1","city-1","city-1","city-1","city-1"};
    Float totalamount;
    Button b;
    SharedPreferences msharedpreferences;
    @SuppressLint("UseValueOf")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_collection);
        ngo=findViewById(R.id.spinner);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,ngodata);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        vehiclenumber=findViewById(R.id.vehiclenumber);
        materialrecieveename=findViewById(R.id.recepname);

        ngo.setAdapter(aa);
        b=findViewById(R.id.submit);
        city=findViewById(R.id.city);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,citydata);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);
        segment=findViewById(R.id.segment);
        ArrayAdapter<String> cc = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,sg1);
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        segment.setAdapter(cc);

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
        materialrecieveename=findViewById(R.id.recepname);
        materilaa=findViewById(R.id.materiala);
        beerbottle=findViewById(R.id.bottle);
        hubsupname=findViewById(R.id.supname);
        msharedpreferences=getSharedPreferences("HUB",MODE_PRIVATE);
        x=msharedpreferences.getInt("ngo",0);
        y=msharedpreferences.getInt("city",0);
        fdmw=msharedpreferences.getFloat("dmw",0);
        flvp=msharedpreferences.getFloat("lvp",0);
        fcolorrec=msharedpreferences.getFloat("colorec",0);
        fpetbot=msharedpreferences.getFloat("petbot",0);
        fmilk=msharedpreferences.getFloat("milk",0);
        fhardplastic=msharedpreferences.getFloat("hardplastic",0);
        ftetra=msharedpreferences.getFloat("tetra",0);
        fkraft=msharedpreferences.getFloat("kraft",0);
        foldpapaer=msharedpreferences.getFloat("paper",0);
        foldmag=msharedpreferences.getFloat("oldmag",0);
        fnotebook=msharedpreferences.getFloat("notebook",0);
        fwhiterec=msharedpreferences.getFloat("whiterec",0);
        firon=msharedpreferences.getFloat("iron",0);
        faluminium=msharedpreferences.getFloat("alumini",0);
        ftin=msharedpreferences.getFloat("tim",0);
        ftinaluminium=msharedpreferences.getFloat("tinalumini",0);
        fcoconut=msharedpreferences.getFloat("coconut",0);
        fmaterilaa=msharedpreferences.getFloat("materiala",0);
        shubname=msharedpreferences.getString("Supervisorname","");
        snameofhub=msharedpreferences.getString("nameofhub","");
        scollecpointname=msharedpreferences.getString("collectionname","");
        fbeerbottle=msharedpreferences.getFloat("beerbottle",0);
        ngo.setSelection(x);
        city.setSelection(y);
        final gpstracker gpsTracker = new gpstracker(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/3/d/e/")
                .build();
        final HubCollectionInterface spreadsheetWebService = retrofit.create(HubCollectionInterface.class);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swardno=wardno.getText().toString();
                sngo=ngo.getSelectedItem().toString();
                scity=city.getSelectedItem().toString();
                ssegment=segment.getSelectedItem().toString();
                svehiclenumber=vehiclenumber.getText().toString();
                snameofhub=nameofhub.getText().toString();
                shubsupname=hubsupname.getText().toString();
                xfdmw= new Float(dmw.getText().toString());
                afdmw=multiplyfloat(xfdmw,fdmw);
                //editor.putFloat("dmw",fdmw);
                xflvp=  new Float(lvp.getText().toString());
                aflvp=multiplyfloat(xflvp,flvp);
                //editor.putFloat("lvp",flvp);
                materialname=materialrecieveename.getText().toString();
                xfcolorrec=  new Float(colorrec.getText().toString());
                afcolorrec=multiplyfloat(xfcolorrec,fcolorrec);        //editor.putFloat("colorec",fcolorrec);
                xfpetbot=  new Float(petbot.getText().toString());
                afpetbot=multiplyfloat(xfpetbot,fpetbot);
                //editor.putFloat("petbot",fpetbot);
                xfmilk=  new Float(milk.getText().toString());
                afmilk=multiplyfloat(xfmilk,fmilk);
                //editor.putFloat("milk",fmilk);
                xfhardplastic=  new Float(hardplastic.getText().toString());
                afhardplastic=multiplyfloat(xfhardplastic,fhardplastic);
                //editor.putFloat("hardplastic",fhardplastic);
                xftetra=  new Float(tetra.getText().toString());
                aftetra=multiplyfloat(xftetra,ftetra);
                //editor.putFloat("tetra",ftetra);
                xfkraft=  new Float(kraft.getText().toString());
                afkraft=multiplyfloat(xfkraft,fkraft);
                //editor.putFloat("kraft",fkraft);
                xfoldpapaer=  new Float(oldpapaer.getText().toString());
                //editor.putFloat("paper",foldpapaer);
                afoldpapaer=multiplyfloat(xfoldpapaer,foldpapaer);
                xfoldmag=  new Float(oldmag.getText().toString());
                //editor.putFloat("oldmag",foldmag);
                afoldmag=multiplyfloat(xfoldmag,foldmag);
                xfnotebook=  new Float(notebook.getText().toString());
                //editor.putFloat("notebook",fnotebook);
                afnotebook=multiplyfloat(xfnotebook,fnotebook);
                xfwhiterec=  new Float(whiterec.getText().toString());
                //editor.putFloat("whiterec",fwhiterec);
                afwhiterec=multiplyfloat(xfwhiterec,fwhiterec);
                xfiron=  new Float(iron.getText().toString());
                //editor.putFloat("iron",firon);
                afiron=multiplyfloat(xfiron,firon);
                xfaluminium=  new Float(aluminium.getText().toString());
                //editor.putFloat("alumini",faluminium);
                afaluminium=multiplyfloat(xfaluminium,faluminium);
                xftin=  new Float(tin.getText().toString());
                //editor.putFloat("tim",ftin);
                aftin =multiplyfloat(xftin,ftin);
                xftinaluminium=  new Float(tinaluminium.getText().toString());
                //editor.putFloat("tinalumini",ftinaluminium);
                aftinaluminium=multiplyfloat(xftinaluminium,ftinaluminium);
                xfcoconut=  new Float(coconut.getText().toString());
                //editor.putFloat("coconut",fcoconut);
                afcoconut=multiplyfloat(xfcoconut,fcoconut);
                xfmaterilaa=  new Float(materilaa.getText().toString());
                afmaterilaa=multiplyfloat(xfmaterilaa,fmaterilaa);
                xfbeerbottle= new Float(beerbottle.getText().toString());
                afbeerbottle=multiplyfloat(xfbeerbottle,fbeerbottle);
                total=calctotal();
                totalamount=totalamountcount();
                scollecpointname=collectionpointname.getText().toString();
              //  Location mlocation = new Location(LOCATION_SERVICE);
               // new SendRequest().execute();
                if(gpsTracker.getIsGPSTrackingEnabled()) {
                    lat = String.valueOf(gpsTracker.latitude);
                    longitude = String.valueOf(gpsTracker.longitude);
                    Log.e("XXX", lat);
                    Log.e("XXX", longitude);
                    Log.e("XXX", String.valueOf(total));
                    Call<Void> completeFollowup = spreadsheetWebService.HubCollection(sngo,scity,snameofhub,materialname,ssegment
                            ,scollecpointname,svehiclenumber
                            ,afdmw,aflvp,afcolorrec,afpetbot,afmilk,afhardplastic,aftetra,
                            afkraft,afoldpapaer,afoldmag,afnotebook,afwhiterec,afiron
                            ,afaluminium,aftin,aftinaluminium,afcoconut
                            ,afmaterilaa,total,totalamount,afbeerbottle,shubsupname,lat, longitude );
                    completeFollowup.enqueue(callCallbac);
                }
            }
        });


    }

    final Callback<Void> callCallbac = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }


    };
    private Float totalamountcount() {
        return afdmw+afaluminium+afcoconut+afcolorrec+afhardplastic+afiron+afkraft+afdmw+aflvp+afmaterilaa+afmilk+afnotebook+afoldmag+afoldpapaer+afpetbot+aftetra+aftin+aftinaluminium+afwhiterec;
    }

    public Float multiplyfloat(Float x,Float y)
    {
        return x*y;
    }
    public Float calctotal()
    {
        return xfdmw+xfaluminium+xfcoconut+xfcolorrec+xfhardplastic+xfiron+xfkraft+xfdmw+xflvp+xfmaterilaa+xfmilk+xfnotebook+xfoldmag+xfoldpapaer+xfpetbot+xftetra+xftin+xftinaluminium+xfwhiterec;
    }
}
