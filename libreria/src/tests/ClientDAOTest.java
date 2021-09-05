package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import access.ClientDAO;
import model.ClientModel;

public class ClientDAOTest {
    @Test
    public void testGetClientByLogin() {
        ClientDAO clientDAO = new ClientDAO();
        ClientModel clientExpected = new ClientModel("ninja", "Andres", "Cruz", "Gaetano_McClure74@yahoo.com",
                311567843, "oVdCG_20M3hXxG2", "2021-08-20");
        ClientModel result = clientDAO.getClientByLogin("ninja");
        assertEquals(clientExpected, result);
    }
}
