/*
 * MEMORY OBSERVATORY — REFLECTION
 *
 * 1. When you changed p2.x to 999, p1.x also became 999. Explain in one sentence
 *    why, using the words "stack", "heap", and "reference":
 *    [your answer]
 *
 * 2. How many stack frames existed at the deepest point of factorial(5)?
 *    (Count them in the Frames panel)
 *    [your answer]
 *
 * 3. After `p = new Point(20, 30)`, can your program ever access Point(5, 10) again?
 *    What does this mean for GC?
 *    [your answer]
 *
 * 4. What error would you see if you called causeStackOverflow()?
 *    Which memory region is exhausted?
 *    [your answer]
 */
package Phase_00;

public class MemoryObservatory {

    // Represents an object that lives on the heap
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    // Demonstrates: primitives vs references
    static void primitiveVsReference() {
        int a = 10;          // lives on the stack
        int b = a;           // b is a COPY of a's value
        b = 99;              // changing b does NOT change a

        Point p1 = new Point(1, 2);   // p1 is a reference (stack), object is on heap
        Point p2 = p1;                // p2 is another reference to the SAME object
        p2.x = 999;                   // this changes the object BOTH p1 and p2 point to

        System.out.println("=== Primitive vs Reference ===");
        System.out.println("a = " + a + ", b = " + b);     // a is still 10
        System.out.println("p1 = " + p1);                   // p1.x is now 999
        System.out.println("p2 = " + p2);                   // p2.x is also 999
        System.out.println("Same object? " + (p1 == p2));   // true
    }

    // Demonstrates: stack frames — each call gets its own frame
    static int factorial(int n) {
        // Set a breakpoint here and watch 'n' change per frame
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // Demonstrates: StackOverflowError
    static void causeStackOverflow() {
        causeStackOverflow(); // no base case — infinite recursion
    }

    // Demonstrates: heap allocation and GC eligibility
    static void demonstrateGCEligibility() {
        Point p = new Point(5, 10);   // object allocated on heap
        System.out.println("\n=== GC Eligibility ===");
        System.out.println("Object created: " + p);

        p = new Point(20, 30);         // p now points to a NEW object
        // the first Point(5, 10) is now unreachable
        // it is eligible for GC — we cannot access it

        System.out.println("p now points to: " + p);
        System.out.println("The first Point is now garbage — unreachable, eligible for GC");
        System.out.println("Available heap before forced GC attempt: "
                + Runtime.getRuntime().freeMemory() / 1024 + " KB");
    }

    public static void main(String[] args) {
        primitiveVsReference();

        System.out.println("\n=== Factorial (watch stack frames in debugger) ===");
        System.out.println("5! = " + factorial(5));

        demonstrateGCEligibility();

        // Uncomment to observe StackOverflowError — run separately
        // causeStackOverflow();
    }
}
