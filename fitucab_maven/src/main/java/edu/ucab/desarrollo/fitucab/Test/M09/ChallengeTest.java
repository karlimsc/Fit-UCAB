package M09;

import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by JuanMendez on 2/7/2017.
 */
public class ChallengeTest {

    @Test(expected = AssertionError.class)
    public void ChallengeTestPUF() {
        Challenge challenge = null;
        assertNotNull(challenge);
    }


    @Test(expected = AssertionError.class)
    public void ChallengeTestPUE() {
        Challenge challenge = new Challenge ();
        assertNull(challenge);
    }

    @Test
    public void setIdTestPUE() {
        Challenge challenge = new Challenge();
        challenge.setId(1);
        assertEquals(1,challenge.getId());
    }

    @Test(expected = AssertionError.class)
    public void setIdTestPUFE() {
        Challenge challenge = new Challenge();
        challenge.setId(1);
        assertEquals(2,challenge.getId());
    }

    @Test
    public void getIdTestPUE() {
        Challenge challenge = new Challenge(1,"Reto de Prueba","Descripcion reto de prueba",10);
        assertEquals(1,challenge.getId());
    }

    @Test(expected = AssertionError.class)
    public void getIdTestPUFE() {
        Challenge challenge = new Challenge(2,"Reto de Prueba","Descripcion reto de prueba",10);
        assertEquals(1,challenge.getId());
    }

    @Test
    public void setNameTestPUE() {
        Challenge challenge = new Challenge();
        challenge.setName("prueba de reto");
        assertEquals("prueba de reto",challenge.getName());
    }

    @Test(expected = AssertionError.class)
    public void setNameTestPUFE() {
        Challenge challenge = new Challenge();
        challenge.setName("prueba de reto");
        assertEquals("prueba de reto 2",challenge.getName());
    }

    @Test
    public void getNameTestPUE() {
        Challenge challenge = new Challenge(1,"Reto de Prueba","Descripcion reto de prueba",10);
        assertEquals("Reto de Prueba",challenge.getName());
    }

    @Test(expected = AssertionError.class)
    public void getNameTestPUFE() {
        Challenge challenge = new Challenge(2,"Reto de Prueba","Descripcion reto de prueba",10);
        assertEquals("Reto de Prueba 2",challenge.getName());
    }

    @Test
    public void setDescriptionTestPUE() {
        Challenge challenge = new Challenge();
        challenge.setDescription("Descripcion reto de prueba");
        assertEquals("Descripcion reto de prueba",challenge.getDescription());
    }

    @Test(expected = AssertionError.class)
    public void setDescriptionTestPUFE() {
        Challenge challenge = new Challenge();
        challenge.setName("Descripcion reto de prueba");
        assertEquals("Descripcion reto de prueba 2",challenge.getDescription());
    }

    @Test
    public void getDescriptionTestPUE() {
        Challenge challenge = new Challenge(1,"Reto de Prueba","Descripcion reto de prueba",10);
        assertEquals("Descripcion reto de prueba",challenge.getDescription());
    }

    @Test(expected = AssertionError.class)
    public void getDescriptionTestPUFE() {
        Challenge challenge = new Challenge(2,"Reto de Prueba","Descripcion reto de prueba",10);
        assertEquals("Descripcion reto de prueba 2",challenge.getDescription());
    }

    @Test
    public void setScoreTestPUE() {
        Challenge challenge = new Challenge();
        challenge.setScore(10);
        assertEquals(10,challenge.getScore());
    }

    @Test(expected = AssertionError.class)
    public void setScoreTestPUFE() {
        Challenge challenge = new Challenge();
        challenge.setScore(10);
        assertEquals(20,challenge.getScore());
    }

    @Test
    public void getScoreTestPUE() {
        Challenge challenge = new Challenge(30);
        assertEquals(30,challenge.getScore());
    }

    @Test(expected = AssertionError.class)
    public void getScoreTestPUFE() {
        Challenge challenge = new Challenge(30);
        assertEquals(40,challenge.getScore());
    }

    @Test
    public void setAchieveTestPUE() {
        Challenge challenge = new Challenge();
        challenge.setAchieve(10);
        assertEquals(10,challenge.getAchieve());
    }

    @Test(expected = AssertionError.class)
    public void setAchieveTestPUFE() {
        Challenge challenge = new Challenge();
        challenge.setAchieve(10);
        assertEquals(20,challenge.getAchieve());
    }

    @Test
    public void getAchieveTestPUE() {
        Challenge challenge = new Challenge(10,20);
        assertEquals(10,challenge.getAchieve());
    }

    @Test(expected = AssertionError.class)
    public void getAchieveTestPUFE() {
        Challenge challenge = new Challenge(20,30);
        assertEquals(40,challenge.getAchieve());
    }

    @Test
    public void setUnachieveTestPUE() {
        Challenge challenge = new Challenge();
        challenge.setUnachieve(10);
        assertEquals(10,challenge.getUnachieve());
    }

    @Test(expected = AssertionError.class)
    public void setUnachieveTestPUFE() {
        Challenge challenge = new Challenge();
        challenge.setUnachieve(10);
        assertEquals(20,challenge.getUnachieve());
    }

    @Test
    public void getUnachieveTestPUE() {
        Challenge challenge = new Challenge(10,20);
        assertEquals(20,challenge.getUnachieve());
    }

    @Test(expected = AssertionError.class)
    public void getUnachieveTestPUFE() {
        Challenge challenge = new Challenge(20,30);
        assertEquals(20,challenge.getUnachieve());
    }

    @Test
    public void setLevelTestPUE() {
        Challenge challenge = new Challenge();
        challenge.setLevel(10);
        assertEquals(10,challenge.getLevel());
    }

    @Test(expected = AssertionError.class)
    public void setLevelTestPUFE() {
        Challenge challenge = new Challenge();
        challenge.setLevel(10);
        assertEquals(20,challenge.getLevel());
    }

    @Test
    public void getLevelTestPUE() {
        Challenge challenge = new Challenge();
        challenge.setLevel(1);
        assertNotNull(challenge.getLevel());
    }

    @Test(expected = AssertionError.class)
    public void getLevelTestPUFE() {
        Challenge challenge = new Challenge();
        assertNull(challenge.getLevel());
    }

    @Test
    public void ChallengeConstructTestPUE() {
        Challenge challenge = new Challenge (1,"Reto de Prueba","Descripcion reto de prueba",10);
        assertNotNull(challenge);
    }

    @Test(expected = AssertionError.class)
    public void ChallengeConstructTestPUFE() {
        Challenge challenge = null;
        assertNotNull(challenge);
    }

    @Test
    public void ChallengeConstructorTestPUE() {
        Challenge challenge = new Challenge (5,10);
        assertNotNull(challenge);
    }

    @Test(expected = AssertionError.class)
    public void ChallengeConstructorTestPUFE() {
        Challenge challenge = null;
        assertNotNull(challenge);
    }

}
