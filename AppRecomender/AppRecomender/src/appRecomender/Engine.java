package appRecomender;

import java.util.ArrayList;
import java.util.Observable;

import jess.Fact;
import jess.JessException;
import jess.QueryResult;
import jess.RU;
import jess.Rete;
import jess.Value;
import jess.ValueVector;
import appRecomender.model.App;
import appRecomender.model.User;

public class Engine extends Observable {
	private Rete engine;
	private DataBase database;

	public Engine(DataBase aDatabase) throws JessException {
		engine = new Rete();
		engine.reset();
		engine.batch("recom.clp");
		database = aDatabase;
		engine.addAll(database.getUsers());
		engine.addAll(database.getApps());
	}

	public void run() throws JessException {
		engine.run();
	}

	public void act(User u, App app) {
		try {
			ValueVector vv = new ValueVector();
			vv.add(new Value(u.getName(), RU.STRING));
			vv.add(new Value(app.getCategoryList(), RU.STRING));
			QueryResult it = engine.runQueryStar("getFav", vv);
			it.next();
			Fact f = new Fact("Like", engine);
			f.setSlotValue("nick", new Value(u.getName(), RU.STRING));
			f.setSlotValue("app", new Value(app.getCategoryList(), RU.STRING));
			f.setSlotValue("fav", new Value(it.getInt("fav"), RU.INTEGER));
			engine.modify(f, "fav", new Value(it.getInt("fav") + 1, RU.INTEGER));
		} catch (JessException e) {

			try {
				Fact nf = new Fact("Like", engine);
				nf.setSlotValue("nick", new Value(u.getName(), RU.STRING));
				nf.setSlotValue("app", new Value(app.getCategoryList(),
						RU.STRING));
				nf.setSlotValue("fav", new Value(1, RU.INTEGER));
				engine.assertFact(nf);
			} catch (JessException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void getRecom(User user) {
		ArrayList<App> apps = new ArrayList<App>();
		try {
			QueryResult result = engine
					.runQueryStar("favoritos", new ValueVector().add(new Value(
							user.getName(), RU.STRING)));
			while (result.next()) {

				QueryResult qr = engine.runQueryStar("appFinder",
						new ValueVector().add(new Value(result
								.getString("name"), RU.STRING)));
				qr.next();
				if (Math.random() * 4 < Math.log(result.getFloat("fav") + 1)) {
					apps.add(new App(qr.getString("name"),
							qr.getFloat("prize"), qr.getString("categoryList")));
				}

				/*QueryResult qr = engine.runQueryStar("appFinder",
						new ValueVector().add(new Value(result
								.getString("name"), RU.STRING)));
				qr.next();

				QueryResult qrUser = engine.runQueryStar("findUser",
						new ValueVector().add(new Value(user.getName(),
								RU.STRING)));
				qrUser.next();
				if (qr.getFloat("prize") <= qrUser.getFloat("ecLvl")
						|| Math.random() < 0.05) {
					if (Math.random() < Math.log(result.getFloat("fav") + 1)) {
						apps.add(new App(qr.getString("name"), qr
								.getFloat("prize"), qr
								.getString("categoryList")));
					}
				}
				apps.add(new App(qr.getString("name"), qr.getFloat("prize"), qr
						.getString("categoryList")));*/
			}

			setChanged();
			notifyObservers(apps);
		} catch (JessException e) {
			e.printStackTrace();
		}
	}

	public User newUser(User user) {
		try {
			if (!engine
					.runQueryStar(
							"findUser",
							new ValueVector().add(new Value(user.getName(),
									RU.STRING))).next()) {
				ArrayList<User> u = new ArrayList<User>();
				u.add(user);
				engine.addAll(u);
				engine.run();
				return user;
			}
		} catch (JessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User changeUser(User user) {
		try {

			QueryResult qr = engine
					.runQueryStar("findUser", new ValueVector().add(new Value(
							user.getName(), RU.STRING)));
			if (qr.next()) {
				return new User(qr.getString("name"), 0, 'm', "");
			}
		} catch (JessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<App> recomByPrize(User user) {
		ArrayList<App> apps = new ArrayList<App>();
		ValueVector vv = new ValueVector();
		try {
			vv.add(new Value(user.getName(), RU.STRING));
			vv.add(new Value(user.getEcLvl(), RU.FLOAT));
			QueryResult result = engine.runQueryStar("findByPrize", vv);
			while (result.next()) {
				apps.add(new App(result.getString("appName"), result
						.getFloat("prize"), result.getString("app")));
			}
		} catch (JessException e) {
			e.printStackTrace();
		}
		return apps;
	}
}
