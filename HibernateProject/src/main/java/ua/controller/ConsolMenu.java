package ua.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import ua.entity.Cafe;
import ua.entity.Cuisine;
import ua.entity.Ingredient;
import ua.entity.Meal;
import ua.entity.OpenClose;
import ua.entity.Order;
import ua.entity.Table;
import ua.entity.Type;
import ua.model.view.CafeView;
import ua.model.view.MealView;

public class ConsolMenu {
	private EntityManager em;
	private Scanner cin;
	private String[] defaultIngredient = { "sugar", "salt", "potato", "beet", "avocado", "pepper", "cabbage", "carrot",
			"chili", "corn", "eggplant", "garlic", "olive", "onion", "radish", "tomato", "pea", "cucumber", "apple",
			"banana", "blackberry", "bluberry", "cherry", "raspberry", "cranberry", "strawberry", "kivi", "lemon",
			"gooseberry", "orange", "mandarin", "mango", "peach", "pear", "pinapple", "pomegaranate", "watermelon" };
	private String[] defaultCuisine = { "Indonesian", "Mexican ", "Chinese", "Italian", "Spanish", "French", "Japanese",
			"Turkey", "Indian", "Thai" };

	public ConsolMenu(EntityManager em, Scanner cin) {
		super();
		this.em = em;
		this.cin = cin;
	}

	// --------------------Delete--------------------//
	public void deleteAll() {
		deleteAllCafe();
		deleteAllCuisine();
		deleteAllTime();
		deleteAllIngredient();
		deleteAllTable();
		deleteAllMeal();
		deleteAllOrder();
	}

	public void deleteAllCafe() {
		List<Cafe> list = em.createQuery("FROM Cafe", Cafe.class).getResultList();
		for (Cafe cafe : list) {
			Cafe cf = em.find(Cafe.class, cafe.getId());
			em.remove(cf);
		}
	}

	public void deleteAllCuisine() {
		List<Cuisine> list = em.createQuery("FROM Cuisine", Cuisine.class).getResultList();
		for (Cuisine cuisine : list) {
			Cuisine cuis = em.find(Cuisine.class, cuisine.getId());
			em.remove(cuis);
		}
	}

	public void deleteAllTime() {
		List<OpenClose> list = em.createQuery("FROM OpenClose", OpenClose.class).getResultList();
		for (OpenClose openClose : list) {
			OpenClose time = em.find(OpenClose.class, openClose.getId());
			em.remove(time);
		}
	}

	public void deleteAllIngredient() {
		List<Ingredient> list = em.createQuery("FROM Ingredient", Ingredient.class).getResultList();
		for (Ingredient ingredient : list) {
			Ingredient ing = em.find(Ingredient.class, ingredient.getId());
			em.remove(ing);
		}
	}

	// *
	public void deleteAllTable() {
		List<Table> list = em.createQuery("FROM Table", Table.class).getResultList();
		for (Table table : list) {
			Table tbl = em.find(Table.class, table.getId());
			em.remove(tbl);
		}
	}

	// *
	public void deleteAllMeal() {
		List<Meal> list = em.createQuery("FROM Meal", Meal.class).getResultList();
		for (Meal ml : list) {
			Meal meal = em.find(Meal.class, ml.getId());
			em.remove(meal);
		}
	}

	// *
	public void deleteAllOrder() {
		List<Order> list = em.createQuery("FROM Order", Order.class).getResultList();
		for (Order order : list) {
			Order ord = em.find(Order.class, order.getId());
			em.remove(ord);
		}
	}

	// *
	public void deleteOrder() {
		System.out.println("---------------------");
		printOrder();
		System.out.println("---------------------");
		System.out.println("Enter id");
		Order ordd = em.find(Order.class, cin.nextInt());
		em.remove(ordd);
	}

	// *
	public void deleteMeal() {
		System.out.println("---------------------");
		printMeal();
		System.out.println("---------------------");
		System.out.println("Enter id");
		Meal meal = em.find(Meal.class, cin.nextInt());
		em.remove(meal);
	}

	// *
	public void deleteTable() {
		System.out.println("---------------------");
		printTable();
		System.out.println("---------------------");
		System.out.println("Enter id");
		Table table = em.find(Table.class, cin.nextInt());
		em.remove(table);
	}

	public void deleteCuisine() {
		System.out.println("---------------------");
		printCuisine();
		System.out.println("---------------------");
		System.out.println("Enter id");
		Cuisine cuisine = em.find(Cuisine.class, cin.nextInt());
		em.remove(cuisine);
	}

	public void deleteIngredient() {
		System.out.println("---------------------");
		printIngredient();
		System.out.println("---------------------");
		System.out.println("Enter id");
		Ingredient ingredient = em.find(Ingredient.class, cin.nextInt());
		em.remove(ingredient);
	}

	public void deleteCafe() {
		System.out.println("---------------------");
		printCafe();
		System.out.println("---------------------");
		System.out.println("Enter id");
		Cafe cafe = em.find(Cafe.class, cin.nextInt());
		em.remove(cafe);
	}

	public void deleteOpenClose() {
		System.out.println("---------------------");
		printOpenClose();
		System.out.println("---------------------");
		System.out.println("Enter id");
		int b = cin.nextInt();
		OpenClose time = em.find(OpenClose.class, b);
		em.remove(time);
	}

	// --------------------Add--------------------//
	public void addDefaultIngredient() {
		for (int i = 0; i < defaultIngredient.length; i++) {
			Ingredient ing = new Ingredient();
			ing.setName(defaultIngredient[i]);
			em.persist(ing);
		}
	}

	public void addDefaultCuisine() {
		for (int i = 0; i < defaultCuisine.length; i++) {
			Cuisine cuis = new Cuisine();
			cuis.setName(defaultCuisine[i]);
			em.persist(cuis);
		}
	}

	public void addDefaultTime() {
		for (int i = 0; i < 24; i++) {
			OpenClose time = new OpenClose(LocalTime.of(i, 0));
			em.persist(time);
		}
	}

	public void addOrder() {
		Order order = new Order();

		System.out.println("---------------------");
		printTable();
		System.out.println("---------------------");
		System.out.println("Enter table id");
		Table table = em.find(Table.class, cin.nextInt());
		order.setTable(table);

		System.out.println("Enter number of meals");
		int a = cin.nextInt();
		System.out.println("---------------------");
		printMeal();
		System.out.println("---------------------");
		List<Meal> meals = new ArrayList<Meal>();
		for (int i = 0; i < a; i++) {
			System.out.println("Choice meal");
			meals.add(em.find(Meal.class, verificationMealId(cin.next())));
		}
		order.setMeals(meals);

		em.persist(order);
	}

	public void addMeal() {
		Meal meal = new Meal();
		System.out.println("Enter name");
		meal.setName(cin.next());

		System.out.println("---------------------");
		printCafe();
		System.out.println("---------------------");
		System.out.println("Choice cafe");
		Cafe cafe = em.find(Cafe.class, verificationCafeId(cin.next()));
		meal.setCafe(cafe);

		System.out.println("---------------------");
		printCuisine();
		System.out.println("---------------------");
		System.out.println("Choice cuisine");
		Cuisine cuisine = em.find(Cuisine.class, verificationCuisisneId(cin.next()));
		meal.setCuisine(cuisine);

		System.out.println("Enter number of ingredient");
		int a = cin.nextInt();
		System.out.println("---------------------");
		printIngredient();
		System.out.println("---------------------");
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (int i = 0; i < a; i++) {
			System.out.println("Choice ingredient");
			ingredients.add(em.find(Ingredient.class, verificationIngredientId(cin.next())));
		}
		meal.setIngredients(ingredients);

		System.out.println("Enter price");
		meal.setPrice(cin.nextBigDecimal());

		System.out.println("Enter weight");
		meal.setWeight(cin.nextInt());

		System.out.println("Enter description");
		meal.setDescription(cin.next());

		em.persist(meal);
	}

	public void addTable() {
		Table table = new Table();
		System.out.println("Enter number of place");
		table.setCountOfPeople(cin.nextInt());
		table.setFree(true);
		System.out.println("---------------------");
		printCafe();
		System.out.println("---------------------");
		System.out.println("Choice cafe");
		Cafe cafe = em.find(Cafe.class, verificationCafeId(cin.next()));
		table.setCafe(cafe);
		em.persist(table);
	}

	public void addIngredient() {
		Ingredient ing = new Ingredient();
		System.out.println("Enter name of ingredient");
		ing.setName(cin.next());
		em.persist(ing);
	}

	public void addCuisine() {
		Cuisine cuis = new Cuisine();
		System.out.println("Enter name of cuisine");
		cuis.setName(cin.next());
		;
		em.persist(cuis);
	}

	public void addCafe() {

		Cafe cafe = new Cafe();

		System.out.println("Enter name");
		cafe.setName(cin.next());

		System.out.println("Enter address");
		cafe.setAddress(cin.next());

		System.out.println("Choice type");
		System.out.println("|pub|sushy|bar|cafe|restaurant|");
		cafe.setType(Type.valueOf(cin.next().toUpperCase()));

		System.out.println("---------------------");
		printOpenClose();
		System.out.println("---------------------");
		System.out.println("Choice opening time or");
		System.out.println("press 25 to add new time");
		int choice0 = cin.nextInt();
		switch (choice0) {
		case 25: {
			System.out.println("Enter time");
			int time0 = cin.nextInt();
			addOpenClose(time0);
			OpenClose timeOpen = em.find(OpenClose.class, verificationTimeId(time0));
			cafe.setOpen(timeOpen);
		}
			break;

		default:
			OpenClose timeOpen = em.find(OpenClose.class, verificationTimeId(choice0));
			cafe.setOpen(timeOpen);
			break;
		}

		System.out.println("---------------------");
		printOpenClose();
		System.out.println("---------------------");
		System.out.println("Choice closing time or");
		System.out.println("press 25 to add new time");
		int choice1 = cin.nextInt();
		switch (choice1) {
		case 25: {
			System.out.println("Enter time");
			int time = cin.nextInt();
			addOpenClose(time);
			OpenClose timeClose = em.find(OpenClose.class, verificationTimeId(time));
			cafe.setClose(timeClose);
		}
			break;

		default:
			OpenClose timeClose = em.find(OpenClose.class, verificationTimeId(choice1));
			cafe.setClose(timeClose);
			break;
		}

		System.out.println("Enter shortDescription");
		cafe.setShortDescription(cin.next());

		System.out.println("Enter fullDescription");
		cafe.setFullDescription(cin.next());

		System.out.println("Enter phone");
		cafe.setPhone(cin.next());

		System.out.println("Enter email");
		cafe.setEmail(cin.next());

		em.persist(cafe);
	}

	public void addOpenClose() {
		System.out.println("Enter time");
		int a = cin.nextInt();
		// OpenClose time = new OpenClose(LocalTime.of(a, 0));
		OpenClose time = new OpenClose(verificationTime(a));
	}

	// --------------------Print--------------------//
	public void printOrder() {
		List<Order> list = em.createQuery("Select o FROM Order o JOIN o.table JOIN o.meals", Order.class)
				.getResultList();
		for (Order order : list) {
			System.out.println(order.getId() + "|" + order.getTable() + "|" + order.getMeals() + "|");
		}
	}

	public void printMeal() {
		List<Meal> list = em.createQuery("Select m FROM Meal m JOIN m.cuisine ", Meal.class).getResultList();
		for (Meal meal : list) {
			System.out.println(meal.getId() + "|" + meal.getName() + "|" + meal.getCuisine().getName() + "|"
					+ meal.getPrice() + "|" + meal.getWeight() + "|" + meal.getDescription());
		}
	}

	public void printTable() {
		List<Table> list = em.createQuery("Select t FROM Table t JOIN t.cafe", Table.class).getResultList();
		for (Table table : list) {
			System.out.println(table.getId() + "|" + table.getCountOfPeople() + "|" + table.isFree() + "|"
					+ table.getCafe().getName());
		}
	}

	public void printOpenClose() {
		List<OpenClose> list = em.createQuery("FROM OpenClose", OpenClose.class).getResultList();
		for (OpenClose openClose : list) {
			System.out.println(openClose.getId() + "|" + openClose.getTime());
		}
	}

	public void printIngredient() {
		List<Ingredient> list = em.createQuery("FROM Ingredient", Ingredient.class).getResultList();
		for (Ingredient ingredient : list) {
			System.out.println(ingredient.getId() + "|" + ingredient.getName());
		}
	}

	public void printCuisine() {
		List<Cuisine> list = em.createQuery("FROM Cuisine ", Cuisine.class).getResultList();
		for (Cuisine cuisine : list) {
			System.out.println(cuisine.getId() + "|" + cuisine.getName());
		}
	}

	public void printCafe() {
		List<Cafe> list = em.createQuery("Select c FROM Cafe c JOIN c.open op JOIN c.close cl", Cafe.class)
				.getResultList();
		for (Cafe cafe : list) {
			System.out.println(cafe.getId() + "|" + cafe.getName() + "|" + cafe.getAddress() + "|" + cafe.getType()
					+ "|" + cafe.getOpen().getTime() + "|" + cafe.getClose().getTime() + "|" + cafe.getPhone() + "|"
					+ cafe.getEmail() + "|" + cafe.getShortDescription() + "|" + cafe.getFullDescription());
		}
	}

	public void printCafeMeal(Cafe cafe) {
		for (Meal meals : cafe.getMeals()) {
			System.out.println(meals.getId() + "|" + meals.getName());
		}
	}

	public void printCafeView1() {
		List<Cafe> cafes = em
				.createQuery("Select c FROM Cafe c JOIN c.open o JOIN c.close c WHERE o.time=?1", Cafe.class)
				.setParameter(1, LocalTime.of(13, 0)).getResultList();
		List<CafeView> views = new ArrayList<>();
		for (Cafe cafe : cafes) {
			CafeView cafeView = new CafeView(cafe.getId(), cafe.getRate(), cafe.getName(), cafe.getPhotoUrl(),
					cafe.getVersion(), cafe.getAddress(), cafe.getFullDescription(), cafe.getType(), cafe.getPhone(),
					cafe.getEmail(), cafe.getOpen().getTime(), cafe.getClose().getTime());
			views.add(cafeView);
			cafeView.toString();
		}

	}

	public void printCafeView() {
		List<CafeView> views = em.createQuery(
				"Select ua.model.view CafeView(c.id, c.rate,c.name, c.photoUrl, c.version, "
						+ "c.address,c.fullDescription, c.type, c.phone, c.email, open.time, close.time) "
						+ "FROM Cafe c LEFT JOIN c.open open LEFT JOIN c.close close WHERE open.time=?1",
				CafeView.class).setParameter(1, LocalTime.of(13, 0)).getResultList();
		for (CafeView cafeView : views) {
			System.out.println(cafeView.getId() + "|" + cafeView.getName() + "|" + cafeView.getAddress() + "|"
					+ cafeView.getType() + "|" + cafeView.getOpen() + "|" + cafeView.getClose() + "|"
					+ cafeView.getPhone() + "|" + cafeView.getEmail() + "|" + cafeView.getFullDescription());
		}
	}

	public void printMealView() {
		List<MealView> viewsML = em.createQuery("Select ua.model.view MealView(m.id, m.title, "
				+ "m.description, m.price, m.photoUrl, m.version,m.cuisine, " + "m.weight, ing.ingredients) "
				+ "FROM Meal m LEFT JOIN m.ingredients ing ", MealView.class).getResultList();
		for (MealView malView : viewsML) {
			System.out.println(
					malView.getId() + "|" + malView.getTitle() + "|" + malView.getCuisine() + "|" + malView.getWeight()
							+ "|" + malView.getPrice() + "|" + malView.getWeight() + "|" + malView.getIngredients());
		}
	}

	// --------------------Other methods--------------------//
	public void menu() {
		boolean isRun = true;
		while (isRun) {
			// System.out.println("1)Uuser");
			System.out.println("2)Admin");
			// System.out.println("3)Print cafe viev");
			// System.out.println("4)Print meal viev");

			switch (cin.nextInt()) {
			/*
			 * case 3:{ // System.out.println("Enter time "); //printCafeView();
			 * printCafeView1(); } break; case 4:{ printMealView(); } break;
			 * case 1: { boolean isUser = true; while (isUser) {
			 * System.out.println("1)Take a place");
			 * System.out.println("2)Fires a place"); switch (cin.nextInt()) {
			 * case 1: { System.out.println("Choise cafe");
			 * System.out.println("------------------------"); printCafe();
			 * System.out.println("------------------------"); Cafe cafe =
			 * em.find(Cafe.class, verificationCafeId(cin.next()));
			 * System.out.println("Choise table ID");
			 * System.out.println("------------------------");
			 * System.out.println("ID | Count of people"); for (Table caf :
			 * cafe.getTables()) { System.out.println(caf.getId() + "|" +
			 * caf.getCountOfPeople()); }
			 * System.out.println("------------------------"); Table table =
			 * em.find(Table.class, cin.nextInt()); table.setFree(false);
			 * System.out.println("------------------------"); boolean isMealRun
			 * = true; List<Meal> meals = new ArrayList<>();
			 * 
			 * while (isMealRun) { System.out.println("1)Order a meal"); switch
			 * (cin.nextInt()) { case 1: {
			 * System.out.println("------------------------");
			 * printCafeMeal(cafe);
			 * System.out.println("------------------------");
			 * meals.add(em.find(Meal.class, verificationMealId(cin.next()))); }
			 * break;
			 * 
			 * default: isMealRun = false; break; } } Order order=new Order();
			 * order.setTable(table); order.setMeals(meals); em.persist(order);
			 * em.persist(table); }
			 * 
			 * break; case 2: { System.out.println("Choice order ID");
			 * System.out.println("------------------------"); printOrder();
			 * System.out.println("------------------------"); Order
			 * order=em.find(Order.class, cin.nextInt()); Table
			 * table=em.find(Table.class, order.getTable());
			 * table.setFree(true); em.remove(order); em.persist(table);
			 * 
			 * }
			 * 
			 * break; default: isUser=false; break; } } } break;
			 */
			case 2: {
				boolean isAdm = false;
				System.out.println("Enter password");
				if (cin.next().equals("root")) {
					isAdm = true;
				}
				while (isAdm) {
					System.out.println("------------------------");
					System.out.println("->1)Add");
					System.out.println("->2)Print");
					System.out.println("->3)Delete");
					System.out.println("------------------------");
					switch (cin.nextInt()) {
					case 1: {
						boolean isAdd = true;
						while (isAdd) {
							System.out.println("------------------------");
							System.out.println("-->1)Add Default");
							System.out.println("-->2)Add Ingredient");
							System.out.println("-->3)Add Cuisine");
							System.out.println("-->4)Add Time");
							System.out.println("-->5)Add Cafe");
							System.out.println("-->6)Add Table");
							System.out.println("-->7)Add Order");
							System.out.println("-->8)Add Meal");
							System.out.println("------------------------");
							switch (cin.nextInt()) {
							case 1: {
								System.out.println("------------------------");
								System.out.println("--->1)Add Default Ingredient");
								System.out.println("--->2)Add Default Cuisine");
								System.out.println("--->3)Add Default Time");
								System.out.println("------------------------");
								switch (cin.nextInt()) {
								case 1: {
									addDefaultIngredient();

								}
									break;
								case 2: {
									addDefaultCuisine();

								}
									break;
								case 3: {
									addDefaultTime();

								}
									break;

								default: {

								}
								}
							}
								break;
							case 2: {
								addIngredient();
							}
								break;
							case 3: {
								addCuisine();
							}
								break;
							case 4: {
								addOpenClose();
							}
								break;
							case 5: {
								addCafe();
							}
								break;
							case 6: {
								addTable();
							}
								break;
							case 7: {
								addOrder();
							}
								break;
							case 8: {
								addMeal();
							}
								break;

							default: {
								isAdd = false;
							}
							}
						}
					}
						break;
					case 2: {
						boolean isPrint = true;
						while (isPrint) {
							System.out.println("------------------------");
							System.out.println("-->1)Print Cafe");
							System.out.println("-->2)Print Time");
							System.out.println("-->3)Print Meal");
							System.out.println("-->4)Print Cuisine");
							System.out.println("-->5)Print Ingredient");
							System.out.println("-->6)Print Table");
							System.out.println("-->7)Print Order");
							System.out.println("------------------------");
							switch (cin.nextInt()) {
							case 1: {
								printCafe();
							}
								break;
							case 2: {
								printOpenClose();
							}
								break;
							case 3: {
								printMeal();
							}
								break;
							case 4: {
								printCuisine();
							}
								break;
							case 5: {
								printIngredient();
							}
								break;
							case 6: {
								printTable();
							}
								break;
							case 7: {
								printOrder();
							}
								break;

							default: {
								isPrint = false;
							}
							}

						}
					}
						break;
					case 3: {
						boolean isDelete = true;
						while (isDelete) {
							System.out.println("------------------------");
							System.out.println("-->1)Delete Cafe");
							System.out.println("-->2)Delete Time");
							System.out.println("-->3)Delete Meal");
							System.out.println("-->4)Delete Cuisine");
							System.out.println("-->5)Delete Ingredient");
							System.out.println("-->6)Delete Table");
							System.out.println("-->7)Delete Order");
							System.out.println("-->8)Delete All");
							System.out.println("------------------------");
							switch (cin.nextInt()) {
							case 1: {
								deleteCafe();
							}
								break;
							case 2: {
								deleteOpenClose();
							}
								break;
							case 3: {
								deleteMeal();
							}
								break;
							case 4: {
								deleteCuisine();
							}
								break;
							case 5: {
								deleteIngredient();
							}
								break;
							case 6: {
								deleteTable();
							}
								break;
							case 7: {
								deleteOrder();
							}
								break;
							case 8: {

								boolean isAllDelete = true;
								while (isAllDelete) {
									System.out.println("------------------------");
									System.out.println("--->1)Delete All Cafe");
									System.out.println("--->2)Delete All Time");
									System.out.println("--->3)Delete All Meal");
									System.out.println("--->4)Delete All Cuisine");
									System.out.println("--->5)Delete All Ingredient");
									System.out.println("--->6)Delete All Table");
									System.out.println("--->7)Delete All Order");
									System.out.println("------------------------");
									switch (cin.nextInt()) {
									case 1: {
										deleteAllCafe();
									}
										break;
									case 2: {
										deleteAllTime();
									}
										break;
									case 3: {
										deleteAllMeal();
									}
										break;
									case 4: {
										deleteAllCuisine();
									}
										break;
									case 5: {
										deleteAllIngredient();
									}
										break;
									case 6: {
										deleteAllTable();
									}
										break;
									case 7: {
										deleteAllOrder();
									}
									default: {
										isAllDelete = false;
									}
									}

								}
							}
								break;

							default: {
								isDelete = false;
							}
							}
						}
					}
						break;

					default: {
						isAdm = false;
					}
					}
				}

			}
				break;
			default: {
				isRun = false;
			}
			}

		}
	}

	public void addOpenClose(int a) {
		OpenClose time = new OpenClose(verificationTime(a));
	}

	public int verificationTimeId(int a) {
		OpenClose time = new OpenClose(LocalTime.of(a, 0));
		List<OpenClose> list = em.createQuery("FROM OpenClose", OpenClose.class).getResultList();
		for (OpenClose openClose : list) {
			if (time.getTime() == openClose.getTime()) {
				return openClose.getId();
			}
		}
		return 0;

	}

	public int verificationCafeId(String name) {
		List<Cafe> list = em.createQuery("FROM Cafe", Cafe.class).getResultList();
		for (Cafe cafe : list) {
			if (name.equals(cafe.getName())) {
				return cafe.getId();
			}
		}
		return 0;
	}

	public int verificationCuisisneId(String name) {
		List<Cuisine> list = em.createQuery("FROM Cuisine", Cuisine.class).getResultList();
		for (Cuisine cuisine : list) {
			if (name.equals(cuisine.getName())) {
				return cuisine.getId();
			}
		}
		return 0;
	}

	public int verificationIngredientId(String name) {
		List<Ingredient> list = em.createQuery("FROM Ingredient", Ingredient.class).getResultList();
		for (Ingredient ingredient : list) {
			if (name.equals(ingredient.getName())) {
				return ingredient.getId();
			}
		}
		return 0;
	}

	public int verificationMealId(String name) {
		List<Meal> list = em.createQuery("FROM Meal", Meal.class).getResultList();
		for (Meal meal : list) {
			if (name.equals(meal.getName())) {
				return meal.getId();
			}
		}
		return 0;
	}

	public LocalTime verificationTime(int a) {
		OpenClose time = new OpenClose(LocalTime.of(a, 0));
		List<OpenClose> list = em.createQuery("FROM OpenClose", OpenClose.class).getResultList();
		for (OpenClose openClose : list) {
			if (time.getTime() == openClose.getTime()) {
				return time.getTime();
			}
		}
		em.persist(time);
		return (LocalTime.of(a, 0));
	}

}