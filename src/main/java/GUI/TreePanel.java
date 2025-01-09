package GUI;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.File;

/**
 * class to display the tree
 */
public class TreePanel extends JPanel
{
    private final JTree fileTree;
    private final FileTreeBuilder fileTreeBuilder;
    private final MainFrame mainFrame;

    public TreePanel(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        fileTree = new JTree();
        fileTree.setModel(null);//default if a file isn't selected

        fileTreeBuilder = new FileTreeBuilder();//helper class that make the tree file tree from a chosen filenode
        JScrollPane treeScrollPane = new JScrollPane(fileTree);//stores the tree

        JButton selectFolderButton = new JButton("Open Folder"); //button to open file chooser
        selectFolderButton.addActionListener(e -> openFileChooser());

        fileTree.addTreeSelectionListener(e -> onFileSelect()); //if a file is selected

        //add scrollpane and button to panel
        add(treeScrollPane, BorderLayout.CENTER);
        add(selectFolderButton, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {return this;}

    //listener for file selection
    private void onFileSelect()
    {
        TreePath selectedPath = fileTree.getSelectionPath();
        if (selectedPath != null)
        {
            Object selectedNode = selectedPath.getLastPathComponent();
            if (selectedNode instanceof FileTreeBuilder.FileNode)
            {
                FileTreeBuilder.FileNode node = (FileTreeBuilder.FileNode) selectedNode;
                File selectedFile = node.getFile();
                if (selectedFile.isFile())
                {
                    //open the file tab (ensure it is selected)
                    mainFrame.getMainContentPanel().addFileTab(selectedFile);
                }
            }
        }
    }
    //listener for button
    private void openFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFolder = fileChooser.getSelectedFile();
            TreeModel treeModel = fileTreeBuilder.createFileTreeModel(selectedFolder);
            fileTree.setModel(treeModel);
        }
        else
        {
            fileTree.setModel(null);
        }
    }
}
