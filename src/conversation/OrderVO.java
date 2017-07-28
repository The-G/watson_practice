package conversation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderVO {
	private List<String> mainmenu = new ArrayList<>();
	private List<String> sidemenu = new ArrayList<>();

	public List<String> getMainmenu() {
		return mainmenu;
	}
	public void setMainmenu(List<String> mainmenu) {
		this.mainmenu = mainmenu;
	}
	public List<String> getSidemenu() {
		return sidemenu;
	}
	public void setSidemenu(List<String> sidemenu) {
		this.sidemenu = sidemenu;
	}
	@Override
	public String toString() {
		return "OrderVO [mainmenu=" + mainmenu + ", sidemenu=" + sidemenu + "]";
	}

	

}
