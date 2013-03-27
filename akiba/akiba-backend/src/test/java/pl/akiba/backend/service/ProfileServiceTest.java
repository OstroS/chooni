package pl.akiba.backend.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.akiba.model.entities.Profile;
import pl.akiba.model.exception.EntityIsNotValidException;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.ProfileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/pl/akiba/backend/appContextTest.xml" })
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    private final long userId = 0;
    private final String authCode = "787";

    @Test
    //TODO trzeba dorobic get()
    public void testCRUD() throws EntityIsNotValidException, StatusException, IOException, InterruptedException {
        //        Profile createdProfile = profileService.create(userId, authCode, prepareProfile());
        //
        //        assertNotNull(createdProfile);
        //        assertTrue(createdProfile.getId() > 0);
        //
        //        String newName = "zmieniony profile name";
        //        createdProfile.setName(newName);
        //        profileService.update(userId, authCode, createdProfile);
        //
        //        Profile updatedKind = profileService.get(userId, authCode, createdProfile.getId());
        //
        //        assertEquals(newName, updatedKind.getName());
        //
        //        profileService.delete(userId, authCode, createdProfile.getId());
        //        Profile deletedKind = null;
        //        try {
        //            deletedKind = profileService.get(userId, authCode, createdProfile.getId());
        //        } catch (EmptyResultException e) {
        //        }
        //
        //        assertTrue(deletedKind == null);
    }

    private Profile prepareProfile() {
        Profile profile = new Profile();
        profile.setActive(true);
        profile.setDef(true);
        profile.setName("profil junit testowy");

        return profile;
    }

}
