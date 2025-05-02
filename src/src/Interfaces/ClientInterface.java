package Interfaces;

import Objects.*;

/**
 * Client Interface Class: Contains all methods and fields pertaining to client objects
 *  @author Ana Farmus, Lab sec 02
 *  @version Apr 6, 2025
 */
public interface ClientInterface {
    boolean connectToServer();

    void disconnect();

    String getUsername();

    String getPassword();

    Industry getIndustry();

    Contractor getContractor();

    Solicitor getSolicitor();

    void setContractor(Contractor contractor);

    void setSolicitor(Solicitor solicitor);

    void sendToServer(String message);

    String readFromServer();
}