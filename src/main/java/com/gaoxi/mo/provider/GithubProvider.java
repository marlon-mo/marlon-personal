package com.gaoxi.mo.provider;

import com.alibaba.fastjson.JSON;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(com.gaoxi.mo.dto.AccessTokenDTO accessTokenDTO) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String res_str = response.body().string();
            System.out.println("the return res_str values is "+ res_str);
            String  token=res_str.split("&")[0].split("=")[1];
            System.out.println("the return accesstoken values is "+ token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public com.gaoxi.mo.dto.GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
       // https://api.github.com/user?access_token=2617c23b83da366bb6df85a93f6f398cd402feab
        //https://api.github.com/user?access_token" + accessToken
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+ accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String res_str = response.body().string();
            System.out.println("the response string is " + res_str);
            com.gaoxi.mo.dto.GithubUser githubUser = JSON.parseObject(res_str, com.gaoxi.mo.dto.GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
