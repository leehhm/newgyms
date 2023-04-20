package com.mycompany.newgyms.member.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.newgyms.member.dao.MemberDAO;

@Service("kakaoService")
@Transactional(propagation=Propagation.REQUIRED)
public class KakaoServiceImpl implements KakaoService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
    public String getReturnAccessToken(String code) {
         String access_token = "";
         String refresh_token = "";
         String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
             //HttpURLConnection ���� �� ����
             conn.setRequestMethod("POST");
             conn.setDoOutput(true);
             
             // buffer ��Ʈ�� ��ü �� ���� �� ��û
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
             StringBuilder sb = new StringBuilder();
             sb.append("grant_type=authorization_code");
             sb.append("&client_id=b72869301ce448407f587e4c23f08553");  //�� KEY VALUE
             sb.append("&redirect_uri=http://localhost:8080/newgyms/member/kakaoLogin.do"); // �� CALLBACK ���
             sb.append("&code=" + code);
             bw.write(sb.toString());
             bw.flush();
             
             int responseCode = conn.getResponseCode();
             System.out.println("responseCode : " + responseCode);
             
             //  RETURN �� result ������ ����
             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8")); // �ѱ� �ȱ����� �ޱ�
             String br_line = "";
             String result = "";
 
             while ((br_line = br.readLine()) != null) {
                 result += br_line;
             }
             System.out.println("response : " + result);
 
             JsonParser parser = new JsonParser();
             JsonElement element = parser.parse(result);
 
             
             // ��ū �� ���� �� ����
             access_token = element.getAsJsonObject().get("access_token").getAsString();
             refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();
 
             System.out.println("access_token : " + access_token);
             System.out.println("refresh_token : " + refresh_token);
             
             br.close();
             bw.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
 
         return access_token;
     }
	
	@Override
    public Map<String,Object> getUserInfo(String access_token) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
         try {
             URL url = new URL(reqURL);
             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
             conn.setRequestMethod("POST");
 
            //��û�� �ʿ��� Header�� ���Ե� ����
             conn.setRequestProperty("Authorization", "Bearer " + access_token);
             conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
             conn.setRequestProperty("charset", "utf-8");
 
             int responseCode = conn.getResponseCode();
             System.out.println("responseCode : " + responseCode);
 
             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
 
             String br_line = "";
             String result = "";
 
             while ((br_line = br.readLine()) != null) {
                 result += br_line;
             }
             System.out.println("response:" + result);
 
             JsonParser parser = new JsonParser();
             JsonElement element = parser.parse(result);
             JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
             JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
 
             String id = element.getAsJsonObject().get("id").getAsString();
             String nickname = properties.getAsJsonObject().get("nickname").getAsString();
             String email = kakao_account.getAsJsonObject().get("email").getAsString();
             String gender = kakao_account.getAsJsonObject().get("gender").getAsString();
             resultMap.put("nickname", nickname);
             resultMap.put("id", id);
             resultMap.put("email", email); 
             resultMap.put("gender", gender);
             
         } catch (IOException e) {
             e.printStackTrace();
         }
         return resultMap;
     }
	
	@Override
    public void getLogout(String access_token) {
        String reqURL ="https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            conn.setRequestProperty("Authorization", "Bearer " + access_token);
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            if(responseCode == 400)
                throw new RuntimeException("īī�� �α׾ƿ� ���� ���� �߻�");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            
            String br_line = "";
            String result = "";
            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }
            System.out.println("���");
            System.out.println(result);
        }catch(IOException e) {
            
        }
    }


}