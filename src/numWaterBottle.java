
public class numWaterBottle {
	public int numWaterBottles(int numBottles, int numExchange) {
		int full = 0;
		// full water bottles that bring to next round of exchange
		int emptyb = 0;
		// empty bottles that bring to next round of exchange
		int ans = numBottles;
		// total full water bottles in every round of exchange

		// while loop keep iterate when # of bottles/ numExchange > 0
		// because if quotient equal to 0
		// means not enough empty bottles to exchange for full water bottles
		while (numBottles / numExchange > 0) {
			emptyb = numBottles % numExchange;
			// empty bottles that bring to next round exchange
			// is remainder of current # of bottles/ numExchange

			full = numBottles / numExchange;
			// next round full water bottles
			// is quotient of current # of bottles/ numExchange

			ans += full;
			// accumulate add every full water bottles in each exchange round

			numBottles = emptyb + full;
			// update the current # of bottles after this round of exchange
		}
		return ans;
	}

}
