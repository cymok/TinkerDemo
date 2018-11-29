package cn.mozhx.tinkerdamo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE = 123;

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                installPatch();
            } else {
                Toast.makeText(this, "拒绝了权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 当前是否基准包
                boolean isBaseApk = true;
                if (isBaseApk) {
                    Toast.makeText(MainActivity.this, "我是基准包", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "我是修复包", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检测是否有权限
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //是否可以再次弹出权限对话框 即用户是否选择了不再提示
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQ_CODE);
                    } else {
                        Toast.makeText(MainActivity.this, "拒绝了权限", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    installPatch();
                }
            }
        });

    }

    private void installPatch() {
        String path = getExternalFilesDir("").getAbsolutePath()
                + File.separator + "patch_signed_7zip.apk";
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
    }
}
