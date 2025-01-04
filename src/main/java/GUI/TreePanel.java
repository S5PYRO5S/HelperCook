package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.File;

/**
 * class to display the tree
 */
public class TreePanel
{
    private final JPanel panel;
    private final JTree fileTree;
    private final FileTreeBuilder fileTreeBuilder;
    private final MainFrame mainFrame;

    public TreePanel(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        panel = new JPanel(new BorderLayout());
        fileTree = new JTree();
        fileTree.setModel(null);
        fileTreeBuilder = new FileTreeBuilder();
        JScrollPane treeScrollPane = new JScrollPane(fileTree);

        JButton selectFolderButton = new JButton("Open Folder");
        selectFolderButton.addActionListener(e -> openFileChooser());

        fileTree.addTreeSelectionListener(e -> onFileSelect());

        panel.add(treeScrollPane, BorderLayout.CENTER);
        panel.add(selectFolderButton, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {return panel;}

//    private void onFileSelect()
//    {
//        //get selected file node
//        TreePath selectedPath = fileTree.getSelectionPath();
//        if (selectedPath != null)
//        {
//            Object selectedNode = selectedPath.getLastPathComponent();
//            if (selectedNode instanceof DefaultMutableTreeNode node)
//            {
//                if (node.getUserObject() instanceof String fileName)
//                {
//                    File file = new File(fileName);
//                    mainFrame.getMainContentPanel().addFileTab(file);  //add a new tab with the file
//                }
//            }
//        }
//    }

//    private void onFileSelect()
//    {
//        // Get the selected tree path
//        TreePath selectedPath = fileTree.getSelectionPath();
//        if (selectedPath != null)
//        {
//            Object selectedNode = selectedPath.getLastPathComponent();
//            if (selectedNode instanceof DefaultMutableTreeNode)
//            {
//                DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode;
//                if (node.getUserObject() instanceof String)
//                {
//                    String fileName = (String) node.getUserObject();
//                    File file = new File(fileTreeBuilder.getRootDirectory(), fileName); // Get full file path
//
//                    if (file.isFile())
//                    {
//                        // Add a new tab only if it's a file
//                        mainFrame.getMainContentPanel().addFileTab(file);
//                    }
//                }
//            }
//        }
//    }
    private void onFileSelect() {
        TreePath selectedPath = fileTree.getSelectionPath();
        if (selectedPath != null) {
            Object selectedNode = selectedPath.getLastPathComponent();
            if (selectedNode instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode;
                Object userObject = node.getUserObject();
                if (userObject instanceof File) {
                    File selectedFile = (File) userObject;
                    if (selectedFile.isFile()) {
                        // Add a new tab only if the selected node is a file
                        mainFrame.getMainContentPanel().addFileTab(selectedFile);
                    }
                }
            }
        }
    }

    private void openFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(panel);

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
