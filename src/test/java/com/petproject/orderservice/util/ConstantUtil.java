package com.petproject.orderservice.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantUtil {
    public static final Long VALID_ID = 1L;
    public static final Long INVALID_ID = -1L;

    public static final LocalDate VALID_ORDER_DATE = LocalDate.of(2020, 1, 1);
    public static final LocalDate VALID_ORDER_END_DATE = LocalDate.of(2020, 12, 31);
    public static final String VALID_ORDER_NUMBER = "12345620200101";
    public static final String VALID_CUSTOMER_NAME = "Customer";
    public static final BigDecimal VALID_ORDER_PRICE = BigDecimal.valueOf(100.00);

    public static final BigDecimal VALID_BOOK_PRICE = BigDecimal.valueOf(100.00);
    public static final String VALID_BOOK_TITLE = "Book";

    public static final String VALID_FIRST_NAME = "FirstName";
    public static final String VALID_LAST_NAME = "LastName";

}
