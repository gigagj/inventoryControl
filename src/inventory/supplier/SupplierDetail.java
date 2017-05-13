package inventory.supplier;

/**
 * Created by Vinush on 5/2/2017.
 */
public class SupplierDetail {


    private String supplierID;
    private String name;
    private String address;
    private int contactNumber;


     public SupplierDetail(String supplierID, String name, String address, int contactNumber) {
        this.supplierID = supplierID;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;

    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID =  supplierID;
    }

    public String getName() {return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() {
        return address;
    }

    public void setAddressddress(String address) {
        this.address = address;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }




}