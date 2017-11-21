package fil.coo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryInspector {

    private FilenameFilter startsWithCFilter;
    private FilenameFilter classFilter;
    private File directory;

    public DirectoryInspector(String filePath) {
        initCFilter();
        initClassFilter();
        directory = new File(filePath);
    }

    private void initCFilter() {
        startsWithCFilter = (file, s) -> s.startsWith("C") || s.startsWith("c");
    }

    private void initClassFilter() {
        classFilter = (file, s) -> s.endsWith(".class");
    }

    public List<String> getFilesThatStartWithC() {
        return Arrays.asList(directory.list(startsWithCFilter));
    }

    public List<String> getClassFiles() {
        return Arrays.asList(directory.list(classFilter));

    }
}
