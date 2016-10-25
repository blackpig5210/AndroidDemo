package com.blackpig.www.permissiondemofor6;

import android.Manifest;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by black on 2016/10/25/0025.
 */
@RuntimePermissions
public class PermissionsDispatcherActivity extends AppCompatActivity {
    @BindView(R.id.tv_permission_status)
    TextView tvPermissionStatus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_request_permission)
    public void OnClick() {
        requestPremission();
    }

    private void requestPremission() {
        PermissionsDispatcherActivityPermissionsDispatcher.openCameraWithCheck(this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void openCamera() {
        tvPermissionStatus.setTextColor(Color.GREEN);
        tvPermissionStatus.setText("相机权限已申请");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsDispatcherActivityPermissionsDispatcher.onRequestPermissionsResult(this,
                requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("申请相机权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //再次执行请求
                        request.proceed();
                    }
                })
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void premissionDenied() {
        Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void neverAskAgain() {
        Toast.makeText(this, "不再询问", Toast.LENGTH_SHORT).show();
    }
}
