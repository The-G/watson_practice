package watson.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.Entity;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class WatsonCon {
	private static WatsonCon watson = new WatsonCon();

	public static WatsonCon getInstance() {
		return watson;
	}

	private WatsonCon() {
		price.put(4500, Arrays.asList("ham", "tuna", "seefood", "veggie", "egg mayo"));
		price.put(4900, Arrays.asList("blt"));
		price.put(5300, Arrays.asList("spicy italian", "chikenteriyaki", "turkey bacon", "subwayclub"));
		price.put(5800, Arrays.asList("roastbeef", "subwaymelt", "steak and cheese"));
	}

	private Map<String, Object> OrderDetail = new HashMap<String, Object>();
	private List<String> vegeList = new ArrayList<String>(
			Arrays.asList("LETTUCE", "TOMATOES", "CUCUMBERS", "PEPPERS", "ONIONS", "PICKLES", "OLIVES", "JALAPENOS"));
	private Map<Integer, List> price = new HashMap<Integer, List>();

	public static void main(String[] args) {
		WatsonCon watson = WatsonCon.getInstance();
		System.out.println(watson.vegeList.toString());

		BufferedReader br = null;
		MessageResponse response = null;
		Map<String, Object> context = new HashMap<String, Object>();

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println("Enter Something : ");
				String input = br.readLine();

				if ("q".equals(input)) {

					for (String key : watson.OrderDetail.keySet()) {
						System.out.println(key + " : " + watson.OrderDetail.get(key));
					}

					System.out.println("Exit");
					System.exit(0);
				}

				response = watson.conversationAPI(input, context);
				if (response != null) {
					System.out.println(response.toString());
					System.out.println("Watson Response: " + response.getText());

					if ("[DONE]".equals(response.getText())) {

						for (String key : watson.OrderDetail.keySet()) {
							System.out.println(key + " : " + watson.OrderDetail.get(key));
						}
						System.out.println("Order END");
					}
				}

				context = response.getContext();
				JSONObject jsonObject = new JSONObject(context);
				System.out.println(context);

				JSONObject system = (JSONObject) jsonObject.get("system");
				JSONArray dialog_stack = (JSONArray) system.get("dialog_stack");
				JSONObject dialog_node = (JSONObject) dialog_stack.getJSONObject(0);
				String Intend = dialog_node.getString("dialog_node");

				System.out.println("Intend : " + Intend);

				if ("OrderStart".equals(Intend)) {
					System.out.println("order start");

				} else if ("Menu".equals(Intend)) {
					List<Entity> entity = response.getEntities();
					System.out.println("getEntities " + entity.size());

					for (int i = 0; i < entity.size(); i++) {
						Entity e = entity.get(i);
						if (e.getEntity().equals("menu")) {
							System.out.println("Order menu: " + e.getValue());
							watson.OrderDetail.put(e.getEntity(), e.getValue().toUpperCase());

							for (Integer key : watson.price.keySet()) {
								List<String> menuTmp = watson.price.get(key);
								System.out.println(menuTmp);
								if (menuTmp.contains(e.getValue().toLowerCase())) {
									watson.OrderDetail.put("price", key);
									System.out.println("price : " + key);
									break;
								}
							}
						}
					}

				} else if ("Bread".equals(Intend)) {
					List<Entity> entity = response.getEntities();
					System.out.println("getEntities " + entity.size());

					for (int i = 0; i < entity.size(); i++) {
						Entity e = entity.get(i);
						if (e.getEntity().equals("bread")) {
							System.out.println("Order bread: " + e.getValue());
							watson.OrderDetail.put(e.getEntity(), e.getValue().toUpperCase());
						}
					}

				} else if ("Vege".equals(Intend)) {
					List<Entity> entity = response.getEntities();
					System.out.println("getEntities " + entity.size());

					for (int i = 0; i < entity.size(); i++) {
						Entity e = entity.get(i);
						if (e.getEntity().equals("vegetable")) {
							System.out.println("Order vegetable: " + e.getValue());
							watson.vegeList.remove(e.getValue());
							int vegeLen = watson.vegeList.toString().length() - 1;
							String veges = watson.vegeList.toString().substring(1, vegeLen);
							watson.OrderDetail.put(e.getEntity(), veges);
						}
					}

				} else if ("Source".equals(Intend)) {
					List<Entity> entity = response.getEntities();
					System.out.println("getEntities " + entity.size());

					for (int i = 0; i < entity.size(); i++) {
						Entity e = entity.get(i);
						if (e.getEntity().equals("source")) {
							System.out.println("Order source: " + e.getValue());
							watson.OrderDetail.put(e.getEntity(), e.getValue().toUpperCase());
						}
					}

				} else if ("Cookie".equals(Intend)) {
					List<Entity> entity = response.getEntities();
					System.out.println("getEntities " + entity.size());

					for (int i = 0; i < entity.size(); i++) {
						Entity e = entity.get(i);
						if (e.getEntity().equals("cookie")) {
							System.out.println("Order cookie: " + e.getValue());
							watson.OrderDetail.put(e.getEntity(), e.getValue().toUpperCase());

							Integer priceTmp = (Integer) watson.OrderDetail.get("price");
							watson.OrderDetail.replace("price", priceTmp + 1000);
						}
					}

				} else if ("Drink".equals(Intend)||"DONE".equals(Intend)) {
					List<Entity> entity = response.getEntities();
					System.out.println("getEntities " + entity.size());

					for (int i = 0; i < entity.size(); i++) {
						Entity e = entity.get(i);
						if (e.getEntity().equals("drink")) {
							System.out.println("Order drink: " + e.getValue());
							watson.OrderDetail.put(e.getEntity(), e.getValue().toUpperCase());

							Integer priceTmp = (Integer) watson.OrderDetail.get("price");
							watson.OrderDetail.replace("price", priceTmp + 1500);
						}
					}

				}

				System.out.println("-----------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public MessageResponse conversationAPI(String input, Map<String, Object> context) {
		ConversationService service = new ConversationService("2017-07-20");//ConversationService.VERSION_DATE_2017_02_03
		service.setUsernameAndPassword("5d0ec291-ca1e-4b0c-a835-c613ddefff42", "0tnmaxO6Po6h");
		MessageRequest newMessage = new MessageRequest.Builder().inputText(input).context(context).build();
		String workspaceId = "ddfbe03a-a2ee-461a-96d1-58c88fb9ac95";
		MessageResponse response = service.message(workspaceId, newMessage).execute();
		return response;
	}

}