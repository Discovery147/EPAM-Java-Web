package com.sizonenko.sphereapp.file;

import com.sizonenko.sphereapp.entity.Point;
import com.sizonenko.sphereapp.entity.Sphere;
import com.sizonenko.sphereapp.exception.FileException;
import com.sizonenko.sphereapp.validation.ValidateImportSphere;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;


public class ReadFile {

    private static final Logger LOGGER = Logger.getLogger(ReadFile.class);
    private Map map;

    public Map getDataSphere(String path) {
        map = new LinkedHashMap<Point, Sphere>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resourceURL = classLoader.getResource(path);
            if(resourceURL == null)
                throw new FileException();
            File file = new File(classLoader.getResource(path).getFile());
            if (file.exists() && file.isFile() && file.length() != 0) {
                FileReader fr = new FileReader(file);
                try (BufferedReader br = new BufferedReader(fr)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (ValidateImportSphere.isSphere(line)) {
                            map.put(new ValidateImportSphere().getValidatePoint(line), new ValidateImportSphere().getValidateSphere(line));
                        }
                    }
                } catch (IOException ex) {
                    LOGGER.error("error reading the file: " + path);
                }
            } else {
                throw new FileException();
            }
        } catch (FileException | FileNotFoundException ex) {
            LOGGER.fatal("file not found or clear: " + path);
            throw new RuntimeException("file not found or clear: " + path);
        }
        return map;
    }
}
