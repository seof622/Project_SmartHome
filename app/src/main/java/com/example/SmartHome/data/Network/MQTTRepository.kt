package com.example.SmartHome.data.Network

import android.util.Log
import com.example.SmartHome.data.mqttIP
import com.example.SmartHome.data.mqttPort
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage

class MQTTRepository {
    private val mqttURL: String = "tcp://$mqttIP:$mqttPort"

    private val mqttClient = MqttClient(mqttURL, MqttClient.generateClientId(), null).apply {
        this.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                Log.e("MQTT", "Connection Lost")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                TODO("Not yet implemented")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.e("MQTT", "Message Delivery Complete")
            }
        })
    }

    fun connect() {
        try {
            mqttClient.connect()
        } catch (e: Exception) {
            Log.e("Mqtt Connect", e.toString())
        } finally {
            Log.e("Mqtt Connect", "Connect Complete")
        }
    }

    fun subscribe(topic: String) {
        mqttClient.subscribe(topic)
    }

    fun disconnect() {
        mqttClient.disconnect()
    }

    fun publish (topic: String, data: ByteArray) {
        mqttClient.publish(topic, MqttMessage(data))
    }
}