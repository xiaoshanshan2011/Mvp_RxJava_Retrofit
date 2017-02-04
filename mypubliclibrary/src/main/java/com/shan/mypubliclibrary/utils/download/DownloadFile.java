package com.shan.mypubliclibrary.utils.download;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by chenjunshan on 17-1-18.
 */

public abstract class DownloadFile {
    private Context mContext;
    private boolean isBindService;

    public DownloadFile(Context context) {
        mContext = context;
    }

    public void start(String url) {
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, url);
        isBindService = mContext.bindService(intent, conn, BIND_AUTO_CREATE);
    }

    protected abstract void onProgress(float fraction);

    protected abstract void onFinish();

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            DownloadService downloadService = binder.getService();

            //接口回调，下载进度
            downloadService.setOnProgressListener(new DownloadService.OnProgressListener() {
                @Override
                public void onProgres(float fraction) {
                    onProgress(fraction);
                    //bnp.setProgress((int)(fraction * 100));

                    //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
                    if (fraction == DownloadService.UNBIND_SERVICE && isBindService) {
                        mContext.unbindService(conn);
                        isBindService = false;
                        onFinish();
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
