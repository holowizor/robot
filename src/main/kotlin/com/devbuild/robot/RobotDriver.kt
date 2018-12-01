package com.devbuild.robot

const val MAX_SPEED = 3000
val FORWARD = arrayOf(4096, 0)
val BACKWARD = arrayOf(0, 4096)

val LEFT_WHEEL = arrayOf(2, 3, 4)
val RIGHT_WHEEL = arrayOf(7, 6, 5)

class RobotDriver(_pcaDriver: PCA9685Driver) {
    private val pcaDriver = _pcaDriver

    init {
        pcaDriver.reset()
        pcaDriver.setPWMFrequency(1000);
    }

    fun setLeftWheelSpeed(speed: Int) = setWheelSpeed(LEFT_WHEEL, speed)
    fun setRightWheelSpeed(speed: Int) = setWheelSpeed(RIGHT_WHEEL, speed)

    fun fullStop() {
        wheelStop(LEFT_WHEEL)
        wheelStop(RIGHT_WHEEL)
    }

    private fun setWheelSpeed(wheel:Array<Int>, speed: Int) {
        if (speed == 0) {
            wheelStop(wheel)
            return
        }

        var direction = FORWARD
        var speedVal = minOf(Math.abs(speed), MAX_SPEED)
        if (speed < 0) direction = BACKWARD

        pcaDriver.setPWM(wheel[0], 0, speedVal)
        pcaDriver.setPWM(wheel[1], direction[0], 0)
        pcaDriver.setPWM(wheel[2], direction[1], 0)
    }

    private fun wheelStop(wheel: Array<Int>) {
        pcaDriver.setPWM(wheel[0], 0, 0)
        pcaDriver.setPWM(wheel[1], 0, 0)
        pcaDriver.setPWM(wheel[2], 0, 0)
    }
}
