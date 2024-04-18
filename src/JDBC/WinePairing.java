package JDBC;

/**
 * Represents a wine pairing.
 */
public class WinePairing {
    /**
     * The ID of the wine pairing.
     */
    private final int ID;

    /**
     * The ID of the menu associated with the wine pairing.
     */
    private final int menuID;

    /**
     * The name of the wine.
     */
    private final String wineName;

    /**
     * Constructs a WinePairing object with the given ID, menu ID, and wine name.
     *
     * @param ID       the ID of the wine pairing
     * @param menuID   the ID of the menu associated with the wine pairing
     * @param wineName the name of the wine
     */
    public WinePairing(int ID, int menuID, String wineName) {
        this.ID = ID;
        this.menuID = menuID;
        this.wineName = wineName;
    }

    /**
     * Gets the ID of the wine pairing.
     *
     * @return the ID of the wine pairing
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets an array containing the information of the wine pairing.
     *
     * @return an array containing the ID, menu ID, and wine name of the wine pairing
     */
    public String[] getInfo() {
        return new String[]{String.valueOf(ID), String.valueOf(menuID), wineName};
    }
}
