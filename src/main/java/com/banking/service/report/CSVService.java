package com.banking.service.report;

import com.banking.entity.Customer;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
@Service
public class CSVService {

    public byte[] generateCsv(List<Customer> customerList) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream);

        // Add header
        writer.write("Customer_id,address,age,created_at,date_of_birth,email,first_name,last_name,phone\n");

        // Add rows
        for (Customer customer : customerList) {
            writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                    customer.getCustomerId(),
                    customer.getAddress(),
                    customer.getAge(),
                    customer.getCreatedAt(),
                    customer.getDateOfBirth(),
                    customer.getEmail(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getPhone()));
        }

        writer.flush();
        writer.close();
        return byteArrayOutputStream.toByteArray();
    }

}
