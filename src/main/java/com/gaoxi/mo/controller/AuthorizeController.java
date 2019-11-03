package com.gaoxi.mo.controller;

import com.gaoxi.mo.dto.AccessTokenDTO;
import com.gaoxi.mo.dto.GithubUser;
import com.gaoxi.mo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sound.midi.SoundbankResource;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name ="code") String code, @RequestParam(name ="state") String state)
    {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
//        accessTokenDTO.setClient_id("Iv1.82c41d782c8dbbe9");
//        accessTokenDTO.setClient_secret("897b4c9a74703e41271dd1245adfd5d6346861c9");
//        accessTokenDTO.setRedirect_uri("http://localhost:8090/callback");
        System.out.println("the client_id is "+ client_id);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accesToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user=githubProvider.getUser(accesToken);
        System.out.println("user name is "+ user.getName());

        return "hello";
    }
}
