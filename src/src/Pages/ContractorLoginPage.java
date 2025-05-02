package Pages;

import Interfaces.PageInterface;
import NetworkIO.Client;

public class ContractorLoginPage extends Page implements PageInterface {
    /**
     * constructor
     * @param client representing current user
     */
    public ContractorLoginPage(Client client) {
        super(client);
    }
}
