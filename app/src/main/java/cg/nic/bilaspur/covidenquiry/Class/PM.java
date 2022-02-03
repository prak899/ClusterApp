package cg.nic.bilaspur.covidenquiry.Class;
import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Vibrator;

public class PM extends AbstractClass{

    @Override
    public void startSound() {
        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
    }

    @Override
    public String checkInfo() {
        return name;
    }

    @Override
    public void vibeMe() {

    }
}