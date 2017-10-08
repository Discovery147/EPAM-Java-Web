package com.sizonenko.sphereapp.action;

import com.sizonenko.sphereapp.file.ReadFile;
import com.sizonenko.sphereapp.entity.Point;
import com.sizonenko.sphereapp.entity.Sphere;
import com.sizonenko.sphereapp.validation.ValidateImportSphere;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class SphereAction {

    public static final Logger LOGGER = Logger.getLogger(SphereAction.class.getName());
    private final Map<Point, Sphere> map; // Список элементов, каждый из которых является (Point - Sphere)
    private final ReadFile reader;

    public SphereAction(String path) {
        reader = new ReadFile();
        this.map = reader.getDataSphere(path);
    }

    public Map<Point, Sphere> getMap() {
        return map;
    }

    public ReadFile getReader() {
        return reader;
    }

    public List getArea() { // Площадь поверхности шара
        List<Double> areas = new ArrayList();
        if (ValidateImportSphere.checkMap(map)) {
            map.forEach((key, value) -> areas.add(4 * Math.PI * Math.pow(value.getRadius(), 2)));
            areas.forEach(obj -> ReadFile.LOGGER.debug(obj));
        }
        return areas;
    }

    public List getV() { // Объем шара
        List<Double> various = new ArrayList();
        if (ValidateImportSphere.checkMap(map)) {
            map.forEach((key, value) -> various.add((4 * Math.PI * Math.pow(value.getRadius(), 3)) / 3));
            various.forEach(obj -> ReadFile.LOGGER.debug(obj));
        }
        return various;
    }

    public List getRelations(double point) { // Соотношение объемов, полученных рассечением шара коорд. плоскостью (параметр - удаленность перпендикуляра к рассекающей плоскости от центра шара)
        List<Double> relations = new ArrayList();
        if (ValidateImportSphere.checkMap(map)) {
            for (Map.Entry<Point, Sphere> entry : map.entrySet()) {
                double r = entry.getValue().getRadius();
                if (point < r || point >= 0) {
                    LOGGER.debug("Point is true: " + point);
                    double v = Math.PI * (Math.pow(r - point, 2)) * (r - ((r - point) / 3)); // объем отсеченной части шара
                    double fullV = (4 * Math.PI * Math.pow(r, 3)) / 3; // объем шара
                    relations.add(v / fullV); // соотношение меньшей к большей
                } else {
                    LOGGER.error("not intersect: " + point);
                    relations.add(1.0);
                }
            }
        }
        return relations;
    }

    public List getTouch() { // Касается ли шар оси координат
        List<Boolean> touch = new ArrayList();
        for (Map.Entry<Point, Sphere> entry : map.entrySet()) {
            double r = entry.getValue().getRadius();
            Point point = entry.getKey();
            touch.add((point.getX() - r == 0 || point.getX() + r == 0) ? true
                    : (point.getY() - r == 0 || point.getY() + r == 0) ? true
                            : (point.getZ() - r == 0 || point.getZ() + r == 0) ? true : false);
        }
        return touch;
    }
}
