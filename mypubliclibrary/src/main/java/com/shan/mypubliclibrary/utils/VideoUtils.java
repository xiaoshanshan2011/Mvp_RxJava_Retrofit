package com.shan.mypubliclibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by chenjunshan on 16-10-13.
 */

public class VideoUtils {
    private VideoView videoView = null;
    private Context context = null;

    public VideoUtils(VideoView videoView, Context context) {
        this.videoView = videoView;
        this.context = context;
        init();
    }

    private void init() {
        videoView.setMediaController(new MediaController(context));

    }

    private void startPlay(Uri uri) {
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();
    }

    private void stopPlay() {
        videoView.stopPlayback();
    }

}
