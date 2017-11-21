package fil.coo;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DirectoryInspectorTest {

    private static final Logger logger = Logger.getLogger(DirectoryInspector.class.getSimpleName());

    private static final String TEST = "test";
    private static final String C = "C";
    private static final String CLASS = ".class";

    private static final String startsWithCName = C + "." + TEST;
    private static final String endsWithClassName = TEST + CLASS;


    private DirectoryInspector directoryInspector;


    /**
     * Creates a temp dir in the default system temporary directory and temporary files in our temp dir.
     */
    @Before
    public void setupTestDir() {

        Path tempPath;
        try {
            tempPath = Files.createTempDirectory("temp");
            createFiles(tempPath);
        } catch (IOException e) {
            logger.debug(e);
            return;
        }

        directoryInspector = new DirectoryInspector(tempPath.toString());
    }

    /**
     * Creates the temporary files
     *
     * @param tempPath the path to our custom temporary directory
     * @throws IOException if an error occurs while creating the temp files
     */
    private void createFiles(Path tempPath) throws IOException {
        Files.createTempFile(tempPath, "C", "test", null);
        Files.createTempFile(tempPath, "test", ".class", null);
    }

    @Test
    public void getFilesThatStartWithC() throws Exception {
        List<String> files = directoryInspector.getFilesThatStartWithC();

        assertThat(files.size(), is(1));
        assertThat(files.get(0), is(startsWithCName));
    }

    @Test
    public void getClassFiles() throws Exception {
        List<String> files = directoryInspector.getClassFiles();

        assertThat(files.size(), is(1));
        assertThat(files.get(0), is(endsWithClassName));
    }

}