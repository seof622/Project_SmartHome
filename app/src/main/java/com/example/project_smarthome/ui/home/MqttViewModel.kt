package com.example.project_smarthome.ui.home

import androidx.lifecycle.ViewModel
import com.example.project_smarthome.data.mqttIP
import com.example.project_smarthome.data.mqttPort
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage


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
}