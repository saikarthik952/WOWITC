package wow.itc.com.wow_itc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karthik on 1/8/2018.
 */

public interface HouseHoldFirstTime {
    @POST("1FAIpQLSf9TSuCQjRiomKXMT-XC39yKIZdWwg4SkoQgPao1LCIlETY8g/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.1948772905") String NGO,
            @Field("entry.1592059876") String City,
            @Field("entry.1330730606") String WardNo,
            @Field("entry.1062856512") String PEName,
            @Field("entry.2753966") String HouseHoldname,
            @Field("entry.1554345608") String Address,
            @Field("entry.654870893") String NoofBags,
            @Field("entry.1480990847") String Note
    );
}
