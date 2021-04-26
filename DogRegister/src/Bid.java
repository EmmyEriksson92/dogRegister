//Emmy Eriksson.
public class Bid {
	private User user;
	private int bid;

	public Bid(int bid, User user) {
		this.bid = bid;
		this.user = user;
	}

	public User getUser() {
		return user;
	}


	public int getBid() {
		return bid;
	}

	public String toString() {
		return String.format("%s %d kr", getUser().getName(), getBid());
	}
}