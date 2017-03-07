package com.example.lahm.dailytask.File;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lahm.dailytask.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class FileActivity extends AppCompatActivity {
    private static int PERMISSION_STROGE_CODE = 666;
    private String TAG = "xxxxxxxxx";
    private TextView result;
    private StringBuffer sb = new StringBuffer();
    private List<SearchModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        result = (TextView) findViewById(R.id.result);
        final EditText target = (EditText) findViewById(R.id.target);

        Button find = (Button) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(target.getText().toString().trim())) {
                    showToast("no target file name");
                    return;
                }
                if (!checkSysPermission(WRITE_EXTERNAL_STORAGE, PERMISSION_STROGE_CODE))
                    return;//无权限被拒
                list.clear();
                sb.delete(0, sb.length());
                result.setText("waiting...");
                new SearchTask(FileActivity.this).execute(target.getText().toString().trim());
            }
        });
    }

    //Cannot execute task: the task has already been executed
    // (a task can be executed only once)
    // so i have to new a new AsyncTask without consider memory-using
    /**
     * 多线程查找file
     * 1.先判断权限
     *
     * @param targetFileName
     */
    private void findTargetFile(String targetFileName) {
        Log.i(TAG, "findTargetFile: " + Environment.MEDIA_MOUNTED);
        Log.i(TAG, "findTargetFile: " + Environment.getExternalStorageState());
        Log.i(TAG, "1: " + Environment.getRootDirectory().getName());
        Log.i(TAG, "2: " + Environment.getRootDirectory().getPath());
        Log.i(TAG, "3: " + Environment.getRootDirectory().getAbsolutePath());
        Log.i(TAG, "4: " + Environment.getExternalStorageDirectory().getName());
        Log.i(TAG, "5: " + Environment.getExternalStorageDirectory().getPath());
        Log.i(TAG, "6: " + Environment.getExternalStorageDirectory().getAbsolutePath());

        Debug.startMethodTracing();
        checkFile(targetFileName, Environment.getExternalStorageDirectory().getPath());
        Debug.stopMethodTracing();

    }

    /**
     * 算法可优化
     * 这里是深度优先
     *
     * @param targetFileName
     * @param filePath
     */
    private void checkFile(String targetFileName, String filePath) {
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        for (int i = 0, len = fileList.length; i < len; i++) {
            if (fileList[i].isDirectory() && !fileList[i].getPath().contains("/.")) {
                //文件夹进入下一级，忽略.文件和.文件夹
                checkFile(targetFileName, fileList[i].getPath());
            } else {
                int dot = fileList[i].getName().lastIndexOf('.');
                if ((dot > -1) && (dot < (fileList[i].getName().length()))) {
                    if (targetFileName.equals(fileList[i].getName().substring(0, dot))) {
                        list.add(SearchModel.formatData(fileList[i].getName(), fileList[i].getPath()));
                    }
                }
            }
        }
    }

    private boolean checkSysPermission(String permission, int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {//6.0以前无需check
            return true;
        }
        if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
            return true;//已经允许了的无需check
        }
        //1.没有申请这个权限，false，也就是为什么要先完成上方两个判断；
        //2.拒绝给权限，但是没有勾选『不在提示』，true,此时要给个更见完整的说明，最好用dialog
        //3.拒绝给权限，而且勾选了『不在提示』，false
        if (shouldShowRequestPermissionRationale(permission)) {
            showToast("need permission");//->  这个提示必须要更加明显，toast不够，
            // 最好是dialog，然后跳设置，手动开权限
            requestPermissions(new String[]{permission}, requestCode);
        } else {
            requestPermissions(new String[]{permission}, requestCode);
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //requestPermissions的回调
        if (requestCode == PERMISSION_STROGE_CODE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //第一次询问读写权限，且允许,就直接做事情，这里只showToast
                showToast("request permission success");
            } else {
                //被拒绝了
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && !shouldShowRequestPermissionRationale(permissions[0])) {
                    //而且勾选了不再提示的，我在这里跳转到设置界面，手动打开权限，这个步骤在前面做最好
                    jump2settings();
                } else {
                    showToast(" request permission failed");
                }
            }
        }
    }

    private void jump2settings() {// show a dialog for user to set permission by self
        showToast("jump2setting set permission by self pls");
        Uri packageURI = Uri.parse("package:" + getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        startActivity(intent);
    }

    private void showToast(String msg) {
        Toast.makeText(FileActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private class SearchTask extends AsyncTask<String, Integer, List<SearchModel>> {
        private volatile boolean running = true;

        ProgressDialog pdialog;

        public SearchTask(Context context) {
            pdialog = new ProgressDialog(context, 0);
            pdialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();//  设置一个可见的取消按钮
                }
            });
            pdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {//监听了返回键
                    cancel(true);
                }
            });
            pdialog.setCancelable(true);
            pdialog.setMax(100);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog.show();
        }

        @Override
        protected List<SearchModel> doInBackground(String... params) {
            while (running) {
                findTargetFile(params[0]);
                return list;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<SearchModel> list) {
            super.onPostExecute(list);
            pdialog.dismiss();
            if (list.isEmpty()) {
                result.setText("find nothing");
            } else {
                sb.append("本次共搜索到： ").append(list.size()).append("个结果\n");
                for (int i = 0, len = list.size(); i < len; i++) {
                    sb.append(list.get(i).getFileName())
                            .append("@")
                            .append(list.get(i).getFilePath())
                            .append("\n");
                }
                result.setText(sb.toString());
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(TAG, "onCancelled: ");
            running = false;
        }
    }
}
