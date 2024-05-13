import java.util.*;
import java.io.*;

class Main {

    public static int SimpleAdding(int num) {
        int sum=0;
        for(int i=0;i<=num;i++){
            sum=sum+i;
        }
        num =sum;
        return num;
    }

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Lütfen Sayınızı Giriniz: ");
        int n = s.nextInt();
        System.out.print(SimpleAdding(n));
    }

}