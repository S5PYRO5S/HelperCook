package GUI;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.io.File;

/**
 * Util class for TreePanel (creates the tree panel)
 */
public class FileTreeBuilder
{
    private File rootDirectory;

    public TreeModel createFileTreeModel(File rootFile)
    {
        this.rootDirectory = rootFile;
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootFile.getName());
        addFileNodes(rootNode, rootFile);
        return new DefaultTreeModel(rootNode);
    }

    public void addFileNodes(DefaultMutableTreeNode parentNode, File file)
    {
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            if (files != null)
            {
                boolean hasCookFiles = false;

                for (File child : files)
                {
                    if (child.isDirectory())
                    {
                        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
                        addFileNodes(childNode, child);

                        //add the directory only if it contains .cook files or valid subdirectories
                        if (childNode.getChildCount() > 0)
                        {
                            parentNode.add(childNode);
                            hasCookFiles = true;
                        }
                    }
                    else if (child.isFile() && child.getName().endsWith(".cook"))
                    {
                        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
                        parentNode.add(childNode);
                        hasCookFiles = true;
                    }
                }

                //remove the parent directory if it doesn't contain any .cook files
                if (!hasCookFiles && parentNode.getParent() != null)
                {
                    parentNode.removeFromParent();
                }
            }
        }
        else if (file.isFile() && file.getName().endsWith(".cook"))
        {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file.getName());
            parentNode.add(childNode);
        }
    }



    public File getRootDirectory()
    {
        return rootDirectory;
    }

}
