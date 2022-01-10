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
    }

}