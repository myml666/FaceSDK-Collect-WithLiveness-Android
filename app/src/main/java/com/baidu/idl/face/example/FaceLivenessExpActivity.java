package com.baidu.idl.face.example;

import android.content.DialogInterface;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;

import com.baidu.idl.face.example.manager.VideoRecord;
import com.baidu.idl.face.example.widget.DefaultDialog;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.ui.FaceLivenessActivity;
import com.itfitness.videodemo.manager.VideoRecordManager;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class FaceLivenessExpActivity extends FaceLivenessActivity implements VideoRecord {

    private DefaultDialog mDefaultDialog;
    private VideoRecordManager videoRecordManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLivenessCompletion(FaceStatusEnum status, String message, HashMap<String, String> base64ImageMap) {
        super.onLivenessCompletion(status, message, base64ImageMap);
        if (status == FaceStatusEnum.OK && mIsCompletion) {
            if(videoRecordManager!=null){
                videoRecordManager.stop();
                videoRecordManager.setUnlock(false);
            }
            showMessageDialog("活体检测", "检测成功");
        } else if (status == FaceStatusEnum.Error_DetectTimeout ||
                status == FaceStatusEnum.Error_LivenessTimeout ||
                status == FaceStatusEnum.Error_Timeout) {
            if(videoRecordManager!=null){
                videoRecordManager.stop();
                videoRecordManager.setUnlock(false);
            }
            showMessageDialog("活体检测", "采集超时");
        }else if(status == FaceStatusEnum.Liveness_Eye ){
            if(videoRecordManager!=null){
                videoRecordManager.stop();
                videoRecordManager.setUnlock(false);
            }
            if(videoRecordManager==null){
                videoRecordManager = new VideoRecordManager(this);
            }
            videoRecordManager.start();
        }
    }

    private void showMessageDialog(String title, String message) {
        if (mDefaultDialog == null) {
            DefaultDialog.Builder builder = new DefaultDialog.Builder(this);
            builder.setTitle(title).
                    setMessage(message).
                    setNegativeButton("确认",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mDefaultDialog.dismiss();
                                    finish();
                                }
                            });
            mDefaultDialog = builder.create();
            mDefaultDialog.setCancelable(true);
        }
        mDefaultDialog.dismiss();
        mDefaultDialog.show();
    }
    public void finish() {
        super.finish();
    }

    @Override
    public Camera getCamera() {
        return mCamera;
    }

    @Override
    public SurfaceHolder getSurfaceHolder() {
        return mSurfaceHolder;
    }
}
