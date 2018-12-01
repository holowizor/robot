package com.devbuild.robot

import com.pi4j.io.i2c.I2CBus
import org.springframework.stereotype.Service

@Service class RobotService() {

    private val robotDriver: RobotDriver = RobotDriver(PCA9685Driver(0x60, I2CBus.BUS_1))

    fun moveLeft(speed: Int) = robotDriver.setLeftWheelSpeed(speed)

    fun moveRight(speed: Int) = robotDriver.setRightWheelSpeed(speed)

}
