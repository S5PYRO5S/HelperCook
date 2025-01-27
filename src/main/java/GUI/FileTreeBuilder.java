package GUI;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Helper class for TreePanel for creating the tree panel from the file system via {@link javax.swing.JFileChooser}
 * or a list of files from the terminal.
 * It can also filter to display only files with .cook file extension
 */
class FileTreeBuilder
{
    private File rootDirectory;     //the root directory of the tree

    /**
     * Creates the file tree from a root
     * @param rootFile the root
     * @return  the TreeModel that represents the file structure
     */
    public TreeModel createFileTreeModel(File rootFile)
    {
        this.rootDirectory = rootFile;
        DefaultMutableTreeNode rootNode = new FileNode(rootFile);
        addFileNodes(rootNode, rootFile);
        return new DefaultTreeModel(rootNode);
    }

    /**
     * method to add file nodes to the parent node
     *
     * @param parentNode the parent node
     * @param file       the current file or directory
     */
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

    /**
     * method to return a virtual tree based on a give list of files or default if the list is empty
     * @param files the list of files
     * @return      the tree
     */
    public TreeModel createVirtualFileTreeModel(List<File> files)
    {
        if(files == null || files.isEmpty())
        {
            return new DefaultTreeModel(new DefaultMutableTreeNode("No currently selected files"));
        }
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Recipes");
        Map<String, DefaultMutableTreeNode> folderNodes = new HashMap<>();

        for (File file : files)
        {
            if (file.isFile() && file.getName().endsWith(".cook"))
            {
                String folderName = file.getParentFile().getName();
                DefaultMutableTreeNode folderNode = folderNodes.computeIfAbsent(folderName, k -> new DefaultMutableTreeNode(k));
                folderNode.add(new FileNode(file));
            }
        }

        folderNodes.values().forEach(rootNode::add);
        return new DefaultTreeModel(rootNode);
    }

    /**
     * A tree node that wraps a File object
     */
    static class FileNode extends DefaultMutableTreeNode
    {
        private final File file;

        public FileNode(File file)
        {
            super(file.getName());
            this.file = file;
        }

        public File getFile()
        {
            return file;
        }
    }
}
