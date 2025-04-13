package app.textile.oltyems.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchOrderList {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("order_of_ctn")
        @Expose
        private Integer orderOfCtn;
        @SerializedName("total_pcs")
        @Expose
        private Integer totalPcs;
        @SerializedName("total_cbm")
        @Expose
        private Double totalCbm;
        @SerializedName("total_gross_weight")
        @Expose
        private Double totalGrossWeight;
        @SerializedName("total_amount")
        @Expose
        private Double totalAmount;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Integer getOrderOfCtn() {
            return orderOfCtn;
        }

        public void setOrderOfCtn(Integer orderOfCtn) {
            this.orderOfCtn = orderOfCtn;
        }

        public Integer getTotalPcs() {
            return totalPcs;
        }

        public void setTotalPcs(Integer totalPcs) {
            this.totalPcs = totalPcs;
        }

        public Double getTotalCbm() {
            return totalCbm;
        }

        public void setTotalCbm(Double totalCbm) {
            this.totalCbm = totalCbm;
        }

        public Double getTotalGrossWeight() {
            return totalGrossWeight;
        }

        public void setTotalGrossWeight(Double totalGrossWeight) {
            this.totalGrossWeight = totalGrossWeight;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

    }
}
