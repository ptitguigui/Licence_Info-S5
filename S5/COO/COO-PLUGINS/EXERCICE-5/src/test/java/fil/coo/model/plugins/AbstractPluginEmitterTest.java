package fil.coo.model.plugins;

import fil.coo.Mocks.MockPlugin;
import fil.coo.Mocks.MockPluginListener;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import plugin.Plugin;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public abstract class AbstractPluginEmitterTest {

    private static final Logger logger = Logger.getLogger(AbstractPluginEmitterTest.class.getSimpleName());

    protected AbstractPluginEmitter pluginEmitter;
    private MockPluginListener mockPluginListener;

    @Before
    public void setup() throws IOException {
        pluginEmitter = getPluginEmitter("repository");
        mockPluginListener = new MockPluginListener();

        pluginEmitter.addPluginListener(mockPluginListener);
    }

    protected abstract AbstractPluginEmitter getPluginEmitter(String dirToWatch) throws IOException;

    @Test
    public void testAddListener() {
        assertThat(pluginEmitter.pluginListeners.size(), is(1));

        MockPluginListener anotherListener = new MockPluginListener();
        pluginEmitter.addPluginListener(anotherListener);
        assertThat(pluginEmitter.pluginListeners.size(), is(2));

        pluginEmitter.removePluginListener(anotherListener);
        assertThat(pluginEmitter.pluginListeners.size(), is(1));
    }

    @Test
    public void testFirePluginAddedCorrectlyNotifies() {
        assertThat(mockPluginListener.onPluginAddedCounter, is(0));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(0));
        assertThat(mockPluginListener.lastEvent, is(nullValue()));

        pluginEmitter.firePluginAdded(MockPlugin.class);
        assertThat(mockPluginListener.onPluginAddedCounter, is(1));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(0));

        Class<? extends Plugin> pluginClass = mockPluginListener.lastEvent.getPluginClass();
        assertThat(pluginClass.getCanonicalName(), is(MockPlugin.class.getCanonicalName()));
    }

    @Test
    public void testFirePluginAddedDoesNotNotifyAfterRemoval() {
        assertThat(mockPluginListener.onPluginAddedCounter, is(0));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(0));
        assertThat(mockPluginListener.lastEvent, is(nullValue()));

        pluginEmitter.firePluginAdded(MockPlugin.class);
        assertThat(mockPluginListener.onPluginAddedCounter, is(1));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(0));

        Class<? extends Plugin> pluginClass = mockPluginListener.lastEvent.getPluginClass();
        assertThat(pluginClass.getCanonicalName(), is(MockPlugin.class.getCanonicalName()));

        pluginEmitter.removePluginListener(mockPluginListener);
        pluginEmitter.firePluginAdded(MockPlugin.class);
        assertThat(mockPluginListener.onPluginAddedCounter, is(1));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(0));
    }

    @Test
    public void testFirePluginRemovedCorrectlyNotifies() {
        assertThat(mockPluginListener.onPluginAddedCounter, is(0));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(0));
        assertThat(mockPluginListener.lastEvent, is(nullValue()));

        pluginEmitter.firePluginRemoved(MockPlugin.class);
        assertThat(mockPluginListener.onPluginAddedCounter, is(0));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(1));

        Class<? extends Plugin> pluginClass = mockPluginListener.lastEvent.getPluginClass();
        assertThat(pluginClass.getCanonicalName(), is(MockPlugin.class.getCanonicalName()));
    }

    @Test
    public void testFirePluginRemovedDoesNotNotifyAfterRemoval() {
        assertThat(mockPluginListener.onPluginAddedCounter, is(0));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(0));
        assertThat(mockPluginListener.lastEvent, is(nullValue()));

        pluginEmitter.firePluginRemoved(MockPlugin.class);
        assertThat(mockPluginListener.onPluginAddedCounter, is(0));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(1));

        Class<? extends Plugin> pluginClass = mockPluginListener.lastEvent.getPluginClass();
        assertThat(pluginClass.getCanonicalName(), is(MockPlugin.class.getCanonicalName()));

        pluginEmitter.removePluginListener(mockPluginListener);
        pluginEmitter.firePluginAdded(MockPlugin.class);
        assertThat(mockPluginListener.onPluginAddedCounter, is(0));
        assertThat(mockPluginListener.onPluginRemovedCounter, is(1));
    }

}