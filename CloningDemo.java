package src.com.code;

/**
 * @author namit.sharma
 * 
 */
public class CloningDemo {

	public static void main(String[] args) {
		System.out
				.println("Simple reference pointing >> orginal will change after making changes in the new reference");
		Test ob1 = new Test();
		System.out
				.println("ob1 original -- " + ob1.var1 + " " + ob1.var2 + " " + ob1.dummy.aVar + " " + ob1.dummy.bVar);
		// Creating a new reference variable ob2 pointing to same address as ob1
		Test ob2 = ob1;
		// Any change made in ob2 will be reflected in ob1
		ob2.var1 = 100;
		ob2.dummy.aVar = 1000;
		System.out.println("ob1 changed -- " + ob1.var1 + " " + ob1.var2 + " " + ob1.dummy.aVar + " " + ob1.dummy.bVar);
		System.out
				.println("ob2 original -- " + ob2.var1 + " " + ob2.var2 + " " + ob2.dummy.aVar + " " + ob2.dummy.bVar);
		System.out.println("================================================================================");

		System.out.println("Shallow Cloning >> original primitives won't change, but non primitives will change ");
		// Test test = new Test();
		// Type mismatch: cannot convert from Object to Test
		// Test test2 = test.clone();
		CloneTest cloneTest = new CloneTest();
		try {
			cloneTest.clone();
		} catch (CloneNotSupportedException e) {
			// e.printStackTrace();
			System.err.println("Exception since Cloneable interface is not implemented!!");
		}

		System.out.println("----------------- Trying after implementing Cloneable interface -----------------");
		CloneableTest cloneableTest = new CloneableTest();
		CloneableTest newCloneable = null;
		try {
			newCloneable = (CloneableTest) cloneableTest.clone();
			newCloneable.var1 = 99;
			newCloneable.var2 = 99;
			newCloneable.dummy.aVar = 99;
			newCloneable.dummy.bVar = 99;
		} catch (CloneNotSupportedException e) {
			// e.printStackTrace();
			System.err.println("Exception since Cloneable interface is not implemented!!");
		}
		System.out.println("Original Before : " + cloneableTest.var1 + " & " + cloneableTest.var2 + " & "
				+ cloneableTest.dummy.aVar + " & " + cloneableTest.dummy.bVar);
		System.out.println("Cloned   : " + newCloneable.var1 + " & " + newCloneable.var2 + " & "
				+ newCloneable.dummy.aVar + " & " + newCloneable.dummy.bVar);
		System.out.println("Original After : " + cloneableTest.var1 + " & " + cloneableTest.var2 + " & "
				+ cloneableTest.dummy.aVar + " & " + cloneableTest.dummy.bVar);

		System.out.println("================================================================================");

		System.out.println("Deep Cloning >> will original change ?? No, neither primitives nor non primitives");
		CloneableDeepTest cloneableDeepTest = new CloneableDeepTest();
		CloneableDeepTest cloneableDeepTest2 = null;
		try {
			cloneableDeepTest2 = (CloneableDeepTest) cloneableDeepTest.clone();
			cloneableDeepTest2.var1 = 55;
			cloneableDeepTest2.var2 = 66;
			cloneableDeepTest2.dummy.aVar = 77;
			cloneableDeepTest2.dummy.bVar = 88;
		} catch (CloneNotSupportedException e) {
			// e.printStackTrace();
			System.err.println("Exception since Cloneable interface is not implemented!!");
		}
		System.out.println("Original Before : " + cloneableDeepTest.var1 + " & " + cloneableDeepTest.var2 + " & "
				+ cloneableDeepTest.dummy.aVar + " & " + cloneableDeepTest.dummy.bVar);
		System.out.println("Cloned   : " + cloneableDeepTest2.var1 + " & " + cloneableDeepTest2.var2 + " & "
				+ cloneableDeepTest2.dummy.aVar + " & " + cloneableDeepTest2.dummy.bVar);
		System.out.println("Original After : " + cloneableDeepTest.var1 + " & " + cloneableDeepTest.var2 + " & "
				+ cloneableDeepTest.dummy.aVar + " & " + cloneableDeepTest.dummy.bVar);

		System.out.println("========= The End - Visit thecodebuddy.blogspot.in for more stuff ===============");
	}
}

// Class to test references
class Test {
	int var1, var2;
	Dummy dummy = new Dummy();

	Test() {
		var1 = 10;
		var2 = 20;
		dummy.aVar = 30;
		dummy.bVar = 40;
	}
}

// Class to show cloning failure
class CloneTest {
	int var1, var2;
	Dummy dummy = new Dummy();

	CloneTest() {
		var1 = 10;
		var2 = 20;
		dummy.aVar = 30;
		dummy.bVar = 40;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

// Class to test shallow cloning
class CloneableTest implements Cloneable {
	int var1, var2;
	Dummy dummy = new Dummy();

	CloneableTest() {
		var1 = 10;
		var2 = 20;
		dummy.aVar = 30;
		dummy.bVar = 40;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

// Class to test deep cloning
class CloneableDeepTest implements Cloneable {
	int var1, var2;
	Dummy dummy = new Dummy();

	CloneableDeepTest() {
		var1 = 10;
		var2 = 20;
		dummy.aVar = 30;
		dummy.bVar = 40;
	}

	public Object clone() throws CloneNotSupportedException {
		CloneableDeepTest cloneableDeepTest = (CloneableDeepTest) super.clone();
		cloneableDeepTest.dummy = new Dummy();
		return cloneableDeepTest;
	}
}

// Auxiliary class for common use
class Dummy {
	int aVar;
	int bVar;
}
