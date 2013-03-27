package pl.akiba.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.akiba.model.entities.Kind;
import pl.akiba.model.exception.EmptyResultException;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.KindService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/pl/akiba/backend/appContextTest.xml" })
public class KindServiceTest {

    @Autowired
    private KindService kindService;

    private final long userId = 0;
    private final String authCode = "787";

    @Test
    public void testCRUD() throws EntityIsNotValidException, StatusException, IOException, InterruptedException {
        Kind createdKind = kindService.create(userId, authCode, prepareKind());

        assertNotNull(createdKind);
        assertTrue(createdKind.getId() > 0);

        String newName = "zmieniony kind";
        createdKind.setName(newName);
        kindService.update(userId, authCode, createdKind);

        Kind updatedKind = kindService.get(userId, authCode, createdKind.getId());

        assertEquals(newName, updatedKind.getName());

        kindService.delete(userId, authCode, createdKind.getId());
        Kind deletedKind = null;
        try {
            deletedKind = kindService.get(userId, authCode, createdKind.getId());
        } catch (EmptyResultException e) {
        }

        assertTrue(deletedKind == null);
    }

    private Kind prepareKind() {
        Kind kind = new Kind();
        kind.setName("kind junit test");

        return kind;
    }

}
