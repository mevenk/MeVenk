fun main(args: Array<String>) {

	println("Hello, World!" + args.asList())

	val a: Int = 10000
	val d: Double = 100.00
	val f: Float = 100.00f
	val l: Long = 1000000004
	val s: Short = 10
	val b: Byte = 1

	println("Your Int Value is " + a);
	println("Your Double  Value is " + d);
	println("Your Float Value is " + f);
	println("Your Long Value is " + l);
	println("Your Short Value is " + s);
	println("Your Byte Value is " + b);


	val letter: Char    // defining a variable 
	letter = 'A'        // Assigning a value to it 
	println("$letter")


	val bool: Boolean
	bool = true;
	println("Boolean value is ${bool}")


	val rawString: String = "Raw String"
	val escapedString: String = "Escaped String\n"

	println("Escaped-" + escapedString)
	println("Raw-" + rawString)


	val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
	println("Int array ${intArray.asList()}")


	val mutableList: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
	val readOnlyList: List<Int> = mutableList;

	println("Mutable list - ${mutableList}");
	mutableList.add(10)
	println("Mutable list after addition - ${mutableList}");
	println("Read only list - ${readOnlyList}")


}