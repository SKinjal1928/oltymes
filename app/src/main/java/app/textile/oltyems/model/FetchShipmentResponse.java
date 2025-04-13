package app.textile.oltyems.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchShipmentResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class CompletedData {

        @SerializedName("totalCtnShip")
        @Expose
        private String totalCtnShip;
        @SerializedName("totalPcsShip")
        @Expose
        private String totalPcsShip;
        @SerializedName("totalCbmCompleted")
        @Expose
        private String totalCbmCompleted;
        @SerializedName("totalGrossWeightCompleted")
        @Expose
        private String totalGrossWeightCompleted;
        @SerializedName("totalCostShip")
        @Expose
        private String totalCostShip;
        @SerializedName("totalInvoiceValueCompleted")
        @Expose
        private String totalInvoiceValueCompleted;
        @SerializedName("totalSalePriceShip")
        @Expose
        private String totalSalePriceShip;

        public String getTotalCtnShip() {
            return totalCtnShip;
        }

        public void setTotalCtnShip(String totalCtnShip) {
            this.totalCtnShip = totalCtnShip;
        }

        public String getTotalPcsShip() {
            return totalPcsShip;
        }

        public void setTotalPcsShip(String totalPcsShip) {
            this.totalPcsShip = totalPcsShip;
        }

        public String getTotalCbmShip() {
            return totalCbmCompleted;
        }

        public void setTotalCbmShip(String totalCbmShip) {
            this.totalCbmCompleted = totalCbmShip;
        }

        public String getTotalWeightShip() {
            return totalGrossWeightCompleted;
        }

        public void setTotalWeightShip(String totalWeightShip) {
            this.totalGrossWeightCompleted = totalWeightShip;
        }

        public String getTotalCostShip() {
            return totalCostShip;
        }

        public void setTotalCostShip(String totalCostShip) {
            this.totalCostShip = totalCostShip;
        }

        public String getTotalInvoiceShip() {
            return totalInvoiceValueCompleted;
        }

        public void setTotalInvoiceShip(String totalInvoiceShip) {
            this.totalInvoiceValueCompleted = totalInvoiceShip;
        }

        public String getTotalSalePriceShip() {
            return totalSalePriceShip;
        }

        public void setTotalSalePriceShip(String totalSalePriceShip) {
            this.totalSalePriceShip = totalSalePriceShip;
        }

    }
    public class Data {

        @SerializedName("shipData")
        @Expose
        private ShipData shipData;
        @SerializedName("pendingData")
        @Expose
        private PendingData pendingData;
        @SerializedName("proceedData")
        @Expose
        private ProceedData proceedData;
        @SerializedName("completedData")
        @Expose
        private CompletedData completedData;
        @SerializedName("orderCounts")
        @Expose
        private OrderCounts orderCounts;

        public ShipData getShipData() {
            return shipData;
        }

        public void setShipData(ShipData shipData) {
            this.shipData = shipData;
        }

        public PendingData getPendingData() {
            return pendingData;
        }

        public void setPendingData(PendingData pendingData) {
            this.pendingData = pendingData;
        }

        public ProceedData getProceedData() {
            return proceedData;
        }

        public void setProceedData(ProceedData proceedData) {
            this.proceedData = proceedData;
        }

        public CompletedData getCompletedData() {
            return completedData;
        }

        public void setCompletedData(CompletedData completedData) {
            this.completedData = completedData;
        }

        public OrderCounts getOrderCounts() {
            return orderCounts;
        }

        public void setOrderCounts(OrderCounts orderCounts) {
            this.orderCounts = orderCounts;
        }

    }
    public class OrderCounts {

        @SerializedName("pending")
        @Expose
        private String pending;
        @SerializedName("proceed")
        @Expose
        private String proceed;
        @SerializedName("completed")
        @Expose
        private String completed;

        public String getPending() {
            return pending;
        }

        public void setPending(String pending) {
            this.pending = pending;
        }

        public String getProceed() {
            return proceed;
        }

        public void setProceed(String proceed) {
            this.proceed = proceed;
        }

        public String getCompleted() {
            return completed;
        }

        public void setCompleted(String completed) {
            this.completed = completed;
        }

    }
    public class PendingData {

        @SerializedName("totalCtnShip")
        @Expose
        private String totalCtnShip;
        @SerializedName("totalPcsShip")
        @Expose
        private String totalPcsShip;
        @SerializedName("totalCbmPending")
        @Expose
        private String totalCbmPending;
        @SerializedName("totalGrossWeightPending")
        @Expose
        private String totalGrossWeightPending;
        @SerializedName("totalCostShip")
        @Expose
        private String totalCostShip;
        @SerializedName("totalInvoiceValuePending")
        @Expose
        private String totalInvoiceValuePending;
        @SerializedName("totalSalePriceShip")
        @Expose
        private String totalSalePriceShip;

        public String getTotalCtnShip() {
            return totalCtnShip;
        }

        public void setTotalCtnShip(String totalCtnShip) {
            this.totalCtnShip = totalCtnShip;
        }

        public String getTotalPcsShip() {
            return totalPcsShip;
        }

        public void setTotalPcsShip(String totalPcsShip) {
            this.totalPcsShip = totalPcsShip;
        }

        public String getTotalCbmShip() {
            return totalCbmPending;
        }

        public void setTotalCbmShip(String totalCbmShip) {
            this.totalCbmPending = totalCbmShip;
        }

        public String getTotalWeightShip() {
            return totalGrossWeightPending;
        }

        public void setTotalWeightShip(String totalWeightShip) {
            this.totalGrossWeightPending = totalWeightShip;
        }

        public String getTotalCostShip() {
            return totalCostShip;
        }

        public void setTotalCostShip(String totalCostShip) {
            this.totalCostShip = totalCostShip;
        }

        public String getTotalInvoiceShip() {
            return totalInvoiceValuePending;
        }

        public void setTotalInvoiceShip(String totalInvoiceShip) {
            this.totalInvoiceValuePending = totalInvoiceShip;
        }

        public String getTotalSalePriceShip() {
            return totalSalePriceShip;
        }

        public void setTotalSalePriceShip(String totalSalePriceShip) {
            this.totalSalePriceShip = totalSalePriceShip;
        }

    }
    public class ProceedData {

        @SerializedName("totalCtnShip")
        @Expose
        private String totalCtnShip;
        @SerializedName("totalPcsShip")
        @Expose
        private String totalPcsShip;
        @SerializedName("totalCbmProceed")
        @Expose
        private String totalCbmProceed;
        @SerializedName("totalGrossWeightProceed")
        @Expose
        private String totalGrossWeightProceed;
        @SerializedName("totalCostShip")
        @Expose
        private String totalCostShip;
        @SerializedName("totalInvoiceValueProceed")
        @Expose
        private String totalInvoiceValueProceed;
        @SerializedName("totalSalePriceShip")
        @Expose
        private String totalSalePriceShip;

        public String getTotalCtnShip() {
            return totalCtnShip;
        }

        public void setTotalCtnShip(String totalCtnShip) {
            this.totalCtnShip = totalCtnShip;
        }

        public String getTotalPcsShip() {
            return totalPcsShip;
        }

        public void setTotalPcsShip(String totalPcsShip) {
            this.totalPcsShip = totalPcsShip;
        }

        public String getTotalCbmShip() {
            return totalCbmProceed;
        }

        public void setTotalCbmShip(String totalCbmShip) {
            this.totalCbmProceed = totalCbmShip;
        }

        public String getTotalWeightShip() {
            return totalGrossWeightProceed;
        }

        public void setTotalWeightShip(String totalWeightShip) {
            this.totalGrossWeightProceed = totalWeightShip;
        }

        public String getTotalCostShip() {
            return totalCostShip;
        }

        public void setTotalCostShip(String totalCostShip) {
            this.totalCostShip = totalCostShip;
        }

        public String getTotalInvoiceShip() {
            return totalInvoiceValueProceed;
        }

        public void setTotalInvoiceShip(String totalInvoiceShip) {
            this.totalInvoiceValueProceed = totalInvoiceShip;
        }

        public String getTotalSalePriceShip() {
            return totalSalePriceShip;
        }

        public void setTotalSalePriceShip(String totalSalePriceShip) {
            this.totalSalePriceShip = totalSalePriceShip;
        }

    }
    public class ShipData {

        @SerializedName("ship_id")
        @Expose
        private Integer shipId;
        @SerializedName("shipment_name")
        @Expose
        private String shipmentName;
        @SerializedName("inv_id")
        @Expose
        private String invId;

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

        public String getInvId() {
            return invId;
        }

        public void setInvId(String invId) {
            this.invId = invId;
        }

    }
}
