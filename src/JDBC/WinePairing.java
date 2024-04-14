package JDBC;

public class WinePairing {
    private int ID;
    private int menuID;
    private String wineName;

    public WinePairing(int ID, int menuID, String wineName) {
        this.ID = ID;
        this.menuID = menuID;
        this.wineName = wineName;
    }

    public int getID() {
        return ID;
    }

    public String[] getInfo() {
        return new String[]{String.valueOf(ID), String.valueOf(menuID), wineName};
    }
}
