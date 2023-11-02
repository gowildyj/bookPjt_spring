package com.office.library.book.admin;

import com.office.library.book.BookVo;
import com.office.library.book.admin.util.UploadFileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/book/admin")
@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    UploadFileService uploadFileService;

    //    도서등록
    @GetMapping("/registerBookForm")
    public String registerBookForm() {
        System.out.println("BController-registerBookForm()");
        String nextPage = "admin/book/register_book_form";
        return nextPage;
    }

    @PostMapping("/registerBookConfirm")
    public String registerBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
        System.out.println("BController-registerBookConfirm()");
        String nextPage = "admin/book/register_book_ok";

        String savedFileName = uploadFileService.upload(file);
        if (savedFileName != null) {
            bookVo.setB_thumbnail(savedFileName);
            int result = bookService.registerBookConfirm(bookVo);

            if (result <= 0) nextPage = "admin/book/register_book_ng";
        } else {
            nextPage = "admin/book/register_book_ng";
        }
        return nextPage;
    }

    //    도서검색
    @GetMapping("/searchBookConfirm")
    public String searchBookForm(BookVo bookVo, Model model) {
        System.out.println("BController-searchBookForm");
        String nextPage = "/admin/book/search_book";
        List<BookVo> bookVoList = bookService.searchBookForm(bookVo);
        model.addAttribute("bookVos", bookVoList);
        return nextPage;
    }
}
