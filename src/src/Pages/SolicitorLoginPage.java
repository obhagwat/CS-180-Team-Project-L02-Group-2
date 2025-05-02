package Pages;

import Interfaces.PageInterface;
import NetworkIO.Client;

public class SolicitorLoginPage extends Page implements PageInterface {
    /**
     * constructor
     * @param client representing current user
     */
    public SolicitorLoginPage(Client client) {
        super(client);

    }
}
