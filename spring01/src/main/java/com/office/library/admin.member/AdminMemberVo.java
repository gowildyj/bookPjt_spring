package com.office.library.admin.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminMemberVo {
    int a_m_no; // 관리자번호
    int a_m_approval; // 최고 관리자 승인 여부
    private String a_m_id;
    private String a_m_pw;
    private String a_m_name;
    private String a_m_gender;
    private String a_m_part;
    private String a_m_position;
    private String a_m_mail;
    private String a_m_phone;
    private String a_m_reg_date; // 관리자 등록일
    private String a_m_mod_date; // 관리자 수정일
}
