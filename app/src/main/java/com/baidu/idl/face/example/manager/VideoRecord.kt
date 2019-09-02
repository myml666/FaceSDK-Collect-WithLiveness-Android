package com.baidu.idl.face.example.manager

import android.hardware.Camera
import android.view.SurfaceHolder

interface VideoRecord {
    /**
     * 返回相机对象
     */
    fun getCamera():Camera

    /**
     * 返回SurfaceHolder对象
     */
    fun getSurfaceHolder():SurfaceHolder
}