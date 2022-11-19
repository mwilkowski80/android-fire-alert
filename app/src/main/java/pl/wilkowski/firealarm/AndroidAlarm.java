package pl.wilkowski.firealarm;

import android.content.Context;
import android.content.ContextWrapper;
import android.media.MediaPlayer;
import android.os.PowerManager;

class AndroidAlarm implements Alarm, OnDestroyListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private final ContextWrapper contextWrapper;
    private final SupportsOnDestroyListeners supportsOnDestroyListeners;
    private MediaPlayer mediaPlayer;

    AndroidAlarm(ContextWrapper contextWrapper, SupportsOnDestroyListeners supportsOnDestroyListeners) {
        this.contextWrapper = contextWrapper;
        this.supportsOnDestroyListeners = supportsOnDestroyListeners;
        supportsOnDestroyListeners.addOnDestroyListener(this);
    }

    private boolean isPlaying() {
        return mediaPlayer != null;
    }

    @Override
    public void start() {
        if (!isPlaying()) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stomp_logo_1);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
            mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            mediaPlayer.setOnCompletionListener(this);
        }
    }

    private Context getApplicationContext() {
        return contextWrapper.getApplicationContext();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        supportsOnDestroyListeners.removeOnDestroyListener(this);
        releaseMediaPlayer();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        releaseMediaPlayer();
    }
}
