package wow.itc.com.wow_itc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karthik on 1/26/2018.
 */

public interface CorporateFirstInterface {
    @POST("1FAIpQLSfpp7iDA5P7dxRYJ79g22iITgmbJGssDsZel6HgcT4STdHJ-A/formResponse")
    @FormUrlEncoded
    Call<Void> CorporateFirst(
            @Field("entry.416590483") String NGO,
            @Field("entry.234622195") String City,
            @Field("entry.1259021247") String area,
            @Field("entry.1926525644") String nameofngo,

            @Field("entry.344590851") String corporate,
            @Field("entry.1859184649") String personcontacted,
            @Field("entry.1160400716") String phonenumber,
            @Field("entry.1946221074") String email,
            @Field("entry.1125084177") String mou,
            @Field("entry.1118015161") String noflocations,
            @Field("entry.1307442369") String peroidofagreement,
            @Field("entry.157708368") String  qtppermonth,
            @Field("entry.625108324") String noofemployees,
            @Field("entry.554071830") String latitude,
            @Field("entry.786013108") String longitude,
            @Field("entry.116478245") String note
    );
}
