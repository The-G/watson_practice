package watson.controllers;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.AbstractAction;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import main.controllers.AbstractController;
import main.controllers.ModelAndView;

public class ConversationController extends AbstractController {

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		String url = "https://gateway.watsonplatform.net/conversation/api";
		String username = "e98de019-dc6f-46c7-81cb-1fdd85d81cf2";
		String password = "papkL5VUjcaP";
		String workspace_id = "c2e6e37d-c87b-4417-96ce-df8a1b2c061a";

		Scanner sc = new Scanner(System.in);
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
		service.setUsernameAndPassword(username, password);

		Map<String, Object> context = new HashMap<String, Object>();

		MessageRequest mrequest = null;
		MessageResponse mresponse = null;
		String isay = request.getParameter("iasy").trim();
		Map<String, Object> map = new HashMap<String, Object>();
		
		mrequest = new MessageRequest.Builder().inputText(isay).context(map).build(); // 요청값 집어 넣고
		mresponse = service.message(workspace_id, mrequest).execute(); // 응답 받은 것을 response로 받는다!!
		List<String> watsonsays = mresponse.getText();
		return new ModelAndView("/WEB-INF/views/watsonsay.jsp", "watsonsays", watsonsays);

		
		

	}



}
