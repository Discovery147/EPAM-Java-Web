package com.sizonenko.sphereapp.validation;

import com.sizonenko.sphereapp.file.ReadFile;
import com.sizonenko.sphereapp.entity.Figure;
import com.sizonenko.sphereapp.entity.Sphere;
import com.sizonenko.sphereapp.exception.EmptyMapException;
import com.sizonenko.sphereapp.exception.NegativeRadiusException;
import com.sizonenko.sphereapp.factory.ConcreteCreatorPoint;
import com.sizonenko.sphereapp.factory.ConcreteCreatorSphere;
import java.util.Arrays;
import java.util.Map;
import org.apache.log4j.Logger;

public class ValidateImportSphere {

    public static final Logger LOGGER = Logger.getLogger(ValidateImportSphere.class.getName());

    // Сферу опредеяют 4 аргумента: R>0, +-x, +-y, +-z
    public static boolean isSphere(String line) {
        String[] arr = line.split(" ");
        LOGGER.debug("length of input array: " + arr.length);
        if (arr.length != 4) {
            LOGGER.error("object is not sphere: " + line);
            return false;
        }
        try {
            double[] mas = Arrays.asList(arr).stream().mapToDouble(Double::parseDouble).toArray();

            if (mas[0] <= 0) {
                throw new NegativeRadiusException();
            }
            LOGGER.info("success line: " + line);
            return true;
        } catch (NumberFormatException e) {
            LOGGER.error("symbols are not numbers");
        } catch (NegativeRadiusException ex) {
            LOGGER.error("negative radius exception");
        }
        return false;
    }

    public static Figure getValidateSphere(String line) {
        double[] mas = Arrays.asList(line.split(" ")).stream().mapToDouble(Double::parseDouble).toArray();
        return new ConcreteCreatorSphere().factoryMethod(mas[0]);
    }

    public static Figure getValidatePoint(String line) {
        double[] mas = Arrays.asList(line.split(" ")).stream().mapToDouble(Double::parseDouble).toArray();
        return new ConcreteCreatorPoint().factoryMethod(mas[1], mas[2], mas[3]);
    }

    public static boolean checkMap(Map map) {
        try {
            if (!map.isEmpty()) {
                return true;
            } else {
                throw new EmptyMapException();
            }
        } catch (EmptyMapException ex) {
            ReadFile.LOGGER.error("map is empty");
        }
        return false;
    }
}
