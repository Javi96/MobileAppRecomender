package appRecomendations.model.observer;


import java.awt.Image;
import java.util.List;



public interface Observer {
	
	void opAppEnd();
	void opAppStart(List<Image> actList);
	void OnUserLogOut();
	void OnListAct(List<Image> l);
}