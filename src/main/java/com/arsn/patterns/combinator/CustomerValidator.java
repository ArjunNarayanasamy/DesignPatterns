package com.arsn.patterns.combinator;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

import static com.arsn.patterns.combinator.CustomerValidator.ValidatorResponse.*;

public interface CustomerValidator extends Function<Customer, CustomerValidator.ValidatorResponse> {

    public static CustomerValidator isEmailValid() {
        return customer -> customer.getEmail().contains("@gmail.com") ? SUCCESS : EMAIL_NOT_VALID;
    }

    public static CustomerValidator isPhoneNumberValid() {
        return customer -> customer.getPhone().startsWith("+91") ? SUCCESS : PH_NO_NOT_VALID;
    }

    public static CustomerValidator isAdult() {
        return customer -> Period.between(customer.getDob(), LocalDate.now()).getYears() > 18 ? SUCCESS : IS_NOT_AN_ADULT;
    }

    default CustomerValidator and(CustomerValidator other) {
        return customer -> {
            ValidatorResponse result = this.apply(customer);
            return result.equals(SUCCESS) ? other.apply(customer) : result;
        };
    }

    enum ValidatorResponse {
        SUCCESS,
        EMAIL_NOT_VALID,
        PH_NO_NOT_VALID,
        IS_NOT_AN_ADULT
    }
}
