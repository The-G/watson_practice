package text_to_speech;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class SynthesizeTest {
	public static void main(String[] args) {

		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword("f1f95a06-cbae-4776-8db4-f96561c4ab4f", "LWjx1zbOF3pt");

		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("언어를 선택해 주세요(1:영어, 2:일본어): ");
		int lang_num = reader.nextInt(); // Scans the next token of the input as
											// an int.

		String text = null;
		Scanner str_reader = new Scanner(System.in); // Reading from System.in
		if (lang_num == 1) {
			System.out.println("영어를 선택하셨습니다. 입력해 주세요!!");
			text = str_reader.nextLine();
		} else if (lang_num == 2) {
			System.out.println("일본어를 선택하셨습니다. 입력해 주세요!!");
			text = str_reader.nextLine();
		} else {
			System.out.println("잘못된 선택입니다.");
			return;
		}

		try {
			// String text = "Hello world"; // 값을 받아와서 input 하고 speech로 변환 할 수
			// 있지!!
			// // 일본어도 가능!! voice바꿔 줘야지!!
			// // 언어 선택하게 한 후 할 수도 있겠네!!
			InputStream stream = null;
			InputStream in = null;
			OutputStream out = null;
			if (lang_num == 1) {
				stream = service.synthesize(text, Voice.EN_ALLISON, AudioFormat.WAV).execute();
				in = WaveUtils.reWriteWaveHeader(stream);
				out = new FileOutputStream("english.wav");
			} else if (lang_num == 2) {
				stream = service.synthesize(text, Voice.JA_EMI, AudioFormat.WAV).execute();
				in = WaveUtils.reWriteWaveHeader(stream);
				out = new FileOutputStream("japan.wav");
			}

			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.close();
			in.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
