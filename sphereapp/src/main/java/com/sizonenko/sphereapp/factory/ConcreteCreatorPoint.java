package com.sizonenko.sphereapp.factory;

import com.sizonenko.sphereapp.entity.Figure;
import com.sizonenko.sphereapp.entity.Point;
import com.sizonenko.sphereapp.entity.Sphere;

public class ConcreteCreatorPoint implements Creator {

    @Override
    public Figure factoryMethod(double r) {
        return new Point();
    }

    @Override
    public Figure factoryMethod(double x, double y, double z) {
        return new Point(x, y, z);
    }
}
