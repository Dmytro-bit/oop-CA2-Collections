import Question1_extra.*;

import java.util.ArrayList;

public class Question1 {    // Interfaces
    public static void main(String[] args) {
        System.out.println("Question 1");

        ContainerManager manager = new ContainerManager();

        manager.add(new Box(2, 3, 4, 5));
        manager.add(new Box(4, 5, 6, 6));
        manager.add(new Cylinder(3, 5, 6));
        manager.add(new Cylinder(9, 8, 7));
        manager.add(new Pyramid(4, 4, 3));
        manager.add(new Pyramid(3, 8, 6));

        System.out.println("Total Weight: " + manager.totalWeight());
        System.out.println("Total Rectangular Volume: " + manager.totalRectangularVolume());

        ArrayList<IMeasurableContainer> containers = manager.getAllContainers();

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Total Containers: " + containers.size());

        for (IMeasurableContainer container : containers) {
            if (container instanceof Box) {

                Box box = (Box) container;
                System.out.println("Box: Length=" + box.getLength() + ", Width=" + box.getWidth() + ", Depth=" + box.getDepth() + ", Weight=" + box.getWeight());
            } else if (container instanceof Cylinder) {

                Cylinder cylinder = (Cylinder) container;
                System.out.println("Cylinder: Height=" + cylinder.getHeight() + ", Diameter=" + cylinder.getDiameter() + ", Weight=" + cylinder.getWeight());
            } else if (container instanceof Pyramid) {

                Pyramid pyramid = (Pyramid) container;
                System.out.println("Pyramid: Length=" + pyramid.getLength() + ", Side Length=" + pyramid.getSideLength() + ", Weight=" + pyramid.getWeight());
            }
        }

    }
}