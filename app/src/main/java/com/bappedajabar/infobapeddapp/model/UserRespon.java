
package com.bappedajabar.infobapeddapp.model;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UserRespon {

    @SerializedName("data")
    private User mData;
    @SerializedName("message")
    private String mMessage;

    public User getData() {
        return mData;
    }

    public void setData(User data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
