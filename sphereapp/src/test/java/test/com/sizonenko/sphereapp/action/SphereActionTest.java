package test.com.sizonenko.sphereapp.action;

import com.sizonenko.sphereapp.action.SphereAction;
import static org.testng.AssertJUnit.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class SphereActionTest {

    private static SphereAction action;

    @AfterClass
    public void ClearSphereAction() {
        action = null;
    }

    @Test
    public void CreateSphereAction() {
        action = new SphereAction("files/parameters.txt");
        assertNotEquals(action, null);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void WrongCreateSphereAction() {
        action = new SphereAction("mparameters.txt");
    }

    @Test
    public void trueSizeMapCreateSphereAction() {
        action = new SphereAction("files/parameters.txt");
        double expected = 3;
        double actual = action.getMap().size();
        assertEquals("Size of map is wrong: ", expected, actual, 0.01);
    }

    @Test
    public void falseSizeMapCreateSphereAction() {
        action = new SphereAction("files/parameters.txt");
        double expected = 4;
        double actual = action.getMap().size();
        assertNotEquals("Size of map is wrong: ", expected, actual, 0.01);
    }

    @Test
    public void trueAreasSphereAction() {
        action = new SphereAction("files/parameters.txt");
        List<Double> expected = Arrays.asList(12.566370);
        List<Double> various = action.getArea();
        assertEquals("Wrong sphere areas: ", expected.get(0), various.get(0), 0.01);
    }

    @Test
    public void falseAreasSphereAction() {
        action = new SphereAction("files/parameters.txt");
        List<Double> expected = Arrays.asList(12.586370);
        List<Double> various = action.getArea();
        assertNotEquals("Wrong sphere areas: ", expected.get(0), various.get(0), 0.01);
    }

    @Test
    public void trueVSphereAction() {
        action = new SphereAction("files/parameters.txt");
        List<Double> expected = Arrays.asList(4.188790);
        List<Double> various = action.getV();
        assertEquals("Wrong sphere V: ", expected.get(0), various.get(0), 0.01);
    }

    @Test
    public void falseVSphereAction() {
        action = new SphereAction("files/parameters.txt");
        List<Double> expected = Arrays.asList(4.138790);
        List<Double> various = action.getV();
        assertNotEquals("Wrong sphere V: ", expected.get(0), various.get(0), 0.01);
    }

    @Test // Проверка первого элемента MAP - Соотношение объемов рассеченной к общей части шара
    public void trueRelationsSphereAction() {
        action = new SphereAction("files/parameters.txt");
        double point = 0.5;
        List<Double> expected = Arrays.asList(0.654498 / 4.188790);
        List<Double> various = action.getRelations(point);
        assertEquals("Wrong sphere relations: ", expected.get(0), various.get(0), 0.01);
    }

    @Test
    public void falseRelationsSphereAction() {
        action = new SphereAction("files/parameters.txt");
        double point = 0.5;
        List<Double> expected = Arrays.asList(0.664498 / 4.188790);
        List<Double> various = action.getRelations(point);
        assertNotEquals("Wrong sphere relations: ", expected.get(0), various.get(0), 0.001);
    }

    @Test
    public void trueTouchSphereAction() { // Касается ли первый шар оси координат
        action = new SphereAction("files/parameters.txt");
        List<Boolean> expected = Arrays.asList(false);
        List<Double> various = action.getTouch();
        assertEquals("Wrong boolean touch: ", expected.get(0), various.get(0));
    }

    @Test
    public void falseTouchSphereAction() {
        action = new SphereAction("files/parameters.txt");
        List<Boolean> expected = Arrays.asList(true);
        List<Double> various = action.getTouch();
        assertNotEquals("Wrong boolean touch: ", expected.get(0), various.get(0));
    }
}
