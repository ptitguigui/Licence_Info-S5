package fil.coo;

import fil.coo.exception.TooManyResourcesException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ResourcePool<T extends Resource> {

    protected int nbMaxResources;

    protected List<T> resourceList;

    /**
     * @param nbMaxResources the initial and max amount of resources that this pool will hold.
     */
    public ResourcePool(int nbMaxResources) {
        if (nbMaxResources <= 0) {
            throw new IllegalArgumentException("Cannot create pool of 0 resources");
        }
        this.nbMaxResources = nbMaxResources;
        initResources();
    }

    /**
     * Creates the resources that this pool will hold.
     */
    private void initResources() {
        resourceList = new ArrayList<T>();
        for (int i = 0; i < nbMaxResources; i++) {
            T resource = createOneResource();
        }
    }

    /**
     * @return one instance of a <b>T</b> resource
     */
    protected abstract T createOneResource();

    /**
     * @return a resource from the pool
     * @throws NoSuchElementException if no resources are available
     */
    abstract T provideResource() throws NoSuchElementException;

    /**
     * The pool recovers the resource provided.
     *
     * @param resource the resource that will be recovered
     * @throws IllegalArgumentException  if the resource parameter is incorrect
     * @throws TooManyResourcesException  if the pool already contains {@link #nbMaxResources} resources
     */
    abstract void recoverResource(T resource) throws IllegalArgumentException, TooManyResourcesException;
}
