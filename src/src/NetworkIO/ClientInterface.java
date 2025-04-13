package NetworkIO;

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
    String getIndustry();
    Contractor getContractor();
    Solicitor getSolicitor();
    void setContractor(Contractor contractor);
    void setSolicitor(Solicitor solicitor);
    void sendToUser(User user);
    void sendToServer(String message);
    String readFromServer();



//    public interface ClientInterface {
//        boolean connectToServer();
//        void disconnectFromServer();
//        String getUsername();
//        String getDisplayName();
//        String getPassword();
//        String getGenre();
//        String getProfilePhotoPath();
//        User getUser();
//        void setUser(User user);
//        void sendToServer(String message);
//        String readFromServer();
}
