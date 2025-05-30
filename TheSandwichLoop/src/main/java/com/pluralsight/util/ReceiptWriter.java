package com.pluralsight.util;
import com.pluralsight.models.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    public static void saveReceipt(Order order) {
        // generate filename using current date/time (yyyyMMdd-HHmmss.txt)
        // create a FileWriter and wrap in BufferedWriter (src/main/resources/receipts)

        String fileName = "src/main/resources/Receipts/" + generateTimestamp() + ".txt";

        // loop through all order items
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            //   - write the items to the to receipt & writes total cost from getOrderSummary
            writer.write(order.getOrderSummary());
            System.out.println("Receipt has been saved in: " + fileName + " file.");
            // handles error
        } catch (IOException e) {
            System.out.println("Error, unable to write receipt. " + e.getMessage());
        }

    }

    private static String generateTimestamp() {
        // Create timestamp string useing a formatter
        // return that string

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return now.format(formatter);
    }
}
