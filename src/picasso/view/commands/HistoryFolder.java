/**
 * 
 */
package picasso.view.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author drewlarsen
 *
 */
public class HistoryFolder {
	
	public static List<String> HistoryList;
	
	/**
	 * 
	 */
	public HistoryFolder() {
		HistoryList = new LinkedList<String>();
	}
	
	public static void addToList(String expression) {
		if (HistoryList.size() == 10) {
			((LinkedList<String>) HistoryList).removeLast();
		}
		HistoryList.add(0,expression);
	}

}
