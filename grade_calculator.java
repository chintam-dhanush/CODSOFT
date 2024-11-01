import java.util.Scanner;

public class grade_calculator {
    public static void main(String[] args) {
        
        System.out.println("Enter the number of subjects:");
        int snum, totalscore = 0;
        Scanner sc = new Scanner(System.in);
        snum = sc.nextInt();

        int score[] = new int[snum];

        System.out.println("Enter the scores for each subject:");
        for (int i = 0; i < snum; i++) {
            score[i] = sc.nextInt();
        }

        for (int i = 0; i < snum; i++) {
            totalscore += score[i];
        }

        float Average_score =totalscore / snum;
        
        
        char grade;
        if (Average_score >= 90) {
            grade = 'A';
        } else if (Average_score >= 80) {
            grade = 'B';
        } else if (Average_score >= 70) {
            grade = 'C';
        } else if (Average_score >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        
        System.out.println("Your Totalmarks: " + totalscore);
        System.out.println("Average score: " + Average_score);
        System.out.println("Your grade: " + grade);    
    }
}



