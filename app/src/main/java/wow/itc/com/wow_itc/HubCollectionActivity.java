package wow.itc.com.wow_itc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    gpstracker gpsTracker;
    float afdmw,aflvp,afcolorrec,afpetbot,afmilk,afhardplastic,aftetra,afkraft,afoldpapaer,afoldmag,afnotebook,afwhiterec,afiron,afaluminium,aftin,aftinaluminium,afcoconut,afmaterilaa,afbeerbottle;
    //String[] ngodata={"NGO-1","NGO-2","NGO-3","NGO-4","NGO-5","NGO-6","Others Please Specify"};
    EditText wardno,nameofhub,vehiclenumber,materialrecieveename,collectionpointname,dmw,lvp,colorrec,petbot,milk,hardplastic,tetra,kraft,oldpapaer,oldmag,notebook,whiterec,iron,aluminium,tin,tinaluminium,coconut,materilaa,beerbottle,hubsupname;
    float fdmw,flvp,fcolorrec,fpetbot,fmilk,fhardplastic,ftetra,fkraft,foldpapaer,foldmag,fnotebook,fwhiterec,firon,faluminium,ftin,ftinaluminium,fcoconut,fmaterilaa,fbeerbottle;
    float xfdmw,xflvp,xfcolorrec,xfpetbot,xfmilk,xfhardplastic,xftetra,xfkraft,xfoldpapaer,xfoldmag,xfnotebook,xfwhiterec,xfiron,xfaluminium,xftin,xftinaluminium,xfcoconut,xfmaterilaa,xfbeerbottle,total,amount;
    Spinner ngo,city,segment;
    String swardno,sngo,snameofhub,scity,svehiclenumber,scollecpointname,shubname,ssegment,shubsupname,materialname;
    String lat,longitude;
    int x,y;
    Activity mContext;
    StringBuilder results;
    String[] sg1={"PK","RagPicker","Commercial","Others Please Specify"};
    //String[] citydata={"city-1","city-1","city-1","city-1","city-1","city-1","cit+y-1","city-1","city-1","city-1","city-1","city-1","city-1"};
    Float totalamount;
    Button b;
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
        swardno = msharedpreferences.getString("wardno", "");//=msharedpreferences.getString("wardname","");

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
                results.append("DMW :").append(xfdmw).append("Cost :").append(afdmw).append(" Cost Per KG :").append(fdmw).append("\n");
                xflvp=  new Float(lvp.getText().toString());
                aflvp=multiplyfloat(xflvp,flvp);

                results.append("LVP :").append(xflvp).append("Cost :").append(aflvp).append(" Cost Per KG :").append(flvp).append("\n");
                //editor.putFloat("lvp",flvp);
                materialname=materialrecieveename.getText().toString();
                xfcolorrec=  new Float(colorrec.getText().toString());
                afcolorrec=multiplyfloat(xfcolorrec,fcolorrec);

                results.append("Color Record :").append(xfcolorrec).append("Cost :").append(afcolorrec).append(" Cost Per KG :").append(fcolorrec).append("\n");//editor.putFloat("colorec",fcolorrec);
                xfpetbot=  new Float(petbot.getText().toString());
                afpetbot=multiplyfloat(xfpetbot,fpetbot);

                results.append("Pet Bottles :").append(xfpetbot).append("Cost :").append(afpetbot).append(" Cost Per KG :").append(fpetbot).append("\n");
                //editor.putFloat("petbot",fpetbot);
                xfmilk=  new Float(milk.getText().toString());
                afmilk=multiplyfloat(xfmilk,fmilk);
                //editor.putFloat("milk",fmilk);

                results.append("Milk packets:").append(xfmilk).append("Cost :").append(afmilk).append(" Cost Per KG :").append(fmilk).append("\n");
                xfhardplastic=  new Float(hardplastic.getText().toString());
                afhardplastic=multiplyfloat(xfhardplastic,fhardplastic);
                //editor.putFloat("hardplastic",fhardplastic);

                results.append("Hard Plastic :").append(xfhardplastic).append("Cost :").append(afhardplastic).append(" Cost Per KG :").append(fhardplastic).append("\n");
                xftetra=  new Float(tetra.getText().toString());
                aftetra=multiplyfloat(xftetra,ftetra);

                results.append("Tetra Packs :").append(xftetra).append("Cost :").append(aftetra).append(" Cost Per KG :").append(ftetra).append("\n");
                //editor.putFloat("tetra",ftetra);
                xfkraft=  new Float(kraft.getText().toString());
                afkraft=multiplyfloat(xfkraft,fkraft);

                results.append("Kraft :").append(xfkraft).append("Cost :").append(afkraft).append(" Cost Per KG :").append(fkraft).append("\n");
                //editor.putFloat("kraft",fkraft);
                xfoldpapaer=  new Float(oldpapaer.getText().toString());
                //editor.putFloat("paper",foldpapaer);
                afoldpapaer=multiplyfloat(xfoldpapaer,foldpapaer);

                results.append("Old Papers :").append(xfoldpapaer).append("Cost :").append(afoldpapaer).append(" Cost Per KG :").append(foldpapaer).append("\n");
                xfoldmag=  new Float(oldmag.getText().toString());
                //editor.putFloat("oldmag",foldmag);
                afoldmag=multiplyfloat(xfoldmag,foldmag);

                results.append("Old Magazines :").append(xfoldmag).append("Cost :").append(afoldmag).append(" Cost Per KG :").append(fdmw).append("\n");
                xfnotebook=  new Float(notebook.getText().toString());
                //editor.putFloat("notebook",fnotebook);
                afnotebook=multiplyfloat(xfnotebook,fnotebook);

                results.append("NoteBooks:").append(xfnotebook).append("Cost :").append(afnotebook).append(" Cost Per KG :").append(fnotebook).append("\n");
                xfwhiterec=  new Float(whiterec.getText().toString());
                //editor.putFloat("whiterec",fwhiterec);
                afwhiterec=multiplyfloat(xfwhiterec,fwhiterec);

                results.append("White Records :").append(xfwhiterec).append("Cost :").append(afwhiterec).append(" Cost Per KG :").append(fwhiterec).append("\n");
                xfiron=  new Float(iron.getText().toString());
                //editor.putFloat("iron",firon);
                afiron=multiplyfloat(xfiron,firon);

                results.append("Metal(Iron) :").append(xfiron).append("Cost :").append(afiron).append(" Cost Per KG :").append(firon).append("\n");
                xfaluminium=  new Float(aluminium.getText().toString());
                //editor.putFloat("alumini",faluminium);
                afaluminium=multiplyfloat(xfaluminium,faluminium);

                results.append("Aluminuim :").append(xfaluminium).append("Cost :").append(afaluminium).append(" Cost Per KG :").append(firon).append("\n");
                xftin=  new Float(tin.getText().toString());
                //editor.putFloat("tim",ftin);
                aftin =multiplyfloat(xftin,ftin);

                results.append("Tin :").append(xftin).append("Cost :").append(aftin).append(" Cost Per KG :").append(ftin
                ).append("\n");
                xftinaluminium=  new Float(tinaluminium.getText().toString());
                //editor.putFloat("tinalumini",ftinaluminium);
                aftinaluminium=multiplyfloat(xftinaluminium,ftinaluminium);

                results.append("Tin(Aluminuim) :").append(xftinaluminium).append("Cost :").append(aftinaluminium).append(" Cost Per KG :").append(ftinaluminium).append("\n");
                xfcoconut=  new Float(coconut.getText().toString());
                //editor.putFloat("coconut",fcoconut);
                afcoconut=multiplyfloat(xfcoconut,fcoconut);
                results.append("Coconut Shells :").append(xfcoconut).append("Cost :").append(afcoconut).append(" Cost Per KG :").append(fcoconut).append("\n");

                xfmaterilaa=  new Float(materilaa.getText().toString());
                afmaterilaa=multiplyfloat(xfmaterilaa,fmaterilaa);
                results.append("Material A :").append(xfmaterilaa).append("Cost :").append(afmaterilaa).append(" Cost Per KG :").append(fmaterilaa).append("\n");

                xfbeerbottle= new Float(beerbottle.getText().toString());
                afbeerbottle=multiplyfloat(xfbeerbottle,fbeerbottle);

                results.append("Beer Bottles:").append(xfbeerbottle).append("Cost :").append(afbeerbottle).append(" Cost Per Bottle :").append(fbeerbottle).append("\n");
                total=calctotal();
                totalamount=totalamountcount();

                results.append("Total Weight :").append(total).append("\n");
                results.append("Total Amount :").append(totalamount).append("\n");
                scollecpointname=collectionpointname.getText().toString();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

                //Setting Dialog Title
                alertDialog.setTitle("Review DATA");

                //Setting Dialog Message
                alertDialog.setMessage(results);

                //On Pressing Setting button
                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
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

                //On pressing cancel button
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                alertDialog.show();

              //  Location mlocation = new Location(LOCATION_SERVICE);
               // new SendRequest().execute();

            }
        });


    }

    final Callback<Void> callCallbac = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
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
