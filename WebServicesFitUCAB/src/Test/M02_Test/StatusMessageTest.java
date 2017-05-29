import Exceptions.StatusMessage;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatusMessageTest {

    StatusMessage status;

    @Test
    public void testStatusMessageCreated(){
        status = new StatusMessage(1, "Prueba exitosa");
        assertEquals("Estado del codigo :", 1, status.getCode());
        assertEquals("Estado del mensaje: ", "Prueba exitosa", status.getMessage());
    }

    @Test
    public void testStatusNotCreated() {
        assertEquals("Mensaje vacio: ", null, status);
    }
}