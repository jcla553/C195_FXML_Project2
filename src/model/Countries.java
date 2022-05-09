package model;

public class Countries {
    private int id;
    private String name;

    /**
     * Constructor for the Countries model
     * @param id The primary key
     * @param name Name of the country
     */
    public Countries(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the ID value of the country.
     * @return the ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the Name of the country.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * returns the data rather than the hex value for the combo box.
     * @return The country name.
     */
    public String toString() {
        return name;
    }
}
