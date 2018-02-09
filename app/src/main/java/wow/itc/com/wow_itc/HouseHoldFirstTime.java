package wow.itc.com.wow_itc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karthik on 1/8/2018.
 */

public interface HouseHoldFirstTime {
    @POST("1FAIpQLSfaY_wplIDgnEIgdn-f7fs_5kQ4wwXagnb9S5wxCEd1CeA0Ow/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.119856380") String NGO,
            @Field("entry.822056669") String City,
            @Field("entry.2120655429") String WardNo,
            @Field("entry.1699154976") String wardname,
            @Field("entry.830244250") String householdlocked,
            @Field("entry.1145501185") String householdinterested,
            @Field("entry.1740941215") String Natureofvisit,
            @Field("entry.1938668678") String Pename,
            @Field("entry.901131205") String householdname,
            @Field("entry.1433138190") String address,
            @Field("entry.1183783346") String phonenumber,
            @Field("entry.2007149968") String EmailID,
            @Field("entry.1819601689") String numberofmembers,
            @Field("entry.1718320059") String wetwastecomposite,
            @Field("entry.1540133419") String Imagename,
            @Field("entry.1962871546") String Landmark,
            @Field("entry.500783083") String Latitude,
            @Field("entry.616543378") String Longitude,
            @Field("entry.939810208") String Note

    );
}
