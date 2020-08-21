import java.util.*;
/*
 * Abstract Data Types (Interface Types):
 * 
 * COLLECTION Interface (.add(index(optional), element) .remove(index(optional), element) .size() .contains(element) .addAll(object, object)):
 * 
 * List: INDEXED elements; ArrayList (array implementation) and LinkedList (no random access) collections 
 * .indexOf(element) --> sequential/linear search --> only lists, not arrays
 * .addAll(set)
 * .set(index, value)
 * .lastIndexOf(element)
 * .get(index)
 * .remove(index)
 * 
 * Iterators keep you from having to keep traversing from the beginning or end when sequentially adding or removing elements in
 * a LinkedList
 * REMOVAL CAUSES ALL LIST ELEMENTS TO BE RE-INDEXED (matters for for loops because there are defined upper and lower bounds)
 * 
 * Set: UNINDEXED elements, quick searches, no duplicates; HashSet/LinkedHashSet (hash map implementation) 
 * and TreeSet (binary search tree implementation; output is ordered; must be used with comparable (strings or ints) collections
 * No .get(index) because sets are unindexed --> define an iterator for the set instead
 * Sets perform .contains(element) very quickly!
 * 
 * addAll -> union
 * retainAll -> intersection
 * removeAll -> difference (A and not B)
 * containsAll -> boolean (A contains all of B)
 * 
 * MAP Interface (.put(key, value) .remove(key) .get(key) .keySet() .values() .containsKey(key) .containsValue(value) map1.putAll(map2)):
 * 
 * Map: Key-value pairs; HashMap and TreeMap (stores comparable keys in sorted order) collections
 * If you put a key-value pair into a Map but the key is already in the Map, the old 
 * key-value pair is replaced automatically
 * 
 * 		for (String name : salaryMap.keySet()) { 
			double salary = salaryMap.get(name);
			System.out.println(name + "'s salaray is " + salary + ".");
		}
		
 * 
 * ITERATORS:
 * itr.next()
 * itr.hasNext()
 * itr.remove()
 * for (Type variable : collection variable comes from) is another useful technique
 * 
 * Useful Collections methods:
 * Collections.max(collection)
 * Collections.min(collection)
 * Collections.reverse(collection)
 * Collections.binarySearch(collection)
 * Collections.frequency(collection)
 * 
 * 
 * TreeSets and TreeMaps accept comparators as parameters!
 * This allows objects without a natural ordering (e.g. points) to be added.
 */

public class ADTs {
	
	public static void main (String[] args) {
		List<String> stringList = new LinkedList<String>();
		stringList.add("run");
		stringList.add("hellllllo world");
		stringList.add("!");
		stringList.add("world");
		stringList.add("hello");
		Collections.reverse(stringList);
		System.out.println(stringList);
		System.out.println(longest(stringList));
		removeOddsLinkedList(stringList);
		System.out.println(stringList);
		ArrayList<String> stringArrayList = new ArrayList<String>();
		stringArrayList.add("hello");
		stringArrayList.add("world");
		stringArrayList.add("!");
		stringArrayList.add("cake");
		System.out.println(stringArrayList);
		System.out.println(longest(stringArrayList));
		removeOddsArrayList(stringArrayList);
		System.out.println(stringArrayList);
		List<Integer> sieveHundred = sieve(100);
		System.out.println(sieveHundred);
		
		
		
		Set<String> stooges = new LinkedHashSet<String>(); // order is preserved
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
		stooges.add("Moe");
		stooges.add("Shemp");
		stooges.add("Moe");
		System.out.println(stooges);
		Iterator<String> itr = stooges.iterator();
		while (itr.hasNext()) { // Need an iterator to loop through unindexed set elements
			String word = itr.next();
			System.out.println(word);
		}
		List<Integer> testList = new ArrayList<Integer>();
		testList.add(1);
		testList.add(2);
		testList.add(2);
		System.out.println(hasDuplicates(testList));
		List<Integer> firstIntList = new ArrayList<Integer>();
		List<Integer> secondIntList = new ArrayList<Integer>();
		firstIntList.add(1);
		firstIntList.add(2);
		firstIntList.add(4);
		secondIntList.add(2);
		secondIntList.add(3);
		secondIntList.add(4);
		System.out.println(returnDuplicates(firstIntList, secondIntList));
		
		
		
		Map<String, Double> salaryMap = new HashMap<String, Double>();
		salaryMap.put("Stuart", 100000.00);
		salaryMap.put("Marty", 90000.00);
		salaryMap.put("Jenny", 80000.00);
		Set<String> salarySet = salaryMap.keySet();
		Iterator<String> newItr = salarySet.iterator();
		while (newItr.hasNext()) {
			System.out.println(newItr.next());
		}
		Collection<Double> salaryCollection = salaryMap.values();
		Iterator<Double> collectionItr = salaryCollection.iterator();
		while(collectionItr.hasNext()) {
			System.out.println(collectionItr.next());
		}
		for (String name : salaryMap.keySet()) { 
			double salary = salaryMap.get(name);
			System.out.println(name + "'s salaray is " + salary + ".");
		}
	}
	
	/*
	 * LISTS
	 */
	
	// Takes in ArrayList and continually removes from it
	// This method is designed to work only with ArrayList objects
	public static void removeOddsArrayList(ArrayList<String> list) {
		int i = 0;
		while (i<list.size()) {
			String element = list.get(i);
			if (element.length() % 2 != 0) {
				list.remove(i);
			} else {
				i++;
			}
		}
	}
	
	// Like the above ArrayList method, this is slow, but for a different reason
	// To get to each new node, a separate traversal from the beginning or end of the list is necessary
	public static void slowRemoveOddsLinkedList(LinkedList<String> list) {
		int i = 0;
		while (i<list.size()) {
			String element = list.get(i);
			if (element.length() % 2 == 0) {
				list.remove(i);
			} else {
				i++;
			}
		}
	}
	// Linked list with an iterator ends up being more efficient for continuous removals
	// This method is designed to work with any collection of ADT List
	// Note that the parameter itself must also be declared with type list
	/*
	 * Linked lists offer more efficient removals towards the beginning because
	 * elements do not need to be shifted to the left each time a removal
	 * occurs. Since nodes are not stored contiguously, however, a linked list
	 * must be traversed from the very beginning each time another element is to
	 * be accessed. Iterators are a quick fix to this; they store the value of each
	 * node sequentially in just a single iteration. 
	*/
	public static void removeOddsLinkedList(List<String> list) {
		Iterator<String> myIterator = list.iterator();
		while (myIterator.hasNext()) {
			String element = myIterator.next();
			if (element.length() % 2 != 0) {
				myIterator.remove();
			}	
		}
	}
	
	public static String longest(List<String> list) {
		Iterator<String> myItr = list.iterator();
		String result = myItr.next();
		while (myItr.hasNext()) {
			String current = myItr.next();
			if (current.length() > result.length()) {
				result = current;
			}
		}
		return result;
	}
	
	// Primes up until max parameter
	// Linked lists are necessary because continual removal of the first element is too slow for ArrayLists
	public static List<Integer> sieve(int max) {
		List<Integer> numbers = new LinkedList<Integer>();
		List<Integer> primes = new LinkedList<Integer>();
		
		for (int i = 2; i <= max; i++) {
			numbers.add(i);
		}
		
		while (!numbers.isEmpty()) {
			int front = numbers.remove(0);
			primes.add(front);
			Iterator<Integer> myItr = numbers.iterator();
			while (myItr.hasNext()) {
				int current = myItr.next();
				if (current % front == 0) {
					myItr.remove();
				}
			}
		}
		return primes;
	}
	
	/*
	 * SETS
	 */
	
	public static boolean hasDuplicates(List<Integer> list) {
		Set<Integer> testSet = new HashSet<Integer>(list);
		return testSet.size() != list.size();
	}
	
	public static Set<Integer> returnDuplicates(List<Integer> list1, List<Integer> list2) {
		Set<Integer> intersect = new TreeSet<Integer>(list1); // set operations modify input, so we need auxiliary input
		intersect.retainAll(list2);
		return intersect;
	}
	
}
