package com.baidu.idl.face.example;

import android.app.Application;

import com.baidu.idl.face.platform.LivenessTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class ExampleApplication extends Application {

    public static List<LivenessTypeEnum> livenessList = new ArrayList<LivenessTypeEnum>();
    public static boolean isLivenessRandom = false;

}
