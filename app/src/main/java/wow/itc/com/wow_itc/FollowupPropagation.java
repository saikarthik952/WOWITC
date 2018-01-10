package wow.itc.com.wow_itc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karthik on 1/9/2018.
 */

public interface FollowupPropagation {
    @POST("1FAIpQLScTUe_3XnvcrUqG67xi1F6vpe4rlVkRRv03WoC-JIQUgpj9kA/formResponse")
    @FormUrlEncoded
    Call<Void> completeFollowup(
            @Field("entry.1424518077") String NGO,
            @Field("entry.2054972315") String City,
            @Field("entry.636566260") String WardNo,
            @Field("entry.225598428") String WardName,

            @Field("entry.1273814777") int Segregated,
            @Field("entry.664955383") int notSegregated,
            @Field("entry.1140562241") String NameofNGO,
            @Field("entry.1468124154") String Note
    );
}
