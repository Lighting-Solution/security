package com.ls.security.android.controller.impl;


import com.ls.security.android.controller.AndroidController;
import com.ls.security.android.service.AndroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lighting_solutions/security")
public class AndroidControllerImpl implements AndroidController {
    private final AndroidService androidService;

    @Autowired
    public AndroidControllerImpl(AndroidService androidService) {
        this.androidService = androidService;
    }




}
