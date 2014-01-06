package com.jbrown.core.util;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeechUtil {
	public static void main(String[] args) throws Exception{
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
		
		System.out.println("Done, now list all the voices:");
		
		 VoiceManager voiceManager;
	     voiceManager = VoiceManager.getInstance();
	     // Get all available voices
	     Voice[] voices = voiceManager.getVoices();
	     for (int i = 0; i < voices.length; i++) {
	       System.out.println(voices[i].getName());
	     }
	     
	     System.out.println("Done, now read file:");
	     
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
	            
	     //allocate the resources for the voice
	     voice.allocate();
	 
	     // Create input stream from file
	     java.io.InputStream in = new java.io.FileInputStream(new java.io.File("c:/test.java"));
	     voice.speak(in);
	     //voice.dump(output, pad, title); look into it later to save the file
	     voice.deallocate();
	     
	}
}