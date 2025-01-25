package GUI;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;

public class TreePanel extends JPanel
{
    private final JTree fileTree;
    private final FileTreeBuilder fileTreeBuilder;
    private final MainFrame mainFrame;

    public TreePanel(MainFrame mainFrame)
    {
        this(mainFrame, null);
    }

    public TreePanel(MainFrame mainFrame, java.util.List<File> files)
    {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        fileTree = new JTree();
        fileTreeBuilder = new FileTreeBuilder(); //helper class to build the tree
        JScrollPane treeScrollPane = new JScrollPane(fileTree); //scrollable tree

        //if files are provided, create the virtual file tree
        if (files != null && !files.isEmpty())
        {
            TreeModel treeModel = fileTreeBuilder.createVirtualFileTreeModel(files);
            fileTree.setModel(treeModel);
        }

        JButton selectFolderButton = new JButton("Open Folder"); //folder select button
        selectFolderButton.addActionListener(e -> openFileChooser()); //folder select button listener

        fileTree.addTreeSelectionListener(e -> onFileSelect());

        add(treeScrollPane, BorderLayout.CENTER);
        add(selectFolderButton, BorderLayout.SOUTH);
    }

    public JPanel getPanel()
    {
        return this;
    }

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
                    mainFrame.getMainContentPanel().addFileTab(selectedFile);
                }
            }
        }
    }

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
