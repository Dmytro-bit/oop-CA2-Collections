package Question1_extra;

public class Cylinder implements IMeasurableContainer {
    private double height;
    private double diameter;
    private double weight;

    public Cylinder(double height, double diameter, double weight) {
        this.height = height;
        this.diameter = diameter;
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public double rectangularVolume() {
        double radius = diameter / 2;
        return diameter * diameter * height;
    }
}

