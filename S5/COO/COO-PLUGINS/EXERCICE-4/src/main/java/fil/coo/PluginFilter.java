package fil.coo;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;

/**
 * An implementation of {@link FilenameFilter} that only accepts compiled java .class files
 */
public class PluginFilter implements FilenameFilter {

    private Logger logger = Logger.getLogger(PluginFilter.class.getSimpleName());

    private static final String PLUGIN_PACKAGE = "plugin.";
    private static final String EXTENSION_CLASS = ".class";


    /**
     * @param dir      the directory which contains filename
     * @param filename the name of the file to be considered
     * @return if the file points to a plugin
     */
    @Override
    public boolean accept(File dir, String filename) {
        logger.debug("Testing if accept \"" + filename + "\" in dir \"" + dir.getAbsolutePath() + "\"");

        if (!fileCanBeClass(filename)) {
            logger.debug("\"" + filename + "\" is not qualified to be a class");
            return false;
        }

        boolean result = isClassInstanceAPlugin(filename);
        logger.debug("Accept \"" + filename + "\" is: " + result);

        return result;
    }

    /**
     * @param filename the name of the file to be treated
     * @return if filename ends with {@link #EXTENSION_CLASS}, is long enough for an actual class name and starts
     * with an uppercase
     */
    private boolean fileCanBeClass(String filename) {
        return filename.endsWith(EXTENSION_CLASS) && filename.length() > EXTENSION_CLASS.length() && Character
                .isUpperCase(filename.charAt(0));
    }

    /**
     * @param filename the name of the file to be treated
     * @return if a {@link Class} can be instantiated from the {@link #PLUGIN_PACKAGE} and has an empty constructor
     */
    private boolean isClassInstanceAPlugin(String filename) {
        Class<?> classFromFile = getClassFromFile(filename);
        return classFromFile != null && canGetConstructor(classFromFile);
    }

    /**
     * @param filename the name of the file to be treated
     * @return the {@link Class} associated to the filename in the {@link #PLUGIN_PACKAGE}
     */
    private Class<?> getClassFromFile(String filename) {

        Class<?> instantiatedClass;
        try {
            String fullClassName = PLUGIN_PACKAGE + getClassNameFromFile(filename);
            logger.debug("Using full class name: \"" + fullClassName + "\"");

            instantiatedClass = Class.forName(fullClassName);
        } catch (ClassNotFoundException | ClassFormatError e) {
            logger.debug(e);
            return null;
        }
        return instantiatedClass;
    }

    /**
     * @param classz the class to consider
     * @return if classz has an empty constructor
     */
    private boolean canGetConstructor(Class<?> classz) {
        Constructor<?> constructor;
        try {
            constructor = classz.getConstructor();
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

    /**
     * @param filename the name of the file to be treated
     * @return the java classname from filename
     */
    private String getClassNameFromFile(String filename) {
        if (!fileCanBeClass(filename)) {
            return null;
        }
        return filename.substring(0, filename.length() - EXTENSION_CLASS.length());
    }

}
