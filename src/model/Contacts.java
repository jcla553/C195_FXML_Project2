package model;
/**
 * The Contacts model class.
 */
public class Contacts {
    /**
     * Unique identifier for the contacts model.
     */
    private int contactId;
    /**
     * The contact name.
     */
    private String contactName;
    /**
     * The email address.
     */
    private String email;

    /**
     * Contacts constructor
     * @param contactId The unique identifier.
     * @param contactName The contact name.
     * @param email The email address.
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Get the ContactId.
     * @return the int value.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Set the ContactId.
     * @param contactId The ID value to set.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Get the Contact Name.
     * @return The String value.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Set the Contact Name.
     * @param contactName The value to assign.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Get the email address.
     * @return The String value.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address.
     * @param email The value to assign.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the data rather than the hex value for the combo box.
     * @return The country name.
     */
    public String toString() {
        return contactName;
    }
}
