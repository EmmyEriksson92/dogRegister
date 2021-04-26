//Emmy Eriksson.
import java.util.ArrayList;
import java.util.Arrays;

public class User {

	private String name;
	private ArrayList<Dog> dogsOwned = new ArrayList<>();

	public User(String name) {
		this.name = name;
	}

	public void addDog(Dog dog) {
		dogsOwned.add(dog);

	}

	public ArrayList<Dog> getDogsOwned() {
		return dogsOwned;

	}

	private String[] dogNamesInArray() {

		String[] dogNames = new String[dogsOwned.size()];

		for (int i = 0; i < dogNames.length; i++) {
			dogNames[i] = dogsOwned.get(i).getName();
		}

		return dogNames;
	}

	public void removeDog(Dog dog) {

		for (int i = 0; i < dogsOwned.size(); i++) {
			dogsOwned.remove(dog);

		}
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("%s %s", getName(), Arrays.toString(dogNamesInArray()));
	}

}
