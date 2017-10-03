package fil.coo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ResourcePool<T extends Resource> {

    protected int nbResources;

    protected List<T> resourceList;

    public ResourcePool(int nbResources) {
        this.nbResources = nbResources;
        initResources();
    }

    private void initResources() {
        resourceList = new ArrayList<T>();
        for (int i=0;i<nbResources;i++) {
            T resource = createOneResource();
        }
    }

    protected abstract T createOneResource();

    abstract T provideResource() throws NoSuchElementException;

    abstract void recoverResource(T resource) throws IllegalArgumentException;
}
