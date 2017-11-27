package cz.fi.muni.pa165.musiclibrary.utils;

import java.util.ArrayList;
import java.util.List;

public class SearchHelper {

	public static List<String> splitSearchQuery(String query) {
		List<String> patterns = new ArrayList<String>();

		for (String word : query.split(" ")) {
			if (!word.isEmpty()) {
				patterns.add("%" + word + "%");
			}
		}

		return patterns;
	}

}
