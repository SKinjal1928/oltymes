package app.textile.oltyems.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchShipmentList {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
    public class Datum {

        @SerializedName("ship_id")
        @Expose
        private Integer shipId;
        @SerializedName("inv_id")
        @Expose
        private String invId;
        @SerializedName("shipment_name")
        @Expose
        private String shipmentName;

        public Integer getShipId() {
            return shipId;
        }

        public void setShipId(Integer shipId) {
            this.shipId = shipId;
        }

        public String getInvId() {
            return invId;
        }

        public void setInvId(String invId) {
            this.invId = invId;
        }

        public String getShipmentName() {
            return shipmentName;
        }

        public void setShipmentName(String shipmentName) {
            this.shipmentName = shipmentName;
        }

    }
}
