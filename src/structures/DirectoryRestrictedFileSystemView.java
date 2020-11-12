package structures;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

// add comments. 
/**
 * Created by Sudarshan on 3/21/2017.
 */
public class DirectoryRestrictedFileSystemView extends FileSystemView
{
    private final File[] rootDirectories;

    public DirectoryRestrictedFileSystemView(File rootDirectory)
    {
        this.rootDirectories = new File[] {rootDirectory};
    }

    public DirectoryRestrictedFileSystemView(File[] rootDirectories)
    {
        this.rootDirectories = rootDirectories;
    }

    @Override
    public File createNewFolder(File containingDir) throws IOException
    {
        throw new UnsupportedOperationException("Unable to create directory");
    }

    @Override
    public File[] getRoots()
    {
        return rootDirectories;
    }

    @Override
    public boolean isRoot(File file)
    {
        for (File root : rootDirectories) {
            if (root.equals(file)) {
                return true;
            }
        }
        return false;
    }
}
