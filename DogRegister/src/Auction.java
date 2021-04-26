
//Emmy Eriksson.
import java.util.ArrayList;
import java.util.Arrays;

public class Auction {

	private int serialNumber;
	private Dog dog;
	private static int counter = 1;
	private ArrayList<Bid> bidList = new ArrayList<>();

	public Auction(Dog dog) {
		this.dog = dog;
		this.serialNumber = counter;
		counter++;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public Dog getDog() {
		return dog;
	}

	public String getBidList() {
		BubbleSort b = new BubbleSort();

		Bid[] bids = new Bid[bidList.size()];
		for (int i = 0; i < bidList.size(); i++) {
			bids[i] = bidList.get(i);
		}
		b.bubbleSort(bids);
		String s = "";

		for (int i = 0; i < bidList.size(); i++) {
			s += bids[i].toString() + "\n";

		}
		return s;

	}

	public String getThreeHighestBids() {

		String s = "";

		BubbleSort b = new BubbleSort();
		Bid[] bids = new Bid[bidList.size()];
		for (int i = 0; i < bidList.size() && i < 3; i++) {
			bids[i] = bidList.get(i);

		}
		b.bubbleSort(bids);
		s += Arrays.toString(bids);

		return s;
	}

	public int highestBid() {

		if (bidList.isEmpty()) {
			return 1;
		}

		BubbleSort b = new BubbleSort();
		Bid[] bids = new Bid[bidList.size()];

		for (int i = 0; i < bidList.size(); i++) {
			bids[i] = bidList.get(i);
		}
		b.bubbleSort(bids);

		return bids[0].getBid();

	}

	public User getWinner() {
		BubbleSort b = new BubbleSort();
		Bid[] bids = new Bid[bidList.size()];
		for (int i = 0; i < bidList.size(); i++) {
			bids[i] = bidList.get(i);
		}
		b.bubbleSort(bids);

		return bids[0].getUser();
	}

	public void addBid(Bid bid) {
		bidList.add(bid);

	}

	public void removeBid(User user) {
		for (int i = 0; i < bidList.size(); i++) {
			if (bidList.get(i).getUser().equals(user)) {
				bidList.remove(i);
			}
		}
	}

	@Override
	public String toString() {
		return String.format("Auction #%d: %s. Topp bud: %s", getSerialNumber(), getDog().getName(),
				getThreeHighestBids());
	}
}