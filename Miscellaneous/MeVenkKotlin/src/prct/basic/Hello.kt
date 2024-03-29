import java.util.Date
import kotlin.reflect.KProperty

fun main(args: Array<String>) {

	println("Hello, World!" + args.asList())

	variableDeclerations()

	arraysAndCollections()

	rangeAndLoops()

	controlFlow()

	classObjectsInheritanceInterface()

	visibilityControl()

	extension()

	dataClasses()

	sealedClass()

	generics()

	delegation()

	functions()

	destructuringDeclarations()

	exceptionHandling()

}


fun variableDeclerations() {

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

}


fun arraysAndCollections() {

	val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
	println("Int array ${intArray.asList()}")


	val mutableList: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
	val readOnlyList: List<Int> = mutableList;

	println("Mutable list - ${mutableList}");
	mutableList.add(10)
	println("Mutable list after addition - ${mutableList}");
	println("Read only list - ${readOnlyList}")


	val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
	println("First -- ${items.first()}")
	println("Last -- ${items.last()}")
	println("Even numbers -- ${items.filter { it % 2 == 0 }}")


	val hashMap = hashMapOf("one" to 1, "two" to 2)
	println("Map - ${hashMap}")
	println("Map of - ${hashMap["one"]}")


	val hashSet = hashSetOf("a", "b", "c", "d", "b")
	println("hashSet - ${hashSet}")

}


fun rangeAndLoops() {

	for (jIndex in 1..4)
		print(jIndex)

	println()

	val rangeIndex: Int = 2

	if (rangeIndex in 1..10) {
		println("Found index - ${rangeIndex}")
	}

}


fun controlFlow() {

	val aVal: Int = 5
	val bVal: Int = 2
	val maxVal: Int

	if (aVal > bVal) {
		maxVal = aVal;
	} else {
		maxVal = bVal
	}

	println("Max - ${maxVal}")

	val maxValExp: Int = if (aVal > bVal) aVal else bVal
	println("Max val exp - ${maxValExp}")


	val xVal: Int = 5

	when (xVal) {
		1 -> println("xVal == 1")
		2 -> println("xVal == 2")
		else -> println("Neither 1 nor 2")
	}

	when (xVal) {
		1, 2 -> println("xVal is either 1 or 2")
		else -> {
			println("xVal is neither 1 nor 2")
		}
	}


	val itemsForLoop = listOf(10, 52, 38, 42)
	for (i in itemsForLoop) println("Iten - ${i}")

	println()

	for ((index, value) in itemsForLoop.withIndex()) {
		println("Index - ${index}  | Value - ${value}");
	}


	var whileX: Int = 0
	println("While loop")

	while (whileX <= 10) {
		println(whileX)
		whileX++
	}


	var doWhileX: Int = 0
	println("Do While loop")

	do {
		doWhileX += 10
		println("Inside Do - ${doWhileX}")
	} while (doWhileX < 50)


	fun doubleMe(x: Int): Int {
		return x * 2
	}

	val xDoubleFun: Int = 10
	println("Calling Double fun - ${doubleMe(xDoubleFun)}")



	println("Break & Continue")

	forLoop@ for (x in 1..10) {
		if (x == 5) {
			println("Breaking loop @ ${x}")
			break@forLoop
		} else {
			println("Continue - ${x}")
			continue@forLoop
		}
	}

}


fun classObjectsInheritanceInterface() {

	var objMyClass = myClass()
	objMyClass.printMe()


	val nestedDemo = Outer.Nested().foo()
	println(nestedDemo)

	val nestedClassInnerFun = Outer().NestedClassInner().foo()
	println(nestedClassInnerFun)


	println("Anonymous Inner Class")
	val humanInstance: Human = object : Human {
		override fun think() {
			println("Overriden fun from interface - Anonymoys inner class")
		}
	}
	humanInstance.think()


	val person1 = Person("Venkatesh", 27)
	println("Name ${person1.firstName}");
	println("Age ${person1.age}");


	var secondaryConstInst = SecondaryConst("Venkatesh", 27)
	println("FN: ${secondaryConstInst.firstName} | Message ${secondaryConstInst.message} | Age ${secondaryConstInst.age}")


	val subClassInst = SubClass()
	subClassInst.supFun()

	val subClassOverridemFunInst = SubClassOverridemFun()
	subClassOverridemFunInst.supFun()
	subClassOverridemFunInst.supFunOpen()


	val exampleInterfaceImpInst = ExampleInterfaceImp()
	println("Overriden var - ${exampleInterfaceImpInst.myVar}")
	println("Abs met ${exampleInterfaceImpInst.absMethod()}")


	val multipleInterfaceExampleInst = multipleInterfaceExample()
	multipleInterfaceExampleInst.printMe()
	multipleInterfaceExampleInst.printMeToo()

}


fun visibilityControl() {

	// private, protected(currently not available ), internal, public(default)


}


fun extension() {

	println("Extension")

	val a1 = Alien()
	a1.skills = "JAVA"

	val a2 = Alien()
	a2.skills = "SQL"

	val a3 = Alien()
	a3.skills = a1.addMySkills(a2)
	a3.printMySkills()


	println("Object Extension")

	println(companionParentClassA.callCompanionObjectFun())

}

fun dataClasses() {

	println("dataClasses")

	data class DataClass(val name: String, var age: Int)

	val dataClassInt: DataClass = DataClass("Venkatesh", 27)
	println("Name - ${dataClassInt.name}")
	println("Age - ${dataClassInt.age}")
	dataClassInt.age = 25
	println("DataClass - ${dataClassInt.toString()}")
	println("Hash - ${dataClassInt.hashCode()}")

}


fun sealedClass() {


//	val sealedClassObj: SealedClass = SealedClass.S1()
//
//	val type = when (sealedClassObj) {
//		is SealedClass.S1 -> "S1"
//		is SealedClass.S2 -> "S2"
//	}
//
//	println(type)

}

fun generics() {

	println("generics")

	var integer: Int = 1
	var number: Number = integer
	println(number)



	class GenericsExample<out T>(input: T) {
		init {
			println("Input provided - ${input}")
		}
	}


	val genericsInstInt: GenericsExample<Int> = GenericsExample<Int>(25)
	println(genericsInstInt)
	val genericsInstString: GenericsExample<String> = GenericsExample<String>("VENKY")
	println(genericsInstString)

}


fun delegation() {


	println("delegation")

	val delInst: DelegationInterface = DelegationInterfaceImpl()
	DelegationDerivedClass(delInst).printDate()


	println("Delegation - property")

	class Delegate {

		operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
			return "$thisRef, thank you for delegating '${property.name}' to me!"
		}

		operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
			println("$value has been assigned to '${property.name} in $thisRef.'")
		}

	}

	class Example {
		var p: String by Delegate()
	}

}


fun functions() {

	println("Custom lambda")

	val myLambda: (String) -> Unit = { s: String -> println("Hello " + s) }
	val sampleStr = "Venkatesh"
	myLambda(sampleStr)

	println("Custom lambda - multiple inputs and return value")
	val lambdaMulInpAndReturn: (String, Int) -> String = { s: String, i: Int -> s + "||" + i }
	println(lambdaMulInpAndReturn("Venkatesh", 25))


	println("Lambda inline")

	fun customLambdaInline(action: (String, Int) -> String, s: String, i: Int) {
		println("Lambda inline - inside function")
		println(action(s, i))
	}

	customLambdaInline(lambdaMulInpAndReturn, "venky", 25)

}

fun destructuringDeclarations() {

	println("destructuringDeclarations")

	data class DataClass(val name: String, var age: Int)

	val dataClassInt = DataClass("Vekatesh", 27)
	val (name, age) = dataClassInt
	println("Name - ${name}  -  Age - ${age}")

}

fun exceptionHandling() {

	println("exceptionHandling")

	try {
		val valueString: String = "Venkatesh"
		println(valueString.toInt())
	} catch (e: Exception) {
		println("Exception occured")
		e.printStackTrace()
	} finally {
		println("Exception handling - finally")
	}

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


class myClass {

	private var name: String = "Venkatesh"

	fun printMe() {
		println("My class -- ${Date()}")
	}

}

class Outer {
	private val welcomeMessage = "Outer class private message"

	class Nested {
		fun foo() = "Nested class fun"
	}

	inner class NestedClassInner {
		fun foo() = welcomeMessage;
	}
}


interface Human {
	fun think()
}


class Person(val firstName: String, var age: Int) {
}


class SecondaryConst(val firstName: String, var age: Int) {
	val message: String = "String message"

	constructor(name: String, age: Int, message: String) : this(name, age) {

	}
}


open class SuperClass {
	fun supFun() {
		println("Sup fun")
	}

	open fun supFunOpen() {
		println("Sup fun")
	}

}


class SubClass : SuperClass()

class SubClassOverridemFun : SuperClass() {
	override fun supFunOpen() {
		println("Overrriden fun supFunOpen")
	}
}

interface ExampleInterface {

	var myVar: Int
	fun absMethod(): String

	fun defaultFun() {
		println("Default Function Interface")
	}

}


class ExampleInterfaceImp : ExampleInterface {

	override var myVar = 27

	override fun absMethod() = "Abs Overriden in Impl"

}


interface A {
	fun printMe() {
		println(" method of interface A")
	}
}

interface B {
	fun printMeToo() {
		println("I am another Method from interface B")
	}
}

// implements two interfaces A and B
class multipleInterfaceExample : A, B


class Alien {
	var skills: String = "null"
	fun printMySkills() {
		println(skills)
	}
}

fun Alien.addMySkills(a: Alien): String {
	var a4: Alien = Alien()
	a4.skills = this.skills + ", " + a.skills
	return a4.skills
}

class companionParentClassA {

	companion object {
		fun callCompanionObjectFun(): String {
			return "callCompanionObjectFun"
		}
	}

}

//sealed class SealedClass {
//	class S1 : SealedClass()
//	class S2 : SealedClass()
//}


interface DelegationInterface {
	fun printDate()
}

class DelegationInterfaceImpl : DelegationInterface {
	override fun printDate() {
		println(Date())
	}
}

class DelegationDerivedClass(d: DelegationInterface) : DelegationInterface by d 

