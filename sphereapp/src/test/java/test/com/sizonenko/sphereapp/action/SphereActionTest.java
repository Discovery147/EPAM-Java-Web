package test.com.sizonenko.sphereapp.action;

import com.sizonenko.sphereapp.action.SphereAction;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class SphereActionTest {

    private static SphereAction action;

    @BeforeMethod
    @Test
    public void CreateSphereAction() {
        action = new SphereAction("files/parameters.txt");
        Assert.assertNotEquals(action, null);
    }
    
    @AfterClass
    public void ClearSphereAction() {
        action = null;
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void WrongCreateSphereAction() {
        action = new SphereAction("mparameters.txt");
    }

    @Test
    public void trueSizeMapCreateSphereAction() {
        double expected = 3;
        double actual = action.getMap().size();
        assertEquals("Size of map is wrong: ", expected, actual, 0.01);
    }

    @Test
    public void falseSizeMapCreateSphereAction() {
        double expected = 4;
        double actual = action.getMap().size();
        Assert.assertNotEquals(expected, actual, 0.01);
    }

    @Test
    public void trueCalculateAreasSphereAction() {
        List<Double> expected = Arrays.asList(12.566370);
        List<Double> various = action.calculateArea();
        assertEquals("Wrong sphere areas: ", expected.get(0), various.get(0), 0.01);
    }

    @Test
    public void falseCalculateAreasSphereAction() {
        List<Double> expected = Arrays.asList(12.586370);
        List<Double> various = action.calculateArea();
        Assert.assertNotEquals(expected.get(0), various.get(0), 0.01, "Wrong sphere areas: ");
    }

    @Test
    public void trueCalculateVSphereAction() {
        List<Double> expected = Arrays.asList(4.188790);
        List<Double> various = action.calculateV();
        assertEquals("Wrong sphere V: ", expected.get(0), various.get(0), 0.01);
    }

    @Test
    public void falseCalculateVSphereAction() {
        List<Double> expected = Arrays.asList(4.138790);
        List<Double> various = action.calculateV();
        Assert.assertNotEquals(expected.get(0), various.get(0), 0.01, "Wrong sphere V: ");
    }

    @Test // Проверка первого элемента MAP - Соотношение объемов рассеченной к общей части шара
    public void trueCalculateRelationsSphereAction() {
        double point = 0.5;
        List<Double> expected = Arrays.asList(0.654498 / 4.188790);
        List<Double> various = action.calculateRelations(point);
        assertEquals("Wrong sphere relations: ", expected.get(0), various.get(0), 0.01);
    }

    @Test
    public void falseCalculateRelationsSphereAction() {
        double point = 0.5;
        List<Double> expected = Arrays.asList(0.664498 / 4.188790);
        List<Double> various = action.calculateRelations(point);
        Assert.assertNotEquals(expected.get(0), various.get(0), 0.001, "Wrong sphere relations: ");
    }

    @Test
    public void trueCalculateTouchSphereAction() { // Касается ли первый шар оси координат
        List<Boolean> expected = Arrays.asList(false);
        List<Double> various = action.calculateTouch();
        assertEquals("Wrong boolean touch: ", expected.get(0), various.get(0));
    }

    @Test
    public void falseCalculateTouchSphereAction() {
        List<Boolean> expected = Arrays.asList(true);
        List<Double> various = action.calculateTouch();
        Assert.assertNotEquals(expected.get(0), various.get(0), "Wrong boolean touch: ");
    }
}
