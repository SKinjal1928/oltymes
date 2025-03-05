package app.textile.oltyems.pojo;

public class ProductReq {
    String product_id, order_of_ctn;

    public ProductReq(String product_id, String order_of_ctn) {
        this.product_id = product_id;
        this.order_of_ctn = order_of_ctn;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOrder_of_ctn() {
        return order_of_ctn;
    }

    public void setOrder_of_ctn(String order_of_ctn) {
        this.order_of_ctn = order_of_ctn;
    }
}
