package SpeakAndSpell;
import com.sun.speech.freetts.VoiceManager;

/**
 * Daniel Anderson
 * CS 232   Due: 12/18/2012
 * Final Project
 * Voice.java
 */

public class Voice {

    private String name;		// Name of the voice object
    private com.sun.speech.freetts.Voice systemVoice;  // Gets the voice

    // Default constructor: Builds a voice object from a VoiceManager
    public Voice(String name)
    {
    	System.setProperty("mbrola.base", "mbrola");
        this.name = name;
        this.systemVoice = VoiceManager.getInstance().getVoice(this.name);
        this.systemVoice.setRate(120);
        this.systemVoice.allocate();
    }

    /**
     * Passes a String for the voice to say
     * @param thingToSay
     */
    public void say(String thingToSay)
    {
        this.systemVoice.speak(thingToSay);
    }

    /**
     * Disposes of the voice  
     */
    public void dispose()
    {
        this.systemVoice.deallocate();
    }
    
    // Main Method: Used for testing
    public static void main(String[] args)
    {
    	//System.setProperty("mbrola.base", "C:\\Documents and Settings\\dcanderson\\My Documents\\Dan\\Programming\\mbrola"); 
    	
    	
        Voice voice = new Voice("kevin16");

        String thingToSay = "";  
        thingToSay = "This is a test";

        voice.say(thingToSay);
    }

}

