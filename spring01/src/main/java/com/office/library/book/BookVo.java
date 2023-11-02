package com.office.library.book;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookVo {
    private int b_no;
    private String b_thumbnail;
    private String b_name;
    private String b_author;
    private String b_publisher;
    private String b_publish_year;
    private String b_isbn;
    private String b_call_number;
    private int b_rental_able;
    private String b_reg_date;
    private String b_mod_date;
}
