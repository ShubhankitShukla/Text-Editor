import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
public class project1 implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    JMenuBar jMenuBar;

    project1() {
        frame = new JFrame("Text Editor");
        textArea = new JTextArea();

        jMenuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");
        JMenuItem printFile = new JMenuItem("Print File");
        JMenuItem newFile = new JMenuItem("New File");

        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);

        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);

        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem close = new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);

        jMenuBar.add(file);
        jMenuBar.add(edit);

        frame.setJMenuBar(jMenuBar);

        frame.add(textArea);
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String arg[]) {
        project1 Project1 = new project1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call = e.getActionCommand();
        if (call == "New File") {
            textArea.setText("");
        } else if (call == "Cut") {
            textArea.cut();
        } else if (call == "Copy") {
            textArea.copy();
        } else if (call == "Paste") {
            textArea.paste();
        } else if (call == "Close") {
            frame.setVisible(false);
        } else if (call == "Save File") {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if (ans == jFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (call == "Open File") {
            JFileChooser jFileChooser1 = new JFileChooser("C:");
            int ans = jFileChooser1.showOpenDialog(null);
            if (ans == jFileChooser1.APPROVE_OPTION) {
                File file = new File(jFileChooser1.getSelectedFile().getAbsolutePath());
                try {
                    String s1 = "", s2 = "";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while ((s1 = bufferedReader.readLine()) != null) {
                        s2 += s1 + "\n";
                    }

                    textArea.setText(s2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (call == "Print File") {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}