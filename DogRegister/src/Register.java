
///Emmy Eriksson, emer4231
import java.util.*;

public class Register {

	private ArrayList<Dog> dogList = new ArrayList<>();
	private InputHandler input = new InputHandler();
	private ArrayList<User> userList = new ArrayList<>();
	private ArrayList<Auction> auctionList = new ArrayList<>();

	private void dogsFromStart() {

		Dog dog1 = new Dog("Zira", "doberrmann", 8, 34);
		Dog dog2 = new Dog("Lasse", "labrador", 9, 40);
		Dog dog3 = new Dog("Pompe", "tax", 3, 1);

		dogList.add(dog1);
		dogList.add(dog2);
		dogList.add(dog3);
	}

	private void usersFromStart() {
		User user1 = new User("Lars");
		User user2 = new User("Danne");
		User user3 = new User("Erik");
		User user4 = new User("Johanna");

		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
	}

	private void registerNewDog() {

		String name = nameFormatting("hundens namn: ");
		String breed = nameFormatting("hundens ras: ");

		System.out.println("Skriv in hundens ålder: ");
		int age = input.readInt();

		System.out.println("Skriv in hundens vikt: ");
		int weight = input.readInt();

		Dog dog = new Dog(name, breed, age, weight);
		dogList.add(dog);

		System.out.println(name + " är tillagd i hundregistret");

	}

	private void increaseDogAge() {

		String name = nameFormatting("namnet på hunden som ska åldras: ");

		boolean nameCorrect = false;

		for (Dog d : dogList) {
			if (d.getName().equals(name)) {
				d.increaseAge();
				nameCorrect = true;
			}
		}
		if (nameCorrect) {
			System.out.println("Hunden med det angivna namnet " + name + " har ökat ålder med ett år!");
		} else {
			System.out.println("Fel: hund med det namnet fanns ej i registret");
		}
	}

	private void listDogs() {

		System.out.println("Skriv in  minsta svanslängd: ");
		double taleLength = input.readDouble();
		Collections.sort(dogList);

		for (int i = 0; i < dogList.size(); i++) {

			if (taleLength <= dogList.get(i).getTailLength()) {
				System.out.println(dogList.get(i));
			}
		}
	}

	private void removeDog() {

		String name = nameFormatting("namn på hunden som ska tas bort: ");
		boolean nameFound = false;

		Dog d = findDogInList(name);
		for (int i = 0; i < dogList.size(); i++) {
			if (dogList.get(i).getName().equals(name)) {
				dogList.remove(i);
				nameFound = true;

			}
		}

		removeDogInAuction(name);
		removeDogFromOwner(d);

		if (nameFound) {
			System.out.println("Hunden med det angivna namnet " + name + " är borttaget!");
		} else {
			System.out.println("Fel: hund med det namnet fanns ej i registret");
		}

	}

	private void registerNewUser() {

		String name = nameFormatting("användarens namn: ");
		User user = new User(name);

		userList.add(user);
		System.out.println(name + " är adderat till registret");

	}

	private void listUsers() {
		if (userList.isEmpty()) {
			System.out.println("Error - inga användare i registret");
		} else {
			for (int i = 0; i < userList.size(); i++) {
				System.out.println(userList.get(i).toString());
			}
		}

	}

	private void removeUser() {

		String name = nameFormatting("namnet på användaren som ska tas bort: ");
		boolean nameFound = false;

		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getName().equals(name)) {
				for (Auction a : auctionList) {
					a.removeBid(userList.get(i));
				}
				nameFound = true;

				for (Dog d : userList.get(i).getDogsOwned()) {
					dogList.remove(d);
				}
				userList.remove(i);
				break;
			}
		}

		if (nameFound) {
			System.out.println("Användaren med det angivna namnet " + name + " är borttaget!");

		} else {
			System.out.println("Fel: användare med det namnet fanns ej i registret");
		}
	}

	private void startAuction() {

		String name = nameFormatting("namnet på hunden som ska säljas: ");
		Dog d = findDogInList(name);
		Auction a = findDogInAuction(name);

		if (d == null) {
			System.out.println("Error - finns ingen hund med namnet " + name);
			return;
		} else if (a != null) {
			System.out.println("Error - denna hund finns redan uppe på auktion");
			return;
		} else if (hasAOwner(name)) {
			System.out.println("Error - denna hund har redan en ägare.");
			return;

		} else if (d != null) {
			Auction auction = new Auction(d);
			auctionList.add(auction);
			System.out.println(name + " har nu satts upp på auktion på auktion #" + auction.getSerialNumber());
		}

	}

	private void listAuctions() {
		if (auctionList.isEmpty()) {
			System.out.println("Error - det finns inga pågående auktioner");
		} else {
			for (int i = 0; i < auctionList.size(); i++) {
				System.out.println(auctionList.get(i).toString());
			}
		}

	}

	private void listBids() {

		String name = nameFormatting("namnet på hunden: ");
		Auction a = findDogInAuction(name);
		if (a != null) {
			if (a.getBidList().isEmpty()) {
				System.out.println("inga bud finns registrerade än för denna auktion");
				return;
			}
		}
		if (a == null) {
			System.out.println("Error - hunden med namnet " + name + " är inte till salu!");
			return;
		} else {
			if (a != null) {
				System.out.println("Här är toppbuden på denna auktion.");
				System.out.println(findDogInAuction(name).getBidList().toString());
			}
		}
	}

	private void makeBid() {

		String nameUser = nameFormatting(" namnet på användaren: ");
		User u = findName(nameUser);
		if (u == null) {
			System.out.println("Error - finns ingen användare med namnet " + nameUser);
			return;
		}
		String nameDog = nameFormatting("namnet på hunden: ");
		Auction a = findDogInAuction(nameDog);
		if (a == null) {
			System.out.println("Error - hunden med namnet " + nameDog + " är inte till salu!");
			return;
		} else {

			int highestBid = a.highestBid();
			int bidInput;
			boolean bidCorrect = false;

			if (a != null && u != null) {
				while (true) {
					System.out.println("Amount to bid: " + "(" + highestBid + "): ");
					bidInput = input.readInt();

					if (bidInput > highestBid) {
						bidCorrect = true;
						break;

					} else {
						System.out.println("Error - för lågt bud");
					}
				}
				if (bidCorrect) {

					if (u.getName().equals(nameUser)) {
						Bid bid = new Bid(bidInput, u);
						a.removeBid(u);
						a.addBid(bid);
						System.out.println("Bid made");
					}
				}
			}

		}
	}

	private void closeAuction() {

		String name = nameFormatting("namnet på hunden: ");
		Dog d = findDogInList(name);
		Auction a = findDogInAuction(name);

		if (d == null) {
			System.out.println("Error - hund med namnet " + name + " finns ej");
			return;
		} else if (a == null) {
			System.out.println("Error - hunden med namnet " + name + " är inte till salu!");
			return;
		} else {
			if (a != null) {
				int bid = a.highestBid();
				if (bid == 1) {
					System.out.println("Auktionen är stängd. Inga bud var gjorda för hunden " + name);
					removeDogInAuction(name);
					return;
				}
				System.out.println("Auction är stängd. Vinnande bud var: " + bid + "kr och budare var: "
						+ a.getWinner().getName());
				if (d.getName().equals(name)) {
					d.setUser(a.getWinner());
					removeDogInAuction(name);
					a.removeBid(a.getWinner());

				}
			}
		}
	}

	private void removeDogFromOwner(Dog dog) {
		for (int i = 0; i < userList.size(); i++) {
			userList.get(i).removeDog(dog);
		}
	}

	private void removeDogInAuction(String name) {
		for (int i = 0; i < auctionList.size(); i++) {
			if (auctionList.get(i).getDog().getName().equals(name)) {
				auctionList.remove(i);
			}
		}
	}

	private boolean hasAOwner(String name) {
		boolean dogFound = false;
		for (User u : userList) {
			for (Dog dog : u.getDogsOwned()) {
				if (dog.getName().equals(name)) {
					dogFound = true;

				}
			}
		}
		return dogFound;
	}

	private Auction findDogInAuction(String name) {
		for (Auction a : auctionList) {
			if (a.getDog().getName().equals(name)) {
				return a;
			}
		}
		return null;

	}

	private Dog findDogInList(String name) {
		for (Dog d : dogList) {
			if (d.getName().equals(name)) {
				return d;
			}
		}
		return null;
	}

	private User findName(String userName) {
		for (User user : userList) {
			if (user.getName().equals(userName)) {
				return user;
			}

		}
		return null;
	}

	private String normailization(String input) {
		if (input == null)
			return "";

		String normalized = "";

		input = input.toLowerCase();
		if (input.length() > 0) {
			input = input.substring(0, 1).toUpperCase() + input.substring(1);
		}
		normalized = input;

		return normalized;

	}

	private String nameFormatting(String displayText) {

		String i = "";

		while (true) {
			System.out.println("Skriv in " + displayText);
			i = input.readString().trim();
			i = normailization(i);

			if (i.length() == 0) {
				System.out.println("Error, " + displayText + " kan inte vara tomt");
			} else if (i.length() > 0) {
				break;
			}

		}

		return i;

	}

	private String readCommand() {

		System.out.println("Vad vill du göra?");
		System.out.println(" ");
		System.out.println(
				"register new dog\nincrease age\nlist dogs\nremove dog\nregister new user\nlist users\nremove user\nstart auction\nlist auctions\nlist bids\nmake bid\nclose auction\nexit");
		return input.readString();
	}

	private void handleCommand(String command) {

		switch (command) {

		case "register new dog":
			registerNewDog();
			break;
		case "increase age":
			increaseDogAge();
			break;
		case "list dogs":
			listDogs();
			break;
		case "remove dog":
			removeDog();
			break;
		case "register new user":
			registerNewUser();
			break;
		case "list users":
			listUsers();
			break;
		case "remove user":
			removeUser();
			break;
		case "start auction":
			startAuction();
			break;
		case "list auctions":
			listAuctions();
			break;
		case "list bids":
			listBids();
			break;
		case "make bid":
			makeBid();
			break;
		case "close auction":
			closeAuction();
			break;
		case "exit":
			break;
		default:
			System.out.println("Fel, försök igen!");
		}
	}

	private void run() {
		String command;

		do {
			command = readCommand();
			handleCommand(command);
		} while (!command.equals("exit"));
		System.out.println("Programmet är nu avslutat!");
	}

	public static void main(String[] args) {

		Register register = new Register();
		register.dogsFromStart();
		register.usersFromStart();
		register.run();

	}
}
