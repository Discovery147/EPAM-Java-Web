package com.sizonenko.sphereapp.factory;

import com.sizonenko.sphereapp.entity.Figure;

public interface Creator {

    public Figure factoryMethod(double r);
    public Figure factoryMethod(double x, double y, double z);
}
