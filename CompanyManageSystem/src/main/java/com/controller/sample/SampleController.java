package com.controller.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controller.base.ControllerBase;
import com.service.sample.SampleService;

/**
 * ログイン コントローラー
 */
@RestController
@RequestMapping("/mock")
public class SampleController extends ControllerBase {

	@Autowired
	SampleService service;

	public SampleController() {
	}

    @GetMapping("/getInfo")
    public String getInfo(String username,String password){
        System.out.println("username:" + username + "," + "password:" + password);
        return "OK";
    }

}