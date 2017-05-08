package inventory.stock;

import java.math.BigDecimal;

/**
 * Created by gigar on 30-Apr-17.
 */
public class StockDetail {
    private String itemCode;
    private String name;
    private int quantity;
    private int reOrderLevel;
    private BigDecimal price;

    public StockDetail(String itemCode,String name,int quantity,int reOrderLevel, BigDecimal price) {
        this.itemCode = itemCode;
        this.name = name;
        this.quantity = quantity;
        this.reOrderLevel = reOrderLevel;
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {return name; }

    public void setName(String name) { this.name = name; }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReOrderLevel() {
        return reOrderLevel;
    }

    public void setReOrderLevel(int reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
