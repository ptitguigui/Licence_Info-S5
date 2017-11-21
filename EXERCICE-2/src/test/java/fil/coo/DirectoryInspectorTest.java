package fil.coo;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private Path tempRootDir;


    /**
     * Creates a temp dir in the default system temporary directory and temporary files in our temp dir.
     */
    @Before
    public void setupTestDir() {

        try {
            tempRootDir = Files.createTempDirectory("temp");
            createFiles(tempRootDir);
        } catch (IOException e) {
            logger.debug(e);
            return;
        }

        directoryInspector = new DirectoryInspector(tempRootDir.toString());
    }

    /**
     * Creates the temporary files
     *
     * @param tempPath the path to our custom temporary directory
     * @throws IOException if an error occurs while creating the temp files
     */
    private void createFiles(Path tempPath) throws IOException {
        Files.createFile(Paths.get(startsWithCName));
        Files.createFile(Paths.get(endsWithClassName));
    }

    @After
    public void deleteTempFiles() {
        try {
            Files.delete(tempRootDir);
        } catch (IOException e) {
            logger.debug(e);
        }
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