import java.util.Scanner;

public class FourthChallenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Satir sayisi giriniz:");
        int rowNumber = scanner.nextInt();
        int row =1;
        for (int i = 0; i< rowNumber *(rowNumber-1)+1; i++){
            System.out.print("*");
            if (i== row *(row -1)){
                row++;
                System.out.println();
            }
        }
    }
}
