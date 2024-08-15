
package testbasicfile;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 * @author Francisco Garcia
 * PID# 5767275
 * Programming Assignment 3
 * Professor Joslyn Smith
 */
public class TestBasicFile {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        boolean done = false;

        String menu = "Menu\n1. File Specifications \n2. Backup File\n3. "
                + "Word Count\n4. Exit";

        while (!done) {
            BasicFile f = new BasicFile();

            String s = JOptionPane.showInputDialog(menu);

            try {
                int i = Integer.parseInt(s);

                switch (i) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "The name, path, and size of the file will be shown after it has been selected.",
                                "File Selection", JOptionPane.INFORMATION_MESSAGE);
                        f.fileSelecting();

                        if (f.exists()) {
                            displayInfo(f.toString(), "File");
                        } else {
                            f.fileSelecting();
                        }
                        break;

                    case 2:
                        f.fileSelecting();

                        if (f.exists()) {
                            displayInfo(f.toString(), "File");
                        } else {
                            f.fileSelecting();
                        }

                        f.fileBackup();
                        break;

                    case 3:
                        f.fileSelecting();

                        if (f.exists()) {
                            displayInfo(f.wordCount(), "Word Count");
                        } else {
                            f.fileSelecting();
                        }
                        break;

                    case 4:

                        done = true;
                        break;
                    default:
                }
            } catch (NumberFormatException e) {
                System.exit(0);
            } catch (NullPointerException e) {
                System.exit(0);
            }
        }
    }

    static void displayInfo(String s, String info) {
        JOptionPane.showMessageDialog(null, s, info, JOptionPane.INFORMATION_MESSAGE);
    }

}