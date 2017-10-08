package com.sizonenko.sphereapp.factory;

import com.sizonenko.sphereapp.entity.Figure;
import com.sizonenko.sphereapp.entity.Sphere;

public class ConcreteCreatorSphere implements Creator {

    @Override
    public Figure factoryMethod(double r) {
        return new Sphere (r);
    }

    @Override
    public Figure factoryMethod(double x, double y, double z) {
        return new Sphere();
    }    
}
