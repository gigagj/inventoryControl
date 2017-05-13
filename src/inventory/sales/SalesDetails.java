package inventory.sales;

import java.math.BigDecimal;

/**
 * Created by Ranjitha on 5/2/2017.
 */
public class SalesDetails {

        private String itemCode;
        private String date;
        private String nic;
        private int quantity;


        public SalesDetails(String itemCode, String nic, int quantity, String date) {
            this.itemCode = itemCode;
            this.nic= nic;
            this.date = date;
            this.quantity = quantity;

        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getNic () { return nic; }

         public void setNic(String nic) {
        this.nic = nic;
    }

         public String getDate() {return date; }

        public void setDate(String date) { this.date = date; }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }



    }


