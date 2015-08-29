package implementIntervalTree;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import implementIntervalTree.IntervalTree.Interval;
import implementIntervalTree.IntervalTree.IntervalTree;

public class PlayWithIntervalTree {

	public static void main(String[] args) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		IntervalTree<Date> tree = new IntervalTree<>();
		tree.add(new Interval<Date>(sdf.parse("2015-08-01"), sdf.parse("2015-08-08")));
		tree.add(new Interval<Date>(sdf.parse("2015-08-04"), sdf.parse("2015-08-06")));
		tree.add(new Interval<Date>(sdf.parse("2015-08-03"), sdf.parse("2015-08-09")));
		
		for (Interval<Date> date : tree) {
			System.out.println(date);
		}
		
		for (Interval<Date> interval : tree.get(sdf.parse("2015-08-03")).values()) {
			System.out.println(interval);
		}
	}

}
