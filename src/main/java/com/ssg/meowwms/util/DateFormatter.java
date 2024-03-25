package com.ssg.meowwms.util;

import org.springframework.format.Formatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {
    private final String pattern = "yyyy-MM-dd";

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return new SimpleDateFormat(pattern).parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        return new SimpleDateFormat(pattern).format(object);
    }
}
