package fil.coo;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
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
    private static final String TEST_DIR_NAME = "test-folder";

    private static final String startsWithCName = C + "." + TEST;
    private static final String endsWithClassName = TEST + CLASS;


    private DirectoryInspector directoryInspector;

    /**
     * The path of the newly created testing folder
     */
    private Path tempRootDirPath;

    /**
     * The {@link File} of the testing directory. Instanciated in {@link #setupTestDir()} Used to delete all contents at end of test
     */
    private File tempRootDirFile;


    /**
     * Creates a temp dir in the default system temporary directory and temporary files in our temp dir.
     */
    @Before
    public void setupTestDir() {

        verifyExistingFolder();

        try {
            createTestingFolder();
            createTempFiles(tempRootDirPath);
        } catch (IOException e) {
            logger.debug(e);
            return;
        }

        directoryInspector = new DirectoryInspector(tempRootDirPath.toString());
    }

    private void verifyExistingFolder() {
        File testDir = new File(TEST_DIR_NAME);
        if (testDir.exists()) {
            logger.debug("test folder exists, will delete");
            deleteAll(testDir);
        }
    }

    /**
     * Creates the testing folder
     *
     * @throws IOException if an error occurs while creating this folder
     */
    private void createTestingFolder() throws IOException {
        tempRootDirPath = Files.createDirectory(Paths.get(TEST_DIR_NAME));
        tempRootDirFile = new File(tempRootDirPath.toString());
        logger.debug("created temp dir at \"" + tempRootDirPath.toAbsolutePath().toString() + "\"");
    }

    /**
     * Creates the temporary files
     *
     * @param tempPath the path to our custom temporary directory
     * @throws IOException if an error occurs while creating the temp files
     */
    private void createTempFiles(Path tempPath) throws IOException {
        String prefix = tempPath.toString() + "/";

        Path startsWithCPath = Files.createFile(Paths.get(prefix + startsWithCName));
        logger.debug("created test file StartsWithC at \"" + startsWithCPath.toAbsolutePath().toString() + "\"");

        Path endsWithClassPath = Files.createFile(Paths.get(prefix + endsWithClassName));
        logger.debug("created test file endWithClass at \"" + endsWithClassPath.toAbsolutePath().toString() + "\"");
    }

    @After
    public void deleteTempFilesAndFolder() {
        deleteAll(tempRootDirFile);
    }

    private void deleteAll(File rootDir) {
        String[] entries = rootDir.list();
        for (String s : entries) {
            File currentFile = new File(rootDir.getPath(), s);
            boolean delete = currentFile.delete();
            logger.debug("deleted file \"" + currentFile.toString() + "\" : " + delete);
        }
        boolean delete = rootDir.delete();
        logger.debug("deleted dir \"" + rootDir.toString() + "\" : " + delete);
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