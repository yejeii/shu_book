package com.iteratrlearning.shu_book.chapter_04;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class CheckFileExistsTest {

    @Test
    public void checkImportFile() throws FileNotFoundException {
        final String PATH = "C:\\Dev\\tools\\Notepad++\\contextMenu.xml";
        final File file = new File(PATH);

        if(!file.exists()) throw new FileNotFoundException();

        final int seperatorIndex = PATH.lastIndexOf(".");
        if(seperatorIndex != -1) {
            System.out.println(seperatorIndex);
            if(seperatorIndex == PATH.length() - 1) {
                System.out.println("No extension found for File");
            }
        }
    }
}
