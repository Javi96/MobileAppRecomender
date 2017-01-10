package appRecomender;
import jess.JessException;
import appRecomender.controller.Controller;
import appRecomender.view.View;

public class Main {

	public static void main(String[] args) {
		try {
			new Controller(new Engine(new DataBase()), new View());
		} catch (JessException e) {
			e.printStackTrace();
		}

	}
}
