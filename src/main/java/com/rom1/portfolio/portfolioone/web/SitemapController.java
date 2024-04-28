package com.rom1.portfolio.portfolioone.web;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rom1.portfolio.portfolioone.utils.Logger;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class SitemapController {
    //2:06
    @GetMapping({"/", "/home", "/index"})
    public String getHomePage(HttpServletRequest request) {
        Logger.getLogger().printMessage("Remote connection from user: %s (%s)".formatted(request.getRemoteUser(), request.getRemoteAddr()));

        return "index";
    }
}
