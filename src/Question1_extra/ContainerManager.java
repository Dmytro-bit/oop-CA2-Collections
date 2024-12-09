package Question1_extra;

import java.util.ArrayList;

public class ContainerManager {
    private ArrayList<IMeasurableContainer> containers;

    public ContainerManager() {
        containers = new ArrayList<>();
    }

    public void add(IMeasurableContainer container) {
        containers.add(container);
    }

    public double totalWeight() {
        double totalWeight = 0;
        for (IMeasurableContainer container : containers) {
            totalWeight += container.weight();
        }

        return totalWeight;
    }

    public double totalRectangularVolume() {
        double totalVolume = 0;
        for (IMeasurableContainer container : containers) {
            totalVolume += container.rectangularVolume();
        }
        return totalVolume;
    }

    public void clearAll() {
        containers.clear();
    }

    public ArrayList<IMeasurableContainer> getAllContainers() {
        return new ArrayList<>(containers);
    }
}