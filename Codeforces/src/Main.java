/*import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int d1 = sc.nextInt();
        int d2 = sc.nextInt();
        int d3 = sc.nextInt();

        int []value = new int[4];
        value[0] = (2*d1) + (2*d2);
        value[1] = d1 + d2 + d3;
        value[2] = (2*d1) + (2*d3);
        value[3] = (2*d2) + (2*d3);
        int min = value[0];

        for(int i = 1; i<4; i++)
            if(min>value[i])
                min = value[i];

        System.out.print(min);
    }
}*/

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int []f = new int[n];
        int []f1 = new int [n];
        int []b = new int[m];
        int []a = new int[m];
        boolean flag = false;

        for(int i = 0; i<n; i++) {
            f[i] = sc.nextInt();
            f1[i] = f[i];
        }

        for(int i = 0; i<m; i++)
            b[i] = sc.nextInt();

        if(n!=m){
            System.out.println("Impossible");
            System.exit(0);
        }

        boolean flag1 = true;
        for (int i = 1; i < n; i++) {
            if(f[0]!=f[i]) {
                flag1 = false;
                break;
            }
        }
        if(flag1 == true){
            System.out.println("Ambiguity");
            System.exit(0);
        }


        for (int i = 0; i < n; i++) {
            for (int j = i; j<n; j++){
                if (f1[j]<f1[i]){
                    int g = f1[i];
                    f1[i] = f1[j];
                    f1[j] = g;
                }
            }
        }

        for (int i = 0; i<n; i++){
            if (f1[i]!=i+1){
                System.out.println("Impossible");
                System.exit(0);
            }
        }

        System.out.println("Possible");

        for(int i = 0; i<m; i++) {
            a[i] = b[f[i]-1];
            System.out.print(a[i] + " ");
        }
    }
}

