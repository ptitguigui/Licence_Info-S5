package fil.coo;

import java.util.NoSuchElementException;

public interface ResourcePool {

    Resource provideResource() throws NoSuchElementException;

    void recoverResource(Resource resource) throws IllegalArgumentException;
}
