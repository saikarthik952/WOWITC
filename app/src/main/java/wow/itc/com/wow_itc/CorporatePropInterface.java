package wow.itc.com.wow_itc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karthik on 1/26/2018.
 */

public interface CorporatePropInterface {
    @POST("1FAIpQLSfXiHc2aFY6QNma1xLtq7gCvfBubDw9rQJDxbZtdH5GH-FMFQ/formResponse")
    @FormUrlEncoded
    Call<Void> CorporateProp(
            @Field("entry.1782179856") String NGO,
            @Field("entry.1712487985") String City,
            @Field("entry.1041843995") String dateofcoll,
            @Field("entry.734615724") String nameofngo,

            @Field("entry.1097821880") String corporatename,
            @Field("entry.1603580192") String locationame,
            @Field("entry.1417656083") String qtymixedwaste,
            @Field("entry.168942882") String qtywhitewaste,
            @Field("entry.447298980") String lvp,
            @Field("entry.2049783560") String note

    );
}
