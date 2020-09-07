package se2hausarbeit.ai.model.dao;

import org.junit.Test;
import org.se2.ai.model.DTO.KundeDTO;
import org.se2.ai.model.dao.KundeDAO;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class KundeDaoTest {

    @Test
    public void getInstance() {
        KundeDAO kdao = mock(KundeDAO.class);
        assertNotNull(kdao);
    }

    @Test
    public void newStudent() {
        KundeDAO kdao = mock(KundeDAO.class);
        KundeDTO k = new KundeDTO();
        k.setId(30);
        k.setNachname("tu");
        k.setVorname("Heinz");
        assertDoesNotThrow(() -> kdao.newKunde(k));
    }

    @Test
    public void getStudent() {
        KundeDAO kdao = mock(KundeDAO.class);
        assertDoesNotThrow(() -> kdao.getKunde(35));
    }

    @Test
    public void testGetStudent() {
        KundeDAO kdao = mock(KundeDAO.class);
        KundeDTO k = new KundeDTO();
        k.setId(37);
        k.setNachname("Coral");
        k.setVorname("Dina");
        k.setEmail("coraldina@gmail.de");
        assertDoesNotThrow(() -> kdao.getKunde(k.getEmail()));
    }
}


