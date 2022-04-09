package model;

public class Division {
    private int divisionId;
    private String divisionName;
    private int countryId;

    public Division(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }


    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String division) {
        this.divisionName = division;
    }

    public int getCountryId() {
        return countryId;
    }

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
