package model;
/**
 * The Division model class.
 */
public class Division {
    /**
     * The division id.
     */
    private int divisionId;
    /**
     * The division name.
     */
    private String divisionName;
    /**
     * The country id.
     */
    private int countryId;

    /**
     * The Division constructor.
     * @param divisionId The division id.
     * @param divisionName The division name.
     * @param countryId The country id.
     */
    public Division(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * The division id getter.
     * @return the divisionid.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * The division id setter.
     * @param divisionId The value provided.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * The division name getter.
     * @return the divisionName.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * The division name setter.
     * @param division The value provided.
     */
    public void setDivisionName(String division) {
        this.divisionName = division;
    }

    /**
     * The country id getter.
     * @return The country id.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * The country id setter.
     * @param countryId The value provided.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * returns the data rather than the hex value for the combo box.
     * @return The country name.
     */
    public String toString() {
        return divisionName;
    }
}
