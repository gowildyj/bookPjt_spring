package com.office.library.book.admin;


import com.office.library.book.BookVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jca.context.BootstrapContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookService {
    final static public int BOOK_ISBN_ALREADY_EXIST = 0;
    final static public int BOOK_ISBN_REGISTER_SUCCESS = 1;
    final static public int BOOK_ISBN_REGISTER_FAIL = -1;


    private final BookDao bookDao;

    public int registerBookConfirm(BookVo bookVo) {
        System.out.println("BService-registerBookConfirm()");
        boolean isISBN = bookDao.isISBN(bookVo.getB_isbn());
        if (!isISBN) {
            int result = bookDao.insertBook(bookVo);
            if (result > 0) return BOOK_ISBN_REGISTER_SUCCESS;
            else return BOOK_ISBN_REGISTER_FAIL;
        } else return BOOK_ISBN_ALREADY_EXIST;
    }

    public List<BookVo> searchBookForm(BookVo bookVo) {
        System.out.println("BService-searchBookForm()");
        return bookDao.selectBooksBySearch(bookVo);
    }
}
