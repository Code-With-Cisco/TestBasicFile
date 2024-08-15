package testbasicfile;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.StreamTokenizer;

/**
 * @author Francisco Garcia
 * PID# 5767275
 * Programming Assignment 3
 * Professor Joslyn Smith
 */
public class BasicFile {
    File f;
    JFileChooser fileSelect;
    File f2 = new File(".", "File Backup");

    BasicFile() {
        fileSelect = new JFileChooser(".");
    }
    public void fileSelecting() {
        int statusResult = fileSelect.showOpenDialog(null);

        try {
            if (statusResult != JFileChooser.APPROVE_OPTION) {
                throw new IOException();
            }
            f = fileSelect.getSelectedFile();

            if (!f.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No File Found ", "Error", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.exit(0);
        }
    }
    void fileBackup() throws FileNotFoundException {
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            in = new DataInputStream(new FileInputStream(f));
            out = new DataOutputStream(new FileOutputStream(f2));

            try {
                while (true) {
                    byte data = in.readByte();
                    out.writeByte(data);
                }
            } catch (EOFException e) {
                JOptionPane.showMessageDialog(null, "File backup completed.",
                        "Complete", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "File Not Found ",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                display(e.toString(), "Error");
            }
        }
    }
    boolean exists() {
        return f.exists();
    }
    public String toString() {
        return f.getName() + "\n" + f.getAbsolutePath() + "\n" + f.length() + " bytes";
    }
    public String wordCount() {
        try {
            int wordCount = 0,
                    numberCount = 0,
                    lineCount = 1,
                    characterCount = 0,
                    totalWords = 0;
            FileReader r = new FileReader(f);
            StreamTokenizer t = new StreamTokenizer(r);       
            t.resetSyntax();
            t.whitespaceChars(0, ' ');
            t.wordChars('A', 'Z');
            t.wordChars('0', '9');
            t.eolIsSignificant(true);
            while (t.nextToken() != StreamTokenizer.TT_EOF) {
                switch (t.ttype) {
                    case StreamTokenizer.TT_NUMBER:
                        break;
                    case StreamTokenizer.TT_WORD:
                        characterCount += t.sval.length();
                        wordCount++;
                        break;
                    case StreamTokenizer.TT_EOL:
                        lineCount++;
                        break;
                    case StreamTokenizer.TT_EOF:
                        break;
                    default:
                }
            }
            r.close();
            totalWords = numberCount + wordCount;
            return f.getName() + " has " + lineCount + " line(s), "
                    + totalWords + " word(s), and "
                    + characterCount + " characters. ";
        } catch (IOException e) {
            display(e.toString(), "Error");
        }
        return " ";

    }
    void display(String msg, String s) {
        JOptionPane.showMessageDialog(null, msg, s, JOptionPane.ERROR_MESSAGE);
    }

}