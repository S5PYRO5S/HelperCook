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
        DefaultMutableTreeNode rootNode = new FileNode(rootFile);
        addFileNodes(rootNode, rootFile);
        return new DefaultTreeModel(rootNode);
    }

    private void addFileNodes(DefaultMutableTreeNode parentNode, File file)
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
                        DefaultMutableTreeNode childNode = new FileNode(child);
                        addFileNodes(childNode, child);

                        if (childNode.getChildCount() > 0)
                        {
                            parentNode.add(childNode);
                            hasCookFiles = true;
                        }
                    }
                    else if (child.isFile() && child.getName().endsWith(".cook"))
                    {
                        DefaultMutableTreeNode childNode = new FileNode(child);
                        parentNode.add(childNode);
                        hasCookFiles = true;
                    }
                }
                if (!hasCookFiles && parentNode.getParent() != null) parentNode.removeFromParent();
            }
        }
        else if (file.isFile() && file.getName().endsWith(".cook"))
        {
            DefaultMutableTreeNode childNode = new FileNode(file);
            parentNode.add(childNode);
        }
    }

    public File getRootDirectory() {return rootDirectory;}

    //inner class that represents a file
    static class FileNode extends DefaultMutableTreeNode
    {
        private final File file;

        public FileNode(File file)
        {
            super(file);
            this.file = file;
        }

        @Override
        public String toString() {return file.getName();}
        public File getFile() {return file;}
    }
}
