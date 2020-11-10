package org.dmg.dreamcaster.controller;

import org.dmg.dreamcaster.model.Power;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerController {
    @RequestMapping("/newPower")
    public HttpEntity<Power> newPower(@RequestParam(value = "key") String key) {

        Power power = new Power();
        power.setKey(key);

        return new ResponseEntity<>(power, HttpStatus.OK);
    }
}