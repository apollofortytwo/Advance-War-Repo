import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
//"Attacking_Sound.wav"

public class Audio{

	private Clip audio;
	


	public Audio(String path){
		try {
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(Application.class
							.getResource(path));
			audio = AudioSystem.getClip();
			audio.open(audioIn);

			FloatControl gainControl = (FloatControl) audio
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-25.0f);
		} catch (Exception e) {
		}
	}
	
	public Clip getAudio() {
		return audio;
	}

	public void setAudio(Clip audio) {
		this.audio = audio;
	}
	
	
	
}
