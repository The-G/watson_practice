package conversation;

import java.util.Calendar;
import java.util.Scanner;

public class ConversationSenario1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("안녕하세요. 엔원치킨입니다.");
		System.out.println(
			"저희 메뉴는 엔원오리지널, 엔원허니콤보, 엔원레드콤보가 있습니다.");
		System.out.print("어떤 메뉴를 주문하시겠습니까? > ");
		String input = sc.nextLine().trim();
		
		System.out.print("음료 또는 사이드 메뉴를 추가하시겠습니까? > ");
		input = sc.nextLine().trim();
		
		if (input.equals("예")) {
			System.err.print("추가 가능한 음료 및 사이드 메뉴는 ");
			System.out.print("콜라, 사이다, 맥주, 고구마튀김, 샐러드, 구운계란");
			System.out.println("입니다.");
			System.out.print("주문하실 품목을 알려주세요 > ");
			input = sc.nextLine().trim();
		}
		System.out.print("주소를 알려주세요 > ");
		input = sc.nextLine().trim();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 30);
		System.out.println(
			"도착 예상 시간은 " + 
			c.get(Calendar.HOUR_OF_DAY) + "시 " + 
			c.get(Calendar.MINUTE) + "분 입니다.");
		sc.close();
	}
}
