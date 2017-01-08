package appRecomendations;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import appRecomendations.model.App;
import appRecomendations.model.Profiles;
import appRecomendations.model.User;
import appRecomendations.model.observer.Observer;

/**
 * A toy implementation of the Database interface with some hard-coded order
 * data.
 */

public class DataBase {

	// ------------- FROM MODEL -----------------

	public static int MAX_WIDTH = 80;

	public static int MAX_HEIGHT = 80;

	private static String[] path = { "Clash of Clans", "Clash Royale",
			"Hay Day", "Linkedln", "Twitter", "Whatsapp" };

	private ArrayList<Observer> observerList;

	private ArrayList<ImageIcon> imageList;

	public void addObserver(Observer o) {
		observerList.add(o);
	}

	public static BufferedImage loadImage(String pathName) {
		BufferedImage bimage = null;
		try {
			bimage = ImageIO.read(new File(pathName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bimage;
	}

	public static BufferedImage resize(BufferedImage bufferedImage, int newW,
			int newH) {
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		BufferedImage bufim = new BufferedImage(newW, newH,
				bufferedImage.getType());
		Graphics2D g = bufim.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return bufim;
	}

	public ImageIcon resizeImage(String filePath) {
		BufferedImage bimage = loadImage(filePath);
		if (bimage.getHeight() > bimage.getWidth()) {
			int heigt = (bimage.getHeight() * MAX_WIDTH) / bimage.getWidth();
			bimage = resize(bimage, MAX_WIDTH, heigt);
			int width = (bimage.getWidth() * MAX_HEIGHT) / bimage.getHeight();
			bimage = resize(bimage, width, MAX_HEIGHT);
		} else {
			int width = (bimage.getWidth() * MAX_HEIGHT) / bimage.getHeight();
			bimage = resize(bimage, width, MAX_HEIGHT);
			int heigt = (bimage.getHeight() * MAX_WIDTH) / bimage.getWidth();
			bimage = resize(bimage, MAX_WIDTH, heigt);
		}
		return new ImageIcon(bimage);
		// saveImage(bimage, copyPath);
	}

	public ArrayList<ImageIcon> getImageIconList() {
		return this.imageList;
	}

	public void start() {
		/*
		 * this.actList = itemConnection.getNItems(20); for(AppObserver o :
		 * this.observerList){ o.opAppStart(this.actList); }
		 */

	}

	// ------------------------------------------
	private ArrayList<User> users;
	private ArrayList<App> apps;
	private Profiles profiles;

	public DataBase() {
		createUsers();
		createApps();
		// createProfiles();

		// ---------- FROM MODEL ------------------
		this.observerList = new ArrayList<Observer>();
		createImages();
		// ----------------------------------------

	}

	/*
	 * private void createApps() { apps = new ArrayList(); apps.add(new
	 * App("transistor", (float)17.00, new String[] {"Romantic"})); apps.add(new
	 * App("Minecraft", (float)5.00, new String[] {"Game", "OST", "Sand box"}));
	 * apps.add(new App("Clash royale", (float)0.00, new String[] {"Game",
	 * "OST", "Pay to win++"})); apps.add(new App("Clash of clans", (float)0.00,
	 * new String[] {"Game", "OST", "Pay to win"})); apps.add(new
	 * App("Mario run", (float)10.00, new String[] {"Game", "OST",
	 * "Lateral Scroll"})); apps.add(new App("Nisekoi", (float)1.00, new
	 * String[] {"Manga", "Romantic", "Comedy"})); apps.add(new
	 * App("Boom beach", (float)0.00, new String[] {"Game", "OST",
	 * "pay to win"})); apps.add(new App("Pokemon Go", (float)1.00, new String[]
	 * {"Game", "OST", "Walk"})); apps.add(new App("GPS", (float)1.00, new
	 * String[] {"Functionality", "gadget"}));
	 * 
	 * 
	 * }
	 */

	private void createImages() {
		imageList = new ArrayList<ImageIcon>();
		for (int i = 0; i < path.length; i++) {
			imageList.add(this.resizeImage("src/appRecomendations/model/images/"
					+ path[i] + ".png"));
		}

	}
	
	public String getImageId(int i){
		return path[i];
	}

	private void createApps() {
		apps = new ArrayList<App>();
		apps.add(new App("pruebaFunc", (float) 10.00, "Vocaloid", 0));
		apps.add(new App("Clash_Royale", (float) 0.00, "Action", 1.1f));
		apps.add(new App("Clash of Clans", (float) 0.00, "Action", 1.7f));
		apps.add(new App("Hay_Day", (float) 0.00, "Action", 0.5f));
		apps.add(new App("Linkedln", (float) 0.00, "RRSS", 0.2f));
		apps.add(new App("Twitter", (float) 0.00, "RRSS", 0.23f));
		apps.add(new App("Whatsapp", (float) 0.00, "RRSS", 0.15f));
	}
	
	public App getApp(int i){
		return apps.get(i);
	}

	private void createUsers() {
		users = new ArrayList<User>();
		users.add(new User("Pedro", 1995, 'm', "Spain"));
	}

	private void createProfiles() {
		profiles = new Profiles();

		ArrayList likeR = new ArrayList<String>();
		likeR.add("Game");
		likeR.add("shooter");
		likeR.add("sandbox");
		profiles.put((String) "RatKid", likeR);

		ArrayList likeO = new ArrayList<String>();
		likeO.add("Game");
		likeO.add("Vocaloid");
		likeO.add("Manga");
		profiles.put((String) "Otaku", likeO);

	}

	public Collection getUsers() {
		return users;
	}

	public Collection getApps() {
		return apps;
	}

	public Profiles getProfiles() {
		return profiles;
	}
}
