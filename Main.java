import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rand = getRandomNo();
        System.out.println("Okay, let's start a game!");
        game(rand.toCharArray());
    }

    public static void game(char[] rand){
        boolean flag = true;
        int i = 1;
        Scanner sc = new Scanner(System.in);
        while(flag){
            System.out.println("Turn "+ i++ + ":");
            char[] input = sc.next().toCharArray();
            flag = grade(input,rand);
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
    public static int checkLength(String length){
        try {
            int len = Integer.parseInt(length);
            if(len==0){
                throw new Exception();
            }
            return len;
        }
        catch(Exception e){
            System.out.println("Error: "+length +" isn't a valid number.");
            System.exit(0);
            return 0;
        }
    }
    public static void checkSymbolLen(int charlen,int len){
        if(charlen < len){
            System.out.println("Error: it's not possible to generate a code with a length of "+ len+" with "+ charlen+" unique symbols.");
            System.exit(0);
        }
        else if(charlen>36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
    }
    public static String getRandomNo() {
        Scanner sc = new Scanner(System.in);
        String output = new String();
        System.out.println("Please, enter the secret code's length:");
        String inputlen = sc.nextLine();
        int len = checkLength(inputlen);
        System.out.println("Input the number of possible symbols in the code:");
        int charlen = sc.nextInt();
        checkSymbolLen(charlen,len);
        if (len > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
        } else {
            StringBuilder hashes = new StringBuilder();
            for(int i =0 ; i< len ; i++)
                hashes.append("*");
            List<Character> list = new ArrayList<>(List.of('a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't','u','v',  'w',  'x', 'y', 'z'));
            List<Character> randomList = new ArrayList<>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
            System.out.println("The secret is prepared:"+hashes.toString()  + " (0-9"+((charlen>10)?(", "+list.get(0)+"-"+list.get(charlen-11)):"" )+").");
            randomList.addAll(list.subList(0,charlen-10));
            Collections.shuffle(randomList);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < len; i++) {
                str.append(randomList.get(i));
            }
            output = str.toString();
        }
        return output;
    }

    public static boolean grade(char[] input,char[] rand) {
        char[] num = rand;
        int bull = 0;
        int cow = 0;
        for (int i = 0; i < rand.length; i++) {
            if (num[i] == input[i]) {
                bull++;
            }
        }
        for (int i = 0;i < rand.length; i++) {
            for (int j = 0;j < rand.length; j++) {
                if (i!=j && num[i] == input[j] ) {
                    cow++;
                }
            }
        }
        System.out.println(bull +" "+cow);
        printing(bull,cow);
        if(bull==rand.length)
            return false;
        else
            return true;
    }

    public static void printing(int bull,int cow){
        String str1 = (bull==0?"":(bull+" bull(s)"));
        String str2 = (cow>0&&bull>0)?" and ":"";
        String str3 = (cow==0?"":cow+" cow(s)") ;
        String str4 = (cow==0&&bull==0)?" None":"";
        System.out.println("Grade: "+ str1+ str2 +str3 + str4 +".");
    }
}
