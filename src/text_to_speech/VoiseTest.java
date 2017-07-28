package text_to_speech;

import java.util.List;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public class VoiseTest {
	public static void main(String[] args) {
		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword("f1f95a06-cbae-4776-8db4-f96561c4ab4f", "LWjx1zbOF3pt");

		// TextToSpeech service = new TextToSpeech("{username}", "{password}");
		
		ServiceCall call = service.getVoices();
		List<Voice> voices = (List<Voice>) call.execute();
		
		for(Voice voice : voices) {
			// System.out.println(voice); // json 형태로 파일 받아옴!!
			System.out.println(voice.getName() + ":" + voice.getDescription());
		}
		
		
	}
}
