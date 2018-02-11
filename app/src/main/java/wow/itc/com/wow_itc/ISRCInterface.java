package wow.itc.com.wow_itc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karthik on 1/26/2018.
 */

public interface ISRCInterface {
    @POST("1FAIpQLScHQ904V5j3YTa6hZK-OmpzPYLuF1eeOvug699hhF6W5RiJlA/formResponse")
    @FormUrlEncoded
    Call<Void> completeSchoolISRC(
            @Field("entry.244299060") String NGO,
            @Field("entry.1526406669") String City,
            @Field("entry.389229394") String AreaOFVisit,
            @Field("entry.1576812299") String Ngopename,
            @Field("entry.1138042337") String SchoolName,
            @Field("entry.1210573948") String NameOfthePersonContacted,
            @Field("entry.1444609336") String PhoneNumber,
            @Field("entry.1432912281") String Email,
            @Field("entry.1799783161") String SchoolConfirmed,
            @Field("entry.1471015261") String TotalStrength,
            @Field("entry.1266366561") String Totalatpropagation,
            @Field("entry.331093952") String Swatchwowclub,
            @Field("entry.952054964") String numberofvolunteers,
            @Field("entry.816654622") String typeofprop,
            @Field("entry.1344831225") String iecissued,
            @Field("entry.427133532") String otherbenifits,
            @Field("entry.1486570003") String treeplantation,
            @Field("entry.1673195829") String waterconservation,
            @Field("entry.1434485252") String otherspecify,
            @Field("entry.925954646") String strengthatISRC,
            @Field("entry.1228600746") String quantitycollected,
            @Field("entry.1116860892") String valueofthequantity,
            @Field("entry.1395562607") String greenchampion,
            @Field("entry.1656271789") String quantityofgreenchampion,
            @Field("entry.1866982595") String latitude,
            @Field("entry.2118485424") String longitude,
            @Field("entry.990618659") String imgprop,
            @Field("entry.1185679749") String imgisrc
    );
}
