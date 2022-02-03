package cg.nic.bilaspur.covidenquiry;

public class Test {
    public static void main(String[] args) {
        if (getBigger(11, 10))
            System.out.println("true");
        else
            System.out.println("false");
    }
    public static boolean getBigger(int a, int b){
        return a > b;
    }
}
