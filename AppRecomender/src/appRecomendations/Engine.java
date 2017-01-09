package appRecomendations;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import jess.Fact;
import jess.JessException;
import jess.QueryResult;
import jess.RU;
import jess.Rete;
import jess.Value;
import jess.ValueVector;
import jess.WorkingMemoryMarker;
import appRecomendations.model.App;
import appRecomendations.model.User;

public class Engine extends Observable {
	private Rete engine;
	private WorkingMemoryMarker marker;
	private DataBase database;

	public Engine(DataBase aDatabase) throws JessException {
		// Create a Jess rule engine
		engine = new Rete();
		engine.reset();

		// Load the pricing rules
		engine.batch("recom.clp");

		// Load the catalog data into working memory
		database = aDatabase;
		engine.addAll(database.getUsers());
		engine.addAll(database.getApps());

		// Mark end of catalog data for later
		// marker = engine.mark();
	}

	/*
	 * private void loadOrderData(int orderNumber) throws JessException { //
	 * Retrive the order from the database Order order =
	 * database.getOrder(orderNumber);
	 * 
	 * if (order != null) { // Add the order and its contents to working memory
	 * engine.add(order); engine.add(order.getCustomer());
	 * engine.addAll(order.getItems()); } }
	 */

	public void run() throws JessException {
		engine.run();
	}

	public void act(User u, App app) {

		try {
			System.out.println("Actualizado1");
			ValueVector vv = new ValueVector();
			vv.add(new Value(u.getName(), RU.STRING));
			vv.add(new Value(app.getCategoryList(), RU.STRING));
			engine.eval("(facts)");
			QueryResult it = engine.runQueryStar("getFav", vv);
			it.next();
			System.out.println("salta2");
			Fact f = new Fact("Like", engine);
			System.out.println("salta3");
			f.setSlotValue("nick", new Value(u.getName(), RU.STRING));
			f.setSlotValue("app", new Value(app.getCategoryList(), RU.STRING));
			f.setSlotValue("fav", new Value(it.getInt("fav"), RU.INTEGER));
			engine.modify(f, "fav", new Value(it.getInt("fav") + 1, RU.INTEGER));
			System.out.println("salta4");
			System.out.println("Actualizado2");
		} catch (JessException e) {

			try {
				System.out.println("Actualizado3");
				Fact nf = new Fact("Like", engine);
				nf.setSlotValue("nick", new Value(u.getName(), RU.STRING));
				nf.setSlotValue("app", new Value(app.getCategoryList(),
						RU.STRING));
				nf.setSlotValue("fav", new Value(1, RU.INTEGER));
				engine.assertFact(nf);
				System.out.println("Actualizado4");
			} catch (JessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public ArrayList<App> getRecom(User user) {
		ArrayList<App> apps = new ArrayList<App>();
		try {
			QueryResult result = engine
					.runQueryStar("favoritos", new ValueVector().add(new Value(
							user.getName(), RU.STRING)));
		/*	while (result.next()) {
				System.out.println(result.getString("nick") + " "
						+ result.getString("name"));
			}*/
			while (result.next()) {
				
				QueryResult qr = engine.runQueryStar("appFinder",
						new ValueVector().add(new Value(result.getString("name"), RU.STRING)));
				qr.next();
				if(Math.random()  < Math.log(result.getFloat("fav") + 1)){
					apps.add(new App(qr.getString("name"), qr.getFloat("prize"), qr
						.getString("categoryList")));
				}
			}
			return apps;

		} catch (JessException e) {
			e.printStackTrace();
		}
		return null;

	}

	// ------------- FROM MODEL -----------------

	public static int MAX_WIDTH = 80;

	public static int MAX_HEIGHT = 80;

	private static String[] path = { "Clash of Clans", "Clash Royale",
			"Hay Day", "Linkedln", "Twitter", "Whatsapp" };

	private ArrayList<ImageIcon> imageList;

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

	private void createImages() {
		imageList = new ArrayList<ImageIcon>();
		for (int i = 0; i < path.length; i++) {
			imageList.add(this
					.resizeImage("src/appRecomendations/model/images/"
							+ path[i] + ".png"));
		}

	}

	public String getImageId(int i) {
		return path[i];
	}
}
