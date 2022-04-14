package model;

import database.DBCustomers;

import static database.DBCustomers.getAllCustomers;

/**
 * This model class creates the Customers constructor, and all of the getters and setters.
 */
public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String createdDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;

    private String division;

    /**
     * Basic constructor, using all of the fields of this model.
     * @param customerId The primary key of the Customers table.
     * @param customerName The name of the customer.
     * @param address The address of the customer.
     * @param postalCode The postal code of the customer.
     * @param phone The contact number of the customer.
     * @param createdDate The original date of record creation.
     * @param createdBy The user that created the record.
     * @param lastUpdate The most recent time of edit.
     * @param lastUpdatedBy The most recent user to edit.
     * @param divisionId The state or province location info.
     */
    public Customers(int customerId, String customerName, String address, String postalCode, String phone,
                 String createdDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionId, String division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.division = division;
    }

    /**
     * Get the CustomerId.
     * @return the int value
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Set the CustomerId.
     * @param customerId The ID value to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Get the CustomerName.
     * @return the customerName.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Set the CustomerName.
     * @param customerName The customerName value to set.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Get the Address.
     * @return the Address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the Address
     * @param address The Address value to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the PostalCode.
     * @return the PostalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set the PostalCode.
     * @param postalCode The PostalCode to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Get the Phone number.
     * @return the Phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the Phone number.
     * @param phone the Phone number value to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the CreatedDate of this record.
     * @return the CreatedDate.
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Set the createdDate of this record.
     * @param createdDate The original date of record creation.
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Get the CreatedBy (author) of this record.
     * @return the CreatedBy (author).
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the createdBy (author) value.
     * @param createdBy The original author of this record.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the LastUpdate date.
     * @return the LastUpdate date.
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Set the lastUpdate value. The time of the most recent update.
     * @param lastUpdate The date of the most recent update.
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Get the LastUpdatedBy (new author) of this record.
     * @return the LastUpdatedBy (new author)
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Set the lastUpdate value. The author of the latest update.
     * @param lastUpdatedBy The new author of this latest update.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Get the DivisionId. (State / Province ID)
     * @return the DivisionId.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Set the divisionId value. The State / Province ID.
     * @param divisionId The State / Province ID.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Set the division value. The lookup of the State / Province
     * @return The Division ('Alaska').
     */
    public String getDivision() {
        return division;
    }

    /**
     * Set the division value. The lookup of State / Province
     * @param division is the description from the ID.
     */
    public void setDivision(String division) {
        this.division = division;
    }
}
