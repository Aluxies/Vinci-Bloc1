public class TestDrapeauBicolore {

    public static void main(String[] args) {
        System.out.println("  TEST 1");
        DrapeauBicolore d1 = new DrapeauBicolore(new int[] { 43, 18, 39, 76, 27, 85, 52 });
        System.out.println(d1.toString());
        d1.tri();
        System.out.println(d1.toString());
        System.out.println();

        System.out.println("  TEST 2");
        DrapeauBicolore d2 = new DrapeauBicolore(new int[] { 12, 18, 6, 24, 30 });
        System.out.println(d2.toString());
        d2.tri();
        System.out.println(d2.toString());
        System.out.println();

        System.out.println("  TEST 3");
        DrapeauBicolore d3 = new DrapeauBicolore(new int[] { 15, 7, 9, 21, 39, 3 });
        System.out.println(d3.toString());
        d3.tri();
        System.out.println(d3.toString());
        System.out.println();

        System.out.println("  TEST 4");
        DrapeauBicolore d4 = new DrapeauBicolore(15);
        System.out.println(d4.toString());
        d4.tri();
        System.out.println(d4.toString());
        System.out.println();
    }

}
