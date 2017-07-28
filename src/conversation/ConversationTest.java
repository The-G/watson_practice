package conversation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ConversationTest {
	public static void main(String[] args) {

		String url = "https://gateway.watsonplatform.net/conversation/api";
		String username = "e98de019-dc6f-46c7-81cb-1fdd85d81cf2";
		String password = "papkL5VUjcaP";
		String workspace_id = "c2e6e37d-c87b-4417-96ce-df8a1b2c061a";

		Scanner sc = new Scanner(System.in);
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
		service.setUsernameAndPassword(username, password);

		// InputData input = new InputData.Builder("Hi").build();
		// MessageOptions options = new
		// MessageOptions.Builder(workspaceId).input(input).build();
		// MessageResponse response = service.message(options).execute();
		// System.out.println(response);

		Map<String, Object> context = new HashMap<String, Object>();

		MessageRequest request = null;
		MessageResponse response = null;
		String msg = "";
		Map<String, Object> map = new HashMap<String, Object>();

		for (;;) {
			request = new MessageRequest.Builder().inputText(msg).context(map).build(); // 요청값 집어 넣고
//			System.out.println(request);
			response = service.message(workspace_id, request).execute(); // 응답 받은 것을 response로 받는다!!
			List<String> watsonsays = response.getText();
			for(String say:watsonsays) {
				System.out.println("Watson : " + say);
			}
			System.out.print("I : "); msg = sc.nextLine(); //buffer 단위로 받는 것이 좋다!! 띄어쓰기 해도 받음?!
		}
	}
}
