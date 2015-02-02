package com.jbrown.core.util;

import java.io.PrintWriter;
import java.util.Locale;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

public class SpeechUtil {

	public SpeechUtil() {
	}

	public static void main(String[] args) throws Exception {
		new SpeechUtil().speak3();

	}

	public void speak2() throws Exception {
		try {
			System.setProperty("freetts.voices",
					"com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

			Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
			Synthesizer synthesizer = Central
					.createSynthesizer(new SynthesizerModeDesc(Locale.US));
			synthesizer.allocate();
			synthesizer.resume();
			synthesizer.speakPlainText("Can you hear me now?", null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

			synthesizer.deallocate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void speak3(){
		FreeTTS freetts;
	       AudioPlayer audioPlayer = null;
	            String voiceName = "kevin16";
  
	            VoiceManager voiceManager = VoiceManager.getInstance();
	            Voice helloVoice = voiceManager.getVoice(voiceName);

	            if (helloVoice == null) {
	                System.err.println(
	                    "Cannot find a voice named "
	                    + voiceName + ".  Please specify a different voice.");
	                System.exit(1);
	            }
 
	            helloVoice.allocate();
 
	           audioPlayer = new SingleFileAudioPlayer("C:/test/test",Type.WAVE);
	           
	           helloVoice.setAudioPlayer(audioPlayer);
//helloVoice.dump(new PrintWriter(arg0, arg1), pad, title);


	            helloVoice.speak("Thank you for giving me a voice. "
	                             + "I'm so glad to say hello to this world.");
 
	            helloVoice.deallocate();
	 
	            audioPlayer.close();
	}
	
	public void speak1() throws Exception {
		Voice voice;
		FreeTTS freetts;

		{
			voice = VoiceManager.getInstance().getVoice("kevin16");
			
			if (voice != null) {
				voice.allocate();
			}
			
			freetts = new FreeTTS(voice);
			voice.speak("hello,world");

			freetts.startup();
			// freetts.urlToSpeech("http://10.0.0.109:8080/");
			freetts.setAudioFile("c:\\test\\test.wav");
		 
			freetts.shutdown();
		}
	}

	public void speak() throws Exception {
		VoiceManager voiceManager = VoiceManager.getInstance();

		// Get all available voices
		Voice[] voices = voiceManager.getVoices();
		for (int i = 0; i < voices.length; i++) {
			System.out.println(voices[i].getName());
		}

		String voiceName = "kevin16";
		voiceManager = null;
		Voice voice = null;

		voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice(voiceName);

		voice.setPitch((float) 4.00);
		voice.setPitchShift((float) .005);
		voice.setPitchRange((float) 0.01);
		// "business", "casual", "robotic", "breathy"
		voice.setStyle("business");

		// allocate the resources for the voice
		voice.allocate();

		// Create input stream from file
		java.io.InputStream in = new java.io.FileInputStream(
				new java.io.File(
						"C:/git-repo/jbrownservices/src/com/jbrown/core/util/Pair.java"));
		voice.speak(in);

		// voice.dump(output, pad, title); look into it later to save the file
		voice.deallocate();

	}

}