package com.mycompany.newgyms.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
   private String join_type;
   private String member_id;
   private String member_pw;
   private String member_name;
   private String member_gender;
   private String member_rrn1;
   private String member_rrn2;
   private String hp1;
   private String hp2;
   private String hp3;
   private String smssts_yn;
   private String email1;
   private String email2;
   private String emailsts_yn;
   private String zipcode;
   private String road_address;
   private String jibun_address;
   private String namuji_address;
   private String member_birth_y;
   private String member_birth_m;
   private String member_birth_d;
   private Date join_date;
   private String del_yn;
   
   private String center_name;
   private String owner_eid;
   private String owner_tel1;
   private String owner_tel2;
   private String owner_tel3;
   
   public MemberVO() {
      
   }

   // 일반 회원가입 생성자
   public MemberVO(
         String join_type,
         String member_name,
         String member_id,
         String member_pw,
         String zipcode,
         String road_address,
         String jibun_address,
         String namuji_address,
         String hp1,
         String hp2,
         String hp3,
         String smssts_yn,
         String email1,
         String email2,
         String emailsts_yn,
         String member_gender,
         String member_rrn1,
         String member_rrn2,
         String member_birth_y,
         String member_birth_m,
         String member_birth_d) {
      this.join_type = join_type;
      this.member_name = member_name;
      this.member_id = member_id;
      this.member_pw = member_pw;
      this.zipcode = zipcode;
      this.road_address = road_address;
      this.jibun_address = jibun_address;
      this.namuji_address = namuji_address;
      this.hp1 = hp1;
      this.hp2 = hp2;
      this.hp3 = hp3;
      this.smssts_yn = smssts_yn;
      this.email1 = email1;
      this.email2 = email2;
      this.emailsts_yn = emailsts_yn;
      this.member_gender = member_gender;
      this.member_rrn1 = member_rrn1;
      this.member_rrn2 = member_rrn2;
      this.member_birth_y = member_birth_y;
      this.member_birth_m = member_birth_m;
      this.member_birth_d = member_birth_d;
   }
   
   // 사업자 회원가입 생성자
   public MemberVO(
         String join_type,
         String member_name,
         String center_name,
         String owner_eid,
         String member_id,
         String member_pw,
         String zipcode,
         String road_address,
         String jibun_address,
         String namuji_address,
         String owner_tel1,
         String owner_tel2,
         String owner_tel3,
         String hp1,
         String hp2,
         String hp3,
         String smssts_yn,
         String email1,
         String email2,
         String emailsts_yn,
         String member_gender,
         String member_rrn1,
         String member_rrn2,
         String member_birth_y,
         String member_birth_m,
         String member_birth_d) {
      this.join_type = join_type;
      this.member_name = member_name;
      this.center_name = center_name;
      this.owner_eid = owner_eid;
      this.member_id = member_id;
      this.member_pw = member_pw;
      this.zipcode = zipcode;
      this.road_address = road_address;
      this.jibun_address = jibun_address;
      this.namuji_address = namuji_address;
      this.owner_tel1 = owner_tel1;
      this.owner_tel2 = owner_tel2;
      this.owner_tel3 = owner_tel3;
      this.hp1 = hp1;
      this.hp2 = hp2;
      this.hp3 = hp3;
      this.smssts_yn = smssts_yn;
      this.email1 = email1;
      this.email2 = email2;
      this.emailsts_yn = emailsts_yn;
      this.member_gender = member_gender;
      this.member_rrn1 = member_rrn1;
      this.member_rrn2 = member_rrn2;
      this.member_birth_y = member_birth_y;
      this.member_birth_m = member_birth_m;
      this.member_birth_d = member_birth_d;
   }
      public MemberVO(
            String member_id,
            String member_pw
            ) {
         this.member_id = member_id;
         this.member_pw = member_pw;
      }
      
      public MemberVO (
            String email1,
            String email2,
            String member_name) {
         
               this.email1 = email1;
               this.email2 = email2;
               this.member_name = member_name;
      }
      
      public MemberVO (
            String email1,
            String email2,
            String member_name,
            String member_id) {
         
         this.email1 = email1;
         this.email2 = email2;
         this.member_name = member_name;
         this.member_id = member_id;
      }
      

      /*
       * public MemberVO( String member_name, String hp1, String hp2, String hp3) {
       * 
       * this.member_name = member_name; this.hp1 = hp1; this.hp2 = hp2; this.hp3 =
       * hp3; }
       */
  
   public String getJoin_type() {
      return join_type;
   }

   public void setJoin_type(String join_type) {
      this.join_type = join_type;
   }

   public String getMember_id() {
      return member_id;
   }

   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }

   public String getMember_pw() {
      return member_pw;
   }

   public void setMember_pw(String member_pw) {
      this.member_pw = member_pw;
   }

   public String getMember_name() {
      return member_name;
   }

   public void setMember_name(String member_name) {
      this.member_name = member_name;
   }

   public String getMember_gender() {
      return member_gender;
   }

   public void setMember_gender(String member_gender) {
      this.member_gender = member_gender;
   }

   public String getMember_rrn1() {
      return member_rrn1;
   }

   public void setMember_rrn1(String member_rrn1) {
      this.member_rrn1 = member_rrn1;
   }

   public String getMember_rrn2() {
      return member_rrn2;
   }

   public void setMember_rrn2(String member_rrn2) {
      this.member_rrn2 = member_rrn2;
   }

   public String getHp1() {
      return hp1;
   }

   public void setHp1(String hp1) {
      this.hp1 = hp1;
   }

   public String getHp2() {
      return hp2;
   }

   public void setHp2(String hp2) {
      this.hp2 = hp2;
   }

   public String getHp3() {
      return hp3;
   }

   public void setHp3(String hp3) {
      this.hp3 = hp3;
   }

   public String getSmssts_yn() {
      return smssts_yn;
   }

   public void setSmssts_yn(String smssts_yn) {
      this.smssts_yn = smssts_yn;
   }

   public String getEmail1() {
      return email1;
   }

   public void setEmail1(String email1) {
      this.email1 = email1;
   }

   public String getEmail2() {
      return email2;
   }

   public void setEmail2(String email2) {
      this.email2 = email2;
   }

   public String getEmailsts_yn() {
      return emailsts_yn;
   }

   public void setEmailsts_yn(String emaists_yn) {
      this.emailsts_yn = emaists_yn;
   }

   public String getZipcode() {
      return zipcode;
   }

   public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
   }

   public String getRoad_address() {
      return road_address;
   }

   public void setRoad_address(String road_address) {
      this.road_address = road_address;
   }

   public String getJibun_address() {
      return jibun_address;
   }

   public void setJibun_address(String jibun_address) {
      this.jibun_address = jibun_address;
   }

   public String getNamuji_address() {
      return namuji_address;
   }

   public void setNamuji_address(String namuji_address) {
      this.namuji_address = namuji_address;
   }

   public String getMember_birth_y() {
      return member_birth_y;
   }

   public void setMember_birth_y(String member_birth_y) {
      this.member_birth_y = member_birth_y;
   }

   public String getMember_birth_m() {
      return member_birth_m;
   }

   public void setMember_birth_m(String member_birth_m) {
      this.member_birth_m = member_birth_m;
   }

   public String getMember_birth_d() {
      return member_birth_d;
   }

   public void setMember_birth_d(String member_birth_d) {
      this.member_birth_d = member_birth_d;
   }

   public Date getJoin_date() {
      return join_date;
   }

   public void setJoin_date(Date join_date) {
      this.join_date = join_date;
   }

   public String getDel_yn() {
      return del_yn;
   }

   public void setDel_yn(String del_yn) {
      this.del_yn = del_yn;
   }

   public String getCenter_name() {
      return center_name;
   }

   public void setCenter_name(String center_name) {
      this.center_name = center_name;
   }

   public String getOwner_eid() {
      return owner_eid;
   }

   public void setOwner_eid(String owner_eid) {
      this.owner_eid = owner_eid;
   }

   public String getOwner_tel1() {
      return owner_tel1;
   }

   public void setOwner_tel1(String owner_tel1) {
      this.owner_tel1 = owner_tel1;
   }

   public String getOwner_tel2() {
      return owner_tel2;
   }

   public void setOwner_tel2(String owner_tel2) {
      this.owner_tel2 = owner_tel2;
   }

   public String getOwner_tel3() {
      return owner_tel3;
   }

   public void setOwner_tel3(String owner_tel3) {
      this.owner_tel3 = owner_tel3;
   }
   
}