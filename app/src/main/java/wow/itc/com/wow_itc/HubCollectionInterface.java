package wow.itc.com.wow_itc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Karthik on 1/10/2018.
 */

public interface HubCollectionInterface {
    @POST("1FAIpQLSc2QmlDRfEfXCwOrNOvITXlCp5GC9SPyDsc6mrZ0nAOuqa_4g/formResponse")
    @FormUrlEncoded
    Call<Void> HubCollection(
            @Field("entry.1984694177") String NGO,
            @Field("entry.203820026") String City,
            @Field("entry.1451851693") String NameofHub,
            @Field("entry.1528639402") String Materialrecieved,
            @Field("entry.790862145") String segment,
            @Field("entry.831811262") String collectionpointname,
            @Field("entry.1071880089") String vehiclename,
            @Field("entry.1181792519") Float dmw,
            @Field("entry.1641534960") Float Lvp,
            @Field("entry.1774588088") Float colorecord,
            @Field("entry.719354939") Float petbottles,
            @Field("entry.1737000929") Float milk,
            @Field("entry.1914556589") Float hardplastic,
            @Field("entry.2048369728") Float tetrapack,
            @Field("entry.1143222099") Float kraft,
            @Field("entry.983201327") Float oldnewspaper,
            @Field("entry.268832436") Float oldmagazine,
            @Field("entry.1531379549") Float notebooks,
            @Field("entry.833304956") Float whiterecs,
            @Field("entry.1618890734") Float metal,
            @Field("entry.1411448572") Float aluminuim,
            @Field("entry.191347737") Float tin,
            @Field("entry.1789029996") Float tinaluminuim,
            @Field("entry.716958385") Float coconut,
            @Field("entry.1710273896") Float materialA,
            @Field("entry.1082909245") Float totalkgs,
            @Field("entry.96702655") Float totalamount,
            @Field("entry.105922357") Float beerbottles,
            @Field("entry.650159043") String supname,
            @Field("entry.1230304692") String latitude,
            @Field("entry.1108470814") String longitude

    );














}
