package cn.tosee.gms

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.ts.gms.ErrorInfo
import cn.ts.gms.ResultCallback
import cn.ts.gms.bean.LoginParams
import cn.ts.gms.client.GMSClient
import cn.ts.gms.client.GMSClientListener
import cn.ts.gms.connect.bean.EnvConfig
import cn.ts.gms.connect.bean.GMSMessage
import cn.ts.gms.connect.bean.Options
import cn.ts.gms.user.GMSAttributeWithState
import cn.ts.gms.utils.EmptyUtils
import cn.ts.gms.utils.EncryptUtils

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Main Test"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val instance: GMSClient = GMSClient.createInstance(this, "7zVD31N8WqmP0xyE", Options(
            EnvConfig("https://api-dev.rzrtc.com")
        ), object :
            GMSClientListener {
            override fun onConnectionStateChanged(code: Int, reason: Int) {
                Log.e(TAG, "onConnectionStateChanged: $code reason $reason")
            }

            override fun onMessageReceived(message: GMSMessage?, userId: String) {
                Log.e(TAG, "onMessageReceived: $message userId $userId")
            }

            override fun onPeersOnlineStatusChanged(peersStatus: Map<String, Int>) {
                Log.e(TAG, "onPeersOnlineStatusChanged: $peersStatus ")
            }

            override fun onPeersUserAttributesChanged(userAttributes: Map<String, List<GMSAttributeWithState>>) {
                Log.e(TAG, "onPeersUserAttributesChanged: $userAttributes ")
            }

            override fun onTokenExpired() {
                Log.e(TAG, "onTokenExpired:")
            }
        })
        val timestamp = System.currentTimeMillis()
        val token =
            createToke("7zVD31N8WqmP0xyE", "1113", timestamp, "e6cd8cec107a4b5abee9cfae52f15457")
        instance.login(LoginParams(token!!, "1113", timestamp), object : ResultCallback<Void> {

            override fun onFailure(errorInfo: ErrorInfo) {
                Log.e(TAG, "login onFailure:")

            }

            override fun onSuccess(responseInfo: Void?) {
                Log.e(TAG, " login onSuccess:")

            }
        })


    }

    fun createToke(appId: String?, userId: String?, timestamp: Long, appkey: String?): String? {
        if (EmptyUtils.isEmpty(appId)) {
            return null
        }
        if (EmptyUtils.isEmpty(userId)) {
            return null
        }
        if (EmptyUtils.isEmpty(appkey)) {
            return null
        }
        val params = StringBuffer("appId=").append(appId).append("&timestamp=").append(timestamp)
            .append("&userId=")
            .append(userId).append(appkey).toString()
        return EncryptUtils.MD5(params)
    }
}