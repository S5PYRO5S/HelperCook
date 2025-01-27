package GUI;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;

/**
 * JPanel that displays a file tree and allows the user
 * to open folders and select files.
 * It also uses the {@link FileTreeBuilder} helper class to construct the file tree from the selected folder
 */
public class TreePanel extends JPanel
{
    private final JTree fileTree;                   //the tree
    private final FileTreeBuilder fileTreeBuilder;  //helper class to construct the tree
    private final MainFrame mainFrame;              //parent frame

    //constructor without initial file list.
    public TreePanel(MainFrame mainFrame)
    {
        this(mainFrame, null);
    }

    /**
     * Constructor with the file list
     *
     * @param mainFrame the parent frame
     * @param files     the file list
     */
    public TreePanel(MainFrame mainFrame, java.util.List<File> files)
    {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        fileTree = new JTree();
        fileTreeBuilder = new FileTreeBuilder(); //helper class to build the tree
        JScrollPane treeScrollPane = new JScrollPane(fileTree); //scrollable tree

        //if files are provided, create the virtual file tree, else set default tree model
        TreeModel treeModel = fileTreeBuilder.createVirtualFileTreeModel(files);
        fileTree.setModel(treeModel);


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

    /**
     * Action listener for file selection from the tree
     * It opens a new tab in the main content panel if a .cook file is selected
     */
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

    /**
     * Action listener for the folder select button
     */
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
