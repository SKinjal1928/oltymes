package app.textile.oltyems.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchProductList {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
    public class Datum {

        @SerializedName("product_id")
        @Expose
        private Integer productId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("ean_code")
        @Expose
        private String eanCode;

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getEanCode() {
            return eanCode;
        }

        public void setEanCode(String eanCode) {
            this.eanCode = eanCode;
        }

    }
}
