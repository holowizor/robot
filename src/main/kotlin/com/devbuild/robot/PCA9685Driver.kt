package com.devbuild.robot

import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CDevice
import com.pi4j.io.i2c.I2CFactory

const val MODE1 = 0x00
const val PRESCALE = 0xFE
const val LED0_ON_L = 0x06
const val LED0_ON_H = 0x07
const val LED0_OFF_L = 0x08
const val LED0_OFF_H = 0x09

class PCA9685Driver(address: Int, busNumber: Int) {

    private val bus: I2CBus = I2CFactory.getInstance(busNumber)
    private val device: I2CDevice = bus.getDevice(address)

    fun reset() = device.write(MODE1, 0x00.toByte())

    fun setPWMFrequency(frequency: Int) {
        val prescale = Math.floor((25000000.0 / 4096.0) / frequency.toDouble() - 0.5)
        val oldMode = device.read(MODE1)
        val newMode = ((oldMode and 0x7f) or 0x10).toByte()

        device.write(MODE1, newMode)
        device.write(PRESCALE, prescale.toByte())
        device.write(MODE1, oldMode.toByte())
        sleep(5)
        device.write(MODE1, (oldMode or 0x80).toByte())
    }

    fun setPWM(channel: Int, on: Int, off: Int) {
        val offset = 4 * channel;
        device.write(offset + LED0_ON_L, (on and 0xFF).toByte())
        device.write(offset + LED0_ON_H, (on shr 8).toByte())
        device.write(offset + LED0_OFF_L, (off and 0xFF).toByte())
        device.write(offset + LED0_OFF_H, (off shr 8).toByte())
    }

    private fun sleep(millis: Long) = Thread.sleep(millis)
}