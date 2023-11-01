package com.office.library.admin.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminMemberService {
    final static public int ADMIN_ACCOUNT_ALREADY_EXIST = 0;
    final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1;
    final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1;

    private final AdminMemberDao adminMemberDao;

    public int createAccountConfirm(AdminMemberVo adminMemberVo){
        System.out.println("AMService-createAccountConfirm()");
        boolean isMember = adminMemberDao.isAdminMember(adminMemberVo.getA_m_id());
        if(!isMember) {
            int result = adminMemberDao.insertAdminAccount(adminMemberVo);
            if(result>0) return ADMIN_ACCOUNT_CREATE_SUCCESS;
            else return ADMIN_ACCOUNT_CREATE_FAIL;
        }
        return ADMIN_ACCOUNT_ALREADY_EXIST;
    }

    public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
        System.out.println("AMService-loginConfirm()");
        AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(adminMemberVo);

        if(loginedAdminMemberVo!=null)
            System.out.println("ADMIN_ACCOUNT_LOGIN_SUCCESS");
        else
            System.out.println("ADMIN_ACCOUNT_LOGIN_FAIL");

        return loginedAdminMemberVo;
    }

    public List<AdminMemberVo> listupadmin() {
        System.out.println("AMService-listupAdmin()");

        return adminMemberDao.selectAdminList();
    }

    public void setAdminApproval(int a_m_no) {
        System.out.println("AMService-AdminApprval()");
        int result = adminMemberDao.updateAdminAccount(a_m_no);
    }

    public int modifyAccountConfirm(AdminMemberVo adminMemberVo) {
        System.out.println("AMService-modifyAccountConfirm()");
        return adminMemberDao.selectAdmin(a_m_no);
    }
}
