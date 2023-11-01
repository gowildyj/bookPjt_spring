package com.office.library.admin.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
    private final AdminMemberService adminMemberService;

    @GetMapping("/createAccountForm")
    public String createAccountForm() {
        System.out.println("AMController-createAccountForm()");
        String nextPage = "admin/member/create_account_form";
        return nextPage;
    }

    @PostMapping("/createAccountConfirm")
    public String createAccountConfirm(AdminMemberVo adminMemberVo) {
        System.out.println("AMController-createAccountConfirm()");
        String nextPage = "admin/member/create_account_ok";
        int result = adminMemberService.createAccountConfirm(adminMemberVo);
        if (result <= 0) nextPage = "admin/member/create_account_ng";
        return nextPage;
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        System.out.println("AMController-loginForm()");
        String nextPage = "/admin/member/login_form";
        return nextPage;
    }

    @PostMapping("/loginConfirm")
    public String loginConfirm(AdminMemberVo adminMemberVo, HttpSession httpSession) {
        System.out.println("AMController-loginConfirm()");
        String nextPage = "/admin/member/login_ok";
        AdminMemberVo loginedAdminMemberVo = adminMemberService.loginConfirm(adminMemberVo);
        if (loginedAdminMemberVo == null) {
            nextPage = "/admin/member/login_ng";
        } else {
            httpSession.setAttribute("loginedAdminMemberVo", loginedAdminMemberVo);
            httpSession.setMaxInactiveInterval(60 * 30);
        }
        return nextPage;
    }

    @GetMapping("/logoutConfirm")
    public String logoutConfirm(HttpSession httpSession) {
        System.out.println("AMController-logoutConfirm()");
        String nextPage = "redirect:/admin";
        httpSession.invalidate();
        return nextPage;
    }

    @GetMapping("/listupAdmin")
    public String listupAdmin(Model model) {
        System.out.println("AMController-listupAdmin()");
        String nextPage = "/admin/member/listup_admins";

        List<AdminMemberVo> adminMemberVoList = adminMemberService.listupadmin();
        model.addAttribute("adminMemberVos", adminMemberVoList);

        return nextPage;
    }

//    @GetMapping("/listupAdmin")
//    public ModelAndView listupAdmin() {
//        System.out.println("AMController-listupAdmin()");
//        String nextPage = "/admin/member/listup_admins";
//
//        List<AdminMemberVo> adminMemberVoList = adminMemberService.listupadmin();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName(nextPage);
//        modelAndView.addObject("adminMemberVos", adminMemberVoList);
//
//        return modelAndView;
//    }

    @GetMapping("/setAdminApproval")
    public String setAdminApproval(@RequestParam("a_m_no") int a_m_no){
        System.out.println("AMController-setAdminApporval()");
        String nextPage = "redirect:/admin/member/listupAdmin";

        adminMemberService.setAdminApproval(a_m_no);
        return nextPage;
    }


}
