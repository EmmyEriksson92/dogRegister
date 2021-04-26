//Emmy Eriksson, emer4231

public class BubbleSort {
	public void bubbleSort(Bid[] bids) {

		for (int i = 0; i < bids.length; i++) {
			for (int j = 0; j < bids.length - 1; j++) {
				if (bids[j].getBid() < bids[j + 1].getBid()) {
					Bid temp = bids[j];
					bids[j] = bids[j + 1];
					bids[j + 1] = temp;
				}
			}
		}
	}
}
