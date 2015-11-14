/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xqfcounter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author shifeng
 */
public class XQFCounter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        PrintWriter out
   = new PrintWriter(new BufferedWriter(new FileWriter("result.csv")));
        File dir = new File(args[0]);
        if (dir.isDirectory()) {
            File[] fileList = dir.listFiles();
            for (File file : fileList) {
                XQFLoader loader = new XQFLoader(file);
                try {
                    loader.load();
                    List<String> format = loader.format();
                    for (String s : format) {
                        out.print(s+",");
                    }
                    out.println();

                } catch (FileNotFoundException ex) {
                    System.err.println("文件未找到！");
                } catch (Exception ex) {
                    continue;
                }
            }
            out.flush();
            out.close();
        }



    }
}
