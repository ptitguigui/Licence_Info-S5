package fil.coo;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class ResourcePool<T extends Resource> {

    List<T> resourceList;

    abstract T provideResource() throws NoSuchElementException;

    abstract void recoverResource(T resource) throws IllegalArgumentException;
}
