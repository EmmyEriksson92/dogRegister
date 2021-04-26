//Emmy Eriksson, emer4231
public class Dog implements Comparable<Dog> {

	private String name;
	private String breed;
	private int age;
	private int weight;
	private User user;

	public Dog(String name, String breed, int age, int weight) {
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.weight = weight;
	}

	public String getName() {

		return name;
	}

	public String getBreed() {
		return breed;
	}

	public int getAge() {
		return age;
	}

	public int getWeight() {
		return weight;
	}

	public void increaseAge() {
		age++;
	}

	public double getTailLength() {
		if (breed.equalsIgnoreCase("Tax") || breed.equalsIgnoreCase("Dachshund")) {
			return 3.7;
		} else {
			return age * (double) weight / 10;
		}
	}

	public void setUser(User user) {
		user.removeDog(this); // remove tidigare ägare.
		this.user = user;
		user.addDog(this);
	}

	public User getUser() {
		return user;
	}

	@Override
	public int compareTo(Dog other) {
		if (this.getTailLength() - other.getTailLength() == 0) {
			return this.getName().compareTo(other.getName());
		}
		Double d = this.getTailLength();
		return d.compareTo(other.getTailLength());

	}

	@Override
	public String toString() {

		String s = "";

		if (user != null) {
			s = String.format(", ägs av %s", getUser().getName());

		}
		return String.format("%s är av ras %s & är %d år och väger %d kg och har svanslängd %.2f %s", name, breed, age,
				weight, getTailLength(), s);

	}
}
