import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;
public class Main {

    public static String randomWord() throws FileNotFoundException {
        File dictionary = new File("newnouns.txt");
        Scanner scanner = new Scanner(dictionary);

        String line = scanner.nextLine();

        Map<Integer, String> words = new HashMap<>();
        int count = 0;

        while (scanner.hasNextLine()) {
            words.put(count, scanner.nextLine());
            count++;
        }

        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        scanner.close();
        return words.get(randomNumber);
    }

    public static void otgadivanieBukv() throws FileNotFoundException {
        int countMistake = 0;
        String word = randomWord();              // загаданное слово
        char[] wordChar = word.toCharArray();        // слово в мапссив чаров
        char[] lastWord = new char[wordChar.length]; // lastWord массив для содержания и вывода уже отгаданного

        for (int i = 0; i < lastWord.length; i++) {
            lastWord[i] = '_';    // ставит "_" в  новом массиве вместо отсутствующих букв
        }
        int countWin = wordChar.length;// счетчик отгаданного,чтобы закончить игру

        StringBuilder rightLetter = new StringBuilder(); //для  вывода отгаданных букв
        StringBuilder wrongLetter = new StringBuilder(); //для вывода ошибок

        System.out.println(hangmanPicture(countMistake));
        String firstOutEmptyWord=new String(lastWord);
        System.out.println("Слово загадано, отгадывай по одной букве на киррилице, при вводе нескольких учитывается только первая");
        System.out.println(firstOutEmptyWord);

        while ( countMistake < 7 && countWin>0) {

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();

            // ниже введенная букве перреводится в массив чаров
            //далее сравнивается превый элемент массива
            char[] sch = input.toCharArray();


            // ниже проверка на буквенный ввод
            if (!input.matches("[а-яА-ЯёЁ]+") || input.isEmpty() ) {
                System.out.println("Некорректный ввод, Введите кириллицу");
                //ниже вывод состояния виселицы при не корректном вводе
                System.out.println(hangmanPicture(countMistake));
                String result = new String(lastWord);
                System.out.println(result);
                System.out.print("Отгаданые буквы: " + rightLetter + " ");
                System.out.println("|  Ошибки: " + wrongLetter + " ");
            } else { //начало основного цикла

                if (word.contains(input) && !lastWord.toString().contains(input) && !rightLetter.toString().contains(input)
                        && !wrongLetter.toString().contains(input))  // (|слово содержит input && конечный массив не содержит input-
                // стрингбилдер с отгаданными не содержит input && стрингбилдер с ошибками не содержит input|)
                {
                    // System.out.println(countWin);
                    for (int i = 0; i < wordChar.length; i++) {
                        if (wordChar[i] == sch[0]) {
                            lastWord[i] = wordChar[i];
                            countWin--;
                        } else if (lastWord[i] == 0) {
                            lastWord[i] = '_';
                        }
                    }
                    rightLetter.append(sch[0]);
                } else if (!word.contains(input) && !wrongLetter.toString().contains(input) && input.matches("[a-zA-Zа-яА-Я]+")) {
                    countMistake++;          // счетчик ошибок и добавление ошибок в стрингбилдер// добавляется если содержит только бувы
                    wrongLetter.append(sch[0]);
                } else if (rightLetter.toString().contains(input) || wrongLetter.toString().contains(input)  ) {
                    System.out.println("Такая буква уже введена, введите другую.");
                }

                //ниже вывод состояния виселицы
                System.out.println(hangmanPicture(countMistake));
                String result=new String(lastWord);
                System.out.println(result);
                System.out.print("Отгаданые буквы: " + rightLetter + " ");
                System.out.println("|  Ошибки: " + wrongLetter + " ");
                //конец основного цикла
            }

        }

        if (countMistake >= 7 && rightLetter.toString().length() != lastWord.length) {
            System.out.println();
            System.out.println("Проигрыш, загаданное слово: " + word);
            startOrFinish();
        } else if (countMistake < 7 || rightLetter.toString().length() == lastWord.length) {
            System.out.println();
            System.out.println("              Победа! Ура!");
            System.out.println("          Загаданное слово: " + word);
            startOrFinish();
        }

    }

    public static String hangmanPicture(int countMistake){

        switch (countMistake) {
            case 0:
                System.out.println("_______________");
                System.out.println("|             |");
                System.out.println("|             |");
                System.out.println("|             |");
                System.out.println("|             |");
                System.out.println("|_____________|");
                break;
            case 1:
                System.out.println("_______________");
                System.out.println("|      |      |");
                System.out.println("|             |");
                System.out.println("|             |");
                System.out.println("|             |");
                System.out.println("|_____________|");
                break;
            case 2:
                System.out.println("_______________");
                System.out.println("|      |      |");
                System.out.println("|     [oo]    |");
                System.out.println("|             |");
                System.out.println("|             |");
                System.out.println("|_____________|");
                break;
            case 3:
                System.out.println("_______________");
                System.out.println("|      |      |");
                System.out.println("|     [oo]__  |");
                System.out.println("|             |");
                System.out.println("|             |");
                System.out.println("|_____________|");
                break;
            case 4:
                System.out.println("_______________");
                System.out.println("|      |      |");
                System.out.println("|   __[oo]__  |");
                System.out.println("|             |");
                System.out.println("|             |");
                System.out.println("|_____________|");
                break;
            case 5:
                System.out.println("_______________");
                System.out.println("|      |      |");
                System.out.println("|   __[oo]__  |");
                System.out.println("|     (~~)    |");
                System.out.println("|             |");
                System.out.println("|_____________|");
                break;
            case 6:
                System.out.println("_______________");
                System.out.println("|      |      |");
                System.out.println("|   __[oo]__  |");
                System.out.println("|     (~~)    |");
                System.out.println("|        |    |");
                System.out.println("|_____________|");
                break;
            case 7:
                System.out.println("_______________");
                System.out.println("|      |      |");
                System.out.println("|   __[oo]__  |");
                System.out.println("|     (~~)    |");
                System.out.println("|     |  |    |");
                System.out.println("|_____________|");
                break;
        }
        return "";
    }

    public static void startOrFinish() throws FileNotFoundException {
        System.out.println();
        System.out.println("              Игра Виселица ");
        System.out.println("Чтобы начать введите 1. Чтобы выйти нажмите 2");
        Scanner sc = new Scanner(System.in);

        boolean loop=true;
        while (loop){
            String startOrFinish = sc.nextLine();
            if (startOrFinish.equals("1")){
                otgadivanieBukv();
                loop=false;
            }else if(startOrFinish.equals("2")){
                loop=false;

            }else if(!startOrFinish.equals("1") &&!startOrFinish.equals("2") && !startOrFinish.isEmpty() ){
                System.out.println("Не корректный ввод, введите 1 чтобы начать, 2 чтобы выйти");
                // эксепш при ввобе букв

            }else if (startOrFinish.isEmpty()){
                System.out.print("Пустой ввод, введите 1 чтобы начать, 2 чтобы выйти ");
                continue;
            }

        }

    }


    public static void main(String[] args) throws FileNotFoundException {

        startOrFinish();
    }
}
