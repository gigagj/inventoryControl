package inventory.purchase;

/**
 * Created by Owner on 2017-05-02.
 */
public class PurchaseDetail {

    private String itemCode;
    private String supplierId;
    private int quantity;
    private String purchaseDate;

    public PurchaseDetail(String iCode,String supId,int qty,String date) {
        this.itemCode = iCode;
        this.supplierId = supId;
        this.quantity = qty;
        this.purchaseDate = date;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
