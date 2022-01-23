package com.arsn.patterns.combinator;

import java.time.LocalDate;

import static com.arsn.patterns.combinator.CustomerValidator.*;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(
                "Arjun",
                "arjun@gmail.com",
                "+91 9965608234",
                LocalDate.of(1993, 6, 10)
        );

        //System.out.println(new CustomerValidatorService().isValid(customer));
        // if valid store customer

        ValidatorResponse result = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAdult())
                .apply(customer);

        System.out.println(result);

        if (!result.equals(ValidatorResponse.SUCCESS)) {
            throw new IllegalStateException(result.name());
        }
    }
}
