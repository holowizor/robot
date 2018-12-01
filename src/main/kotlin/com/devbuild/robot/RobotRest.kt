package com.devbuild.robot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by holow on 25.05.2017.
 */

@RestController class RobotRest {

    @Autowired
    lateinit var robotService: RobotService

    @RequestMapping("/move")
    fun move(@RequestParam("left") left: Int, @RequestParam("right") right: Int) {
        robotService.moveLeft(left)
        robotService.moveRight(right)
    }
}