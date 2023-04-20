package com.mycompany.newgyms.member.service;

import java.util.Map;

public interface KakaoService {
    public String getReturnAccessToken(String code) throws Exception;
    public Map<String,Object> getUserInfo(String access_token) throws Exception;
    public void getLogout(String access_token) throws Exception;
}