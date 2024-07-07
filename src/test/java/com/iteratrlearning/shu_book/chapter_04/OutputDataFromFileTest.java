package com.iteratrlearning.shu_book.chapter_04;

import org.junit.Test;

public class OutputDataFromFileTest {

    public String[] invoiceArr = {"Dear Joe Bloggs", 
                                "Here is your invoice for the dental treatment that you received.",
                                "Amount: $100",
                                "regards,",
                                "Dr Avaj", 
                                "Awesome Dentist"};
    public String prefix = "Dear";

    @Test
    public void testInvoice() {
        for(final String line:invoiceArr) {
            if(line.startsWith(prefix)) {
                System.out.println(line.substring(prefix.length()));
                break;
            }
        }
    }

}
