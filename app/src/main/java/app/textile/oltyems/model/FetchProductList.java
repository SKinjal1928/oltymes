package app.textile.oltyems.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchProductList {
    @SerializedName("shipment")
    @Expose
    private Shipment shipment;
    @SerializedName("pending_product")
    @Expose
    private List<PendingProduct> pendingProduct;
    @SerializedName("pending_proceed")
    @Expose
    private List<PendingProduct> pendingProceed;
    @SerializedName("pending_completed")
    @Expose
    private List<PendingProduct> pendingCompleted;

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public List<PendingProduct> getPendingProduct() {
        return pendingProduct;
    }

    public void setPendingProduct(List<PendingProduct> pendingProduct) {
        this.pendingProduct = pendingProduct;
    }

    public List<PendingProduct> getPendingProceed() {
        return pendingProceed;
    }

    public void setPendingProceed(List<PendingProduct> pendingProceed) {
        this.pendingProceed = pendingProceed;
    }

    public List<PendingProduct> getPendingCompleted() {
        return pendingCompleted;
    }

    public void setPendingCompleted(List<PendingProduct> pendingCompleted) {
        this.pendingCompleted = pendingCompleted;
    }

    public class PendingProduct {

        @SerializedName("no_of_ctn")
        @Expose
        private Integer noOfCtn;
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
        @SerializedName("invoice_value")
        @Expose
        private Double invoiceValue;
        @SerializedName("product_name")
        @Expose
        private String productName;

        public Integer getNoOfCtn() {
            return noOfCtn;
        }

        public void setNoOfCtn(Integer noOfCtn) {
            this.noOfCtn = noOfCtn;
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

        public Double getInvoiceValue() {
            return invoiceValue;
        }

        public void setInvoiceValue(Double invoiceValue) {
            this.invoiceValue = invoiceValue;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

    }
    public class Shipment {

        @SerializedName("ship_id")
        @Expose
        private Integer shipId;
        @SerializedName("shipment_name")
        @Expose
        private String shipmentName;
        @SerializedName("inv_id")
        @Expose
        private Integer invId;

        public Integer getShipId() {
            return shipId;
        }

        public void setShipId(Integer shipId) {
            this.shipId = shipId;
        }

        public String getShipmentName() {
            return shipmentName;
        }

        public void setShipmentName(String shipmentName) {
            this.shipmentName = shipmentName;
        }

        public Integer getInvId() {
            return invId;
        }

        public void setInvId(Integer invId) {
            this.invId = invId;
        }

    }
}
