package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HubCollectionActivity extends AppCompatActivity  {
    gpstracker gpsTracker;
    ArrayList<String> resultantdata;
    float afdmw,aflvp,afcolorrec,afpetbot,afmilk,afhardplastic,aftetra,afkraft,afoldpapaer,afoldmag,afnotebook,afwhiterec,afiron,afaluminium,aftin,aftinaluminium,afcoconut,afmaterilaa,afbeerbottle;
ArrayAdapter<String> adapters;
    EditText wardno,nameofhub,vehiclenumber,materialrecieveename,collectionpointname,dmw,lvp,colorrec,petbot,milk,hardplastic,tetra,kraft,oldpapaer,oldmag,notebook,whiterec,iron,aluminium,tin,tinaluminium,coconut,materilaa,beerbottle,hubsupname;
    float fdmw,flvp,fcolorrec,fpetbot,fmilk,fhardplastic,ftetra,fkraft,foldpapaer,foldmag,fnotebook,fwhiterec,firon,faluminium,ftin,ftinaluminium,fcoconut,fmaterilaa,fbeerbottle;
    float xfdmw,xflvp,xfcolorrec,xfpetbot,xfmilk,xfhardplastic,xftetra,xfkraft,xfoldpapaer,xfoldmag,xfnotebook,xfwhiterec,xfiron,xfaluminium,xftin,xftinaluminium,xfcoconut,xfmaterilaa,xfbeerbottle,total,amount;
    Spinner ngo,city,segment;
    String swardno,sngo,snameofhub,scity,svehiclenumber,scollecpointname,shubname,ssegment,shubsupname,materialname;
    String lat,longitude;
    int x,y;
    Button review;
    Activity mContext;
    StringBuilder results;
    ProgressDialog md;
    String ab,bc,cd,dd,ee,f,g,h,ij,j,k,l,mm,nn,o,p,q,rr,s,t,u,v,w;
    String[] sg1={"PK","RagPicker","Commercial","Others Please Specify"};
Context c;
    Float totalamount;
    Button b;
    ListView lsitviewl;
    SharedPreferences msharedpreferences;
    @SuppressLint("UseValueOf")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext= HubCollectionActivity.this;
        setContentView(R.layout.activity_hub_collection);
        ngo=findViewById(R.id.spinner);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.ngos));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        vehiclenumber=findViewById(R.id.vehiclenumber);
        materialrecieveename=findViewById(R.id.recepname);
results= new StringBuilder();
review=findViewById(R.id.datareview);
resultantdata=new ArrayList<String>();
adapters= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,resultantdata);
        ngo.setAdapter(aa);
        b=findViewById(R.id.submit);
        city=findViewById(R.id.city);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.cities));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(bb);
        segment=findViewById(R.id.segment);
        ArrayAdapter<String> cc = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,sg1);
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        segment.setAdapter(cc);
md= new ProgressDialog(this);
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
        scollecpointname=msharedpreferences.getString("collectioname","");
        fbeerbottle=msharedpreferences.getFloat("beerbottle",0);
        swardno = msharedpreferences.getString("wardno", "");
         for(int i=0;i<resultantdata.size();i++)
          resultantdata.remove(i);
        ngo.setSelection(x);
        city.setSelection(y);
        nameofhub.setText(snameofhub);
        collectionpointname.setText(scollecpointname);
        hubsupname.setText(shubname);
        wardno.setText(swardno);
         gpsTracker = new gpstracker(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/u/3/d/e/")
                .build();
        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HubCollectionRegister.class);
                i.putExtra("editoption","editing");
                startActivity(i);
            }
        });
        final HubCollectionInterface spreadsheetWebService = retrofit.create(HubCollectionInterface.class);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                swardno=wardno.getText().toString();
                sngo=ngo.getSelectedItem().toString();
                scity=city.getSelectedItem().toString();
                ssegment=segment.getSelectedItem().toString();
                svehiclenumber=vehiclenumber.getText().toString();
                snameofhub=nameofhub.getText().toString();
                shubsupname=hubsupname.getText().toString();
                cd=dmw.getText().toString();
                ab=lvp.getText().toString();
                materialname=materialrecieveename.getText().toString();
                bc=colorrec.getText().toString();s=materilaa.getText().toString();
                dd=petbot.getText().toString();
                f= hardplastic.getText().toString();rr= coconut.getText().toString();
                g= tetra.getText().toString(); k=oldmag.getText().toString();   l= notebook.getText().toString();
                h=   kraft.getText().toString();  o= aluminium.getText().toString();  q=tinaluminium.getText().toString();
                ij=   oldpapaer.getText().toString(); nn= iron.getText().toString();              scollecpointname=collectionpointname.getText().toString();
                ee= milk.getText().toString();  mm=  whiterec.getText().toString(); t=beerbottle.getText().toString();
                materialname=materialrecieveename.getText().toString(); p=tin.getText().toString();
for(int i=0;i<adapters.getCount();i++)
    adapters.clear();

//resultantdata.notify();
                    //results.append("Total Amount :").append(totalamount).append("\n");

                    // String names[] ={"A","B","C","D"};
                if(check(wardno)|| check(nameofhub)|| check(collectionpointname)|| check(dmw)|| check(lvp)||
                        check(colorrec)||check(petbot)|| check(milk)||check(hardplastic)||check(tetra)||
                        check(kraft)||check(oldpapaer)||check(oldmag)||check(notebook)||check(whiterec)||
                        check(iron)||check(aluminium)||check(tin)||check(tinaluminium)||check(coconut)||check(materilaa)
                        ||check(beerbottle)||check(hubsupname) || check(vehiclenumber))
                {
                    Toast.makeText(HubCollectionActivity.this,"All Fields are mandatory",Toast.LENGTH_LONG).show();
                }
                else {

                    //editor.putFloat("lvp",flvp);




                    xfdmw= new Float(cd);
                    afdmw=multiplyfloat(xfdmw,fdmw);
                    resultantdata.add("DMW : "+xfdmw+"Cost :"+afdmw+" Cost Per KG : "+fdmw);

                    xflvp=  new Float(ab);
                    aflvp=multiplyfloat(xflvp,flvp);
                    resultantdata.add("LVP : "+xflvp+"Cost :"+aflvp+" Cost Per KG :"+flvp);
                    xfcolorrec=  new Float(bc);
                    afcolorrec=multiplyfloat(xfcolorrec,fcolorrec);

                    resultantdata.add("Color Record : "+xfcolorrec+" Cost :"+afcolorrec+" Cost Per KG : "+fcolorrec);//editor.putFloat("colorec",fcolorrec);

                    xfpetbot=  new Float(dd);
                    afpetbot=multiplyfloat(xfpetbot,fpetbot);

                    resultantdata.add("Pet Bottles : "+xfpetbot+" Cost :"+afpetbot+" Cost Per KG : "+fpetbot);
                    //editor.putFloat("petbot",fpetbot);

                    xfmilk=  new Float(ee);
                    afmilk=multiplyfloat(xfmilk,fmilk);
                    //editor.putFloat("milk",fmilk);

                    resultantdata.add("Milk packets: "+xfmilk+" Cost : "+afmilk+" Cost Per KG : "+fmilk);
                    xfhardplastic=  new Float(f);
                    afhardplastic=multiplyfloat(xfhardplastic,fhardplastic);
                    //editor.putFloat("hardplastic",fhardplastic);

                    resultantdata.add("Hard Plastic : "+xfhardplastic+" Cost : "+afhardplastic+" Cost Per KG : "+fhardplastic);

                    xftetra=  new Float(g);
                    aftetra=multiplyfloat(xftetra,ftetra);

                    resultantdata.add("Tetra Packs : "+xftetra+" Cost :"+aftetra+" Cost Per KG : "+ftetra);
                    //editor.putFloat("tetra",ftetra);
                    xfkraft=  new Float(h);
                    afkraft=multiplyfloat(xfkraft,fkraft);

                    resultantdata.add("Kraft : "+xfkraft+" Cost :"+afkraft+" Cost Per KG : "+fkraft);
                    //editor.putFloat("kraft",fkraft);

                    xfoldpapaer=  new Float(ij);
                    //editor.putFloat("paper",foldpapaer);
                    afoldpapaer=multiplyfloat(xfoldpapaer,foldpapaer);

                    resultantdata.add("Old Papers : "+xfoldpapaer+" Cost :"+afoldpapaer+" Cost Per KG : "+foldpapaer);

                    xfoldmag=  new Float(k);
                    //editor.putFloat("oldmag",foldmag);
                    afoldmag=multiplyfloat(xfoldmag,foldmag);

                    resultantdata.add("Old Magazines : "+xfoldmag+" Cost :"+afoldmag+" Cost Per KG : "+fdmw);

                    xfnotebook=  new Float(l);
                    //editor.putFloat("notebook",fnotebook);
                    afnotebook=multiplyfloat(xfnotebook,fnotebook);

                    resultantdata.add("NoteBooks: "+xfnotebook+" Cost :"+afnotebook+" Cost Per KG : "+fnotebook);

                    xfwhiterec=  new Float(mm);
                    //editor.putFloat("whiterec",fwhiterec);
                    afwhiterec=multiplyfloat(xfwhiterec,fwhiterec);

                    resultantdata.add("White Records : "+xfwhiterec+" Cost :"+afwhiterec+" Cost Per KG : "+fwhiterec);

                    xfiron=  new Float(nn);
                    //editor.putFloat("iron",firon);
                    afiron=multiplyfloat(xfiron,firon);

                    resultantdata.add("Metal(Iron) : "+xfiron+" Cost :"+afiron+" Cost Per KG : "+firon);

                    xfaluminium=  new Float(o);
                    //editor.putFloat("alumini",faluminium);
                    afaluminium=multiplyfloat(xfaluminium,faluminium);

                    resultantdata.add("Aluminuim : "+xfaluminium+" Cost :"+afaluminium+" Cost Per KG : "+firon);

                    xftin=  new Float(p);
                    //editor.putFloat("tim",ftin);
                    aftin =multiplyfloat(xftin,ftin);

                    resultantdata.add("Tin : "+xftin+" Cost :"+aftin+" Cost Per KG : "+ftin);

                    xftinaluminium=  new Float(q);
                    //editor.putFloat("tinalumini",ftinaluminium);
                    aftinaluminium=multiplyfloat(xftinaluminium,ftinaluminium);

                    resultantdata.add("Tin(Aluminuim) : "+xftinaluminium+" Cost :"+aftinaluminium+" Cost Per KG : "+ftinaluminium);

                    xfcoconut=  new Float(rr);
                    //editor.putFloat("coconut",fcoconut);
                    afcoconut=multiplyfloat(xfcoconut,fcoconut);
                    resultantdata.add("Coconut Shells : "+xfcoconut+" Cost :"+afcoconut+" Cost Per KG : "+fcoconut);

                    xfmaterilaa=  new Float(s);
                    afmaterilaa=multiplyfloat(xfmaterilaa,fmaterilaa);
                    resultantdata.add("Material A : "+xfmaterilaa+" Cost :"+afmaterilaa+" Cost Per KG : "+fmaterilaa);

                    xfbeerbottle= new Float(t);
                    afbeerbottle=multiplyfloat(xfbeerbottle,fbeerbottle);

                    resultantdata.add("Beer Bottles: "+xfbeerbottle+" Cost :"+afbeerbottle+" Cost Per Bottle : "+fbeerbottle);
                    total=calctotal();
                    totalamount=totalamountcount();
                    resultantdata.add("Total Weight : "+total);
                    resultantdata.add("Total Amount : "+totalamount);

                    Intent l = new Intent(HubCollectionActivity.this, HubCollectionData.class);
                    l.putExtra("data", resultantdata);
                    startActivity(l);
                }
    }

        });

b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(check(wardno)|| check(nameofhub)|| check(collectionpointname)|| check(dmw)|| check(lvp)||
                check(colorrec)||check(petbot)|| check(milk)||check(hardplastic)||check(tetra)||
                check(kraft)||check(oldpapaer)||check(oldmag)||check(notebook)||check(whiterec)||
                check(iron)||check(aluminium)||check(tin)||check(tinaluminium)||check(coconut)||check(materilaa)
                ||check(beerbottle)||check(hubsupname) || check(vehiclenumber))
        {
            Toast.makeText(HubCollectionActivity.this,"All Fields are mandatory",Toast.LENGTH_LONG).show();
        }
        else {
            if (gpsTracker.getIsGPSTrackingEnabled()) {

                md.setTitle("Submitting");
                md.setCancelable(false);
                md.show();
                lat = String.valueOf(gpsTracker.latitude);
                longitude = String.valueOf(gpsTracker.longitude);
                Log.e("XXX", lat);
                Log.e("XXX", longitude);
                Log.e("XXX", String.valueOf(total));
                Call<Void> completeFollowup = spreadsheetWebService.HubCollection(sngo, scity, snameofhub, materialname, ssegment
                        , scollecpointname, svehiclenumber
                        , afdmw, aflvp, afcolorrec, afpetbot, afmilk, afhardplastic, aftetra,
                        afkraft, afoldpapaer, afoldmag, afnotebook, afwhiterec, afiron
                        , afaluminium, aftin, aftinaluminium, afcoconut
                        , afmaterilaa, total, totalamount, afbeerbottle, shubsupname, lat, longitude);
                completeFollowup.enqueue(callCallbac);
            }
        }
    }
});
    }


    final Callback<Void> callCallbac = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
            md.dismiss();
            Intent lp= getIntent();
            lp.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            startActivity(lp);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }


    };
    private Float totalamountcount() {
        return afdmw+afaluminium+afcoconut+afcolorrec+afhardplastic+afiron+afkraft+afdmw+aflvp+afmaterilaa+afmilk+afnotebook+afoldmag+afoldpapaer+afpetbot+aftetra+aftin+aftinaluminium+afwhiterec+afbeerbottle;
    }

    public Float multiplyfloat(Float x,Float y)
    {
        return x*y;
    }
    public Float calctotal()
    {
        return xfdmw+xfaluminium+xfcoconut+xfcolorrec+xfhardplastic+xfiron+xfkraft+xfdmw+xflvp+xfmaterilaa+xfmilk+xfnotebook+xfoldmag+xfoldpapaer+xfpetbot+xftetra+xftin+xftinaluminium+xfwhiterec+xfbeerbottle;
    }

    @Override
    protected void onStart() {
        super.onStart();
        for(int i=0;i<resultantdata.size();i++)
            resultantdata.remove(i);
    }
    public boolean check(EditText edt)
    {

        if(edt.getText().toString().isEmpty()) {
            edt.setError("Field should not be empty");
            return true;
        }
        return false;
    }
}
