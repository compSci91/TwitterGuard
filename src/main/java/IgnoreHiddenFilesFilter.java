import java.io.FileFilter;
import java.io.File;

public class IgnoreHiddenFilesFilter implements FileFilter {
    public boolean accept(File pathName){
        return !pathName.isHidden();
    }
}
