package com.example.SmartHome.ui.home

import androidx.lifecycle.ViewModel
import com.example.SmartHome.data.mqttIP
import com.example.SmartHome.data.mqttPort
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage

/*
TODO: 상황에 따라서 다음과 같이 분리해야 할 수 있음
- MQTT Client Repository를 두고,
- 각 Screen에 대해서 MQTT 통신이 필요하면 ViewModel에 Repository를 포함.

현재는 ViewModel로 관리 중.
MQTT 접속 관련 에러 잡기.
 */


class MQTTViewModel : ViewModel() {
    private val mqttURL: String = "tcp://$mqttIP:$mqttPort"

    companion object {
        private var mqttClient: MqttClient? = null
        fun connect() {
            mqttClient?.connect()
        }

        fun setClientCallback(callback: MqttCallback) {
            mqttClient?.setCallback(callback)
        }
    }

    init {
        mqttClient = MqttClient(mqttURL, MqttClient.generateClientId(), null)
        mqttClient?.connect()
        mqttClient?.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                TODO("Not yet implemented")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                TODO("Not yet implemented")
            }
        })
    }

    fun subscribe (topic: String) {
        mqttClient?.subscribe(topic)
    }

    fun publish (topic: String, data: ByteArray) {
        mqttClient?.publish(topic, MqttMessage(data))
    }
}