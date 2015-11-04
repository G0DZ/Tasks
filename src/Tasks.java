import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Добавим оговорики:
// - Исходыне данные корректны, если не утверждается вероятность обратного.
// - Код написан в условиях дефицита времени, тут уж я сам виноват.
public class Tasks {
    //public static void main(String arg[]){
    void task1(){ //complete
        System.out.print("Введите количество строк: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))){
            String[] str = new String[n];
            int lMin = Integer.MAX_VALUE;
            int lMax = -1;
            for(int i = 0; i < n; i++){
                str[i] = br.readLine();
                int currentLenght = str[i].length();
                if(currentLenght < lMin)
                    lMin = currentLenght;
                if(currentLenght > lMax)
                    lMax = currentLenght;
            }
            System.out.println("Минимальная длина строки: "+lMin+"\nМаксимальная длина строки: "+lMax);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String arg[]){
    void task2(){ //complete
        System.out.print("Введите количество строк: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))){
            String[] str = new String[n];
            for(int i = 0; i < n; i++){
                str[i] = br.readLine();
            }
            //пусть и n2, зато мой любимый. при n < 10 вроде и вообще лучший
            for(int i = 1; i < n; i++){
                String currString = str[i];
                int previos = i - 1;
                while(previos >= 0 && str[previos].length() > currString.length()){
                    str[previos+1] = str[previos]; //двигаемся
                    str[previos] = currString;
                    previos--;
                }
            }
            System.out.println("Строки упорядочены:");
            for(int i = 0; i < n; i++){
                System.out.println(str[i]);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String arg[]){
    void task3(){ //complete
        System.out.print("Введите количество строк: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))){
            String[] str = new String[n];
            int sumLenght = 0;
            for(int i = 0; i < n; i++){
                str[i] = br.readLine();
                sumLenght+=str[i].length();
            }
            double avgLenght = sumLenght/n; //среднее
            System.out.println("Средняя длина строки: "+avgLenght);
            System.out.println("Вывод подходящих строк: ");
            for(int i = 0; i < n; i++){
                if(str[i].length() < avgLenght)
                    System.out.println("Строка "+(i+1)+": "+str[i]+" , длиной "+str[i].length());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String arg[]){
    void task4(){ //complete
        System.out.print("Введите количество слов: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Слова разделяются как пробелами, " +
                "так и символами переноса строки.\nВведите "+n+" слов(а): ");
        String[] str = new String[n];
        //слово - это до 1го пробела или \n
        int i = 0;
        while (i < n){
            if(s.hasNext())
                str[i++] = s.next();
        }
        System.out.println("Введены слова: ");
        for(String element : str)		//пробуем другой for
            System.out.println(element);
        int resultIndex = 0;
        int lMin = Integer.MAX_VALUE;
        for(i = 0; i < n; i++){
            String currentStr = str[i];
            int numberOfUnique = 0; //число уникальных символов в ней
            for(int j = 0; j < currentStr.length();){
                currentStr = currentStr.replaceAll(""+currentStr.charAt(j), "");
                numberOfUnique++;
            }
            if(numberOfUnique < lMin)
            {
                resultIndex = i;
                lMin = numberOfUnique;
            }
        }
        System.out.println("Слово с минимальным количеством различных символов: "+str[resultIndex]+
                ", число различных символов: "+lMin);
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String arg[]){
    void task5(){ //complete
        //Случайно выводил сами слова, а не их количество.
        //Таск исправлен на скорую руку.
        System.out.print("Введите количество слов: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Слова разделяются как пробелами, так и символами переноса строки.\nВведите "+n+
                " слов(а): ");
        String[] str = new String[n];
        //слово - это до 1го пробела или \n
        int i = 0;
        while (i < n){
            if(s.hasNext())
                str[i++] = s.next();
        }
        System.out.print("Введены слова: \n");
        for(String element : str)		//пробуем другой for
            System.out.println(element);
        //изучаем Pattern и Matcher, очень рад.
        Pattern p = Pattern.compile("[a-zA-z]+");
        ArrayList<String> list = new ArrayList<String>();
        for(i = 0; i < n; i++){
            //для каждой строки
            Matcher m = p.matcher(str[i]);
            if(m.matches())
                list.add(str[i]);
        }
        System.out.print("Выводим слова, содержащие только латинские буквы:\n");
        for(i = 0;i < list.size(); i++)
            System.out.println(list.get(i));
        int countPaly = 0;
        System.out.print("Среди них слова, содержащие равное число согласных и гласных:");
        for(i = 0; i < list.size(); i++){
            int notVowel = list.get(i).replaceAll("[^aAoOiIuUyYeE]", "").length();
            int vovel = list.get(i).replaceAll("[aAoOiIuUyYeE]", "").length();
            if(vovel == notVowel)
            {
                countPaly++;
                System.out.print("\n"+list.get(i));
            }
        }
        System.out.println("\n\nКоличество слов, в которых все буквы латинские: "+list.size());
        System.out.println("Среди них слов, содержащих равное число согласных и гласных:: "+countPaly);
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String arg[]){
    void task6(){ //complete
        System.out.print("Введите количество слов: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Слова разделяются как пробелами, так и символами переноса строки.\nВведите "+n+
                " слов(а): ");
        String[] str = new String[n];
        //слово - это до 1го пробела или \n
        int i = 0;
        while (i < n){
            if(s.hasNext())
                str[i++] = s.next();
        }
        System.out.print("Введены слова: \n");
        for(String element : str)		//пробуем другой for
            System.out.println(element);
        //getBytes()
        for(i = 0; i < n; i++){
            byte bb[] = str[i].getBytes();
            boolean wordState = true; //слово - подходит
            for(int j = 1; j < bb.length && wordState != false; j++){
                if(bb[j]<=bb[j-1])
                    wordState = false;	//а теперь нет.
            }
            if(wordState){
                System.out.println("Первое слово, в котором все коды символов идут в "+
                        "строгом порядке возрастания: "+str[i]);
                break;
            }
            if(!wordState && i == n-1)
                System.out.println("Нет слов, удовлетворяющих условию.");
        }
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String arg[]){
    void task7(){ //complete
        System.out.print("Введите количество слов: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Слова разделяются как пробелами, так и символами переноса строки.\nВведите "+n+" слов(а): ");
        String[] str = new String[n];
        //слово - это до 1го пробела или \n
        int i = 0;
        while (i < n){
            if(s.hasNext())
                str[i++] = s.next();
        }
        System.out.print("Введены слова: \n");
        for(String element : str)		//пробуем другой for
            System.out.println(element);
        //работаем на время, так что берем 4 лабу и модернизируем
        boolean haveAWord = false; //слова - нет
        for(i = 0; i < n && !haveAWord; i++){
            String currentStr = str[i];
            int numberOfUnique = 0; //число уникальных символов в ней
            for(int j = 0; j < currentStr.length();){
                currentStr = currentStr.replaceAll(""+currentStr.charAt(j), "");
                numberOfUnique++;
            }
            if(numberOfUnique == str[i].length())
            {
                System.out.println("Первое слово, в котором все символы различны: "+str[i]);
                haveAWord = true;
            }
        }
        if(!haveAWord)
            System.out.println("Нет слова, удовлетворяющего условию.");
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String arg[]){
    void task8(){ //complete
        System.out.print("Введите количество слов: ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println("Слова разделяются как пробелами, так и символами переноса строки.\nВведите "+n+" слов(а): ");
        String[] str = new String[n];
        //слово - это до 1го пробела или \n
        int i = 0;
        while (i < n){
            if(s.hasNext())
                str[i++] = s.next();
        }
        System.out.print("Введены слова: \n");
        for(String element : str)		//пробуем другой for
            System.out.println(element);
        //работаем на время, так что берем 5 лабу и модернизируем
        Pattern p = Pattern.compile("[0-9]+");
        ArrayList<String> list = new ArrayList<String>();
        for(i = 0; i < n; i++){
            //для каждой строки
            Matcher m = p.matcher(str[i]);
            if(m.matches())
                list.add(str[i]);
        }
        System.out.print("Выводим слова, содержащие только цифры:\n");
        for(i = 0;i < list.size(); i++)
            System.out.println(list.get(i));
        //логика. отразим и сравним.
        ArrayList<String> listOfPaly = new ArrayList<String>();
        for(i = 0; i < list.size(); i++){
            String reverseString = new StringBuilder(list.get(i)).reverse().toString();
            if(list.get(i).equals(reverseString)){
                listOfPaly.add(list.get(i));
                if(listOfPaly.size()==2)
                    break;
            }
        }
        if(listOfPaly.size() == 0)
            System.out.println("Нет слова, удовлетворяющего условию.");
        else
        if(listOfPaly.size() == 1)
            System.out.println("Первое и единственное слово-палиндром: "+listOfPaly.get(0));
        else
            System.out.println("Второе слово-палиндром: "+listOfPaly.get(1));
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String arg[]){
    void task9(){ //complete
        int k = 1;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++)
                System.out.printf("%3s"+" |", k++);
            System.out.print("\n-------------------------\n");
        }
        System.out.println("Завершение работы программы.");
    }

    //читал вот это давно-давно, даже нашел http://ithappens.me/story/10776
    //но времени в обрез, делаем упрощенно
    //public static void main(String args[]){
    void task10(String args[]){ //complete
        //Ограничения:
        // - агрументы - целые числа
        // - с комплексными не работаем
        // - не рассматриваем случаи, приводящие к потере точности
        int a = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        int b = Integer.parseInt(args[1]); 	// считаем, что агрумент размера - верен.
        int c = Integer.parseInt(args[2]); 	// считаем, что агрумент размера - верен.
        System.out.println("Получены коэффициенты: "+a+" "+b+" "+c+" ");
        if(a == 0){
            System.out.println("Единственный корень уравнения: х1 = "+(-c/b));
        }
        else{
            double d = b*b-4*a*c;
            if(d < 0.0){
                System.out.println("Уравнение не имеет вещественных корней.");
            }
            else{
                if(d==0.0){
                    System.out.println("Два сопадающих корня уравнения х = "+(-b/2*a));
                }
                else{
                    System.out.println("Первый корень уравнения: х1 = "+((-b-Math.sqrt(d))/(2*a)));
                    System.out.println("Второй корень уравнения: х2 = "+((-b+Math.sqrt(d))/(2*a)));
                }
            }
        }
    }

    //public static void main(String arg[]){
    void task11(){ //11 задание про месяцы, которых 12. watta?
        //complete
        Scanner s = new Scanner(System.in);
        //первый раз задание на корректность ввода.
        int n = 0;
        while(true){
            System.out.print("Введите номер месяца (1-12): ");
            try {
                n = Integer.parseInt(s.next());
                if(n > 12 || n < 1)
                    throw new NumberFormatException();
                else
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Повторите ввод. Необходимо ввести число от 1 до 12");
            }
        }
        //СВИЧ?? СЕРЬЕЗНО?
        //System.out.println(Month.of(n));
        switch(n){
            case 1:
                System.out.println("Январь");
                break;
            case 2:
                System.out.println("Февраль");
                break;
            case 3:
                System.out.println("Март");
                break;
            case 4:
                System.out.println("Апрель");
                break;
            case 5:
                System.out.println("Май");
                break;
            case 6:
                System.out.println("Июнь");
                break;
            case 7:
                System.out.println("Июль");
                break;
            case 8:
                System.out.println("Август");
                break;
            case 9:
                System.out.println("Сентябрь");
                break;
            case 10:
                System.out.println("Октябрь");
                break;
            case 11:
                System.out.println("Ноябрь");
                break;
            case 12:
                System.out.println("Декабрь");
                break;

        }
        s.close();
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task12(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        int k = Integer.parseInt(args[1]);// считаем, что агрумент столбца - верен.
        k--;									//а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        for(int i = 1; i < n; i++){
            int line[] = matrix[i];
            int previos = i - 1;
            while(previos >= 0 && matrix[previos][k] > line[k]){
                matrix[previos+1] = matrix[previos]; //двигаемся
                matrix[previos] = line;
                previos--;
            }
        }
        System.out.println("\nМатрица результата: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                System.out.printf("%3s", matrix[i][j]);
            System.out.println();
        }
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task13(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        int k = Integer.parseInt(args[1]);// считаем, что агрумент количества сдвигов - верен.
        k--;									//а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        int result[][] = new int[n][n];//дополнительная N*N
        for(int i = 0; i < n; i++){
            result[(i+k+1)%n] = matrix[i];
        }
        System.out.println("\nМатрица результата: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                System.out.printf("%3s", result[i][j]);
            System.out.println();
        }
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task14(String args[]){
        //complete
        //считаются строго возрастающие элементы
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        int currentResult = 1;
        int maxResult = 1;
        for(int i = 1; i < n*n; i++){
            //System.out.println(matrix[(i-1)/n][(i-1)%n]);
            //путешествия по матрице как по строке, например
            if(matrix[(i-1)/n][(i-1)%n] < matrix[i/n][i%n]){
                currentResult++;
            }
            else{
                if(currentResult > maxResult){
                    //System.out.println(i); //для тестирования
                    maxResult = currentResult;
                }
                currentResult = 1;
            }
        }
        if(currentResult > maxResult) //последняя проверка выпадает.
            maxResult = currentResult;
        System.out.println("\nНаибольшее число возрастающих элементов матрицы, идущих подряд: "+maxResult);
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task15(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        int sumBetweenPos = 0;
        boolean sumFlag = false; //плхая идея, но это флаг суммирования :(
        for(int i = 0; i < n; i++){
            sumFlag = false;
            for(int j = 0; j < n; j++){
                if(matrix[i][j] > 0)
                    if(!sumFlag)
                        sumFlag = true;
                    else
                        break; //после первой пары в каждой строке мы не считаем.

                else
                if(sumFlag)
                    sumBetweenPos+=matrix[i][j];

            }
        }
        System.out.println("\n  Сумма элементов, расположенных между первым и \n"
                + "вторым положительными элементами каждой строки: "+sumBetweenPos);
        System.out.println("  Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task16(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        //знаю, что можно было проще, но...
        int[][] result = new int[n][n];
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                result[n-1-j][i] = matrix[i][j];
            }
        }
        System.out.println("\nМатрица результата: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                System.out.printf("%3s", result[i][j]);
            System.out.println();
        }
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task17(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        double sumOfFstPart1 = 1.0;
        double sumOfFstCurr = 0.0;
        double sumOfSndPart1 = 1.0;
        double sumOfSndCurr = 0.0;
        int currMinr = 0;
        for(int i = 0; i < n; i++){
            sumOfFstPart1 = 1.0;
            sumOfSndPart1 = 1.0;
            currMinr = 2*n-i-1;
            for(int j = 0; j < n; j++){
                sumOfSndPart1*=matrix[j][currMinr%n];
                currMinr--;
                sumOfFstPart1*=matrix[j][(j+i)%(n)];
            }
            sumOfSndCurr+=sumOfSndPart1;
            sumOfFstCurr+=sumOfFstPart1;
        }
        System.out.println("\nОпределитель заданной матрицы: "+(sumOfFstCurr-sumOfSndCurr));
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task18(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        int lMax = Integer.MIN_VALUE;
        ArrayList<Integer> listRow = new ArrayList<Integer>(n);
        ArrayList<Integer> listColumn = new ArrayList<Integer>(n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] > lMax){
                    listRow.clear();
                    listColumn.clear();
                    listRow.add(i);
                    listColumn.add(j);
                    lMax = matrix[i][j];
                }
                if(matrix[i][j] == lMax){
                    if(!listRow.contains(i))
                        listRow.add(i);
                    if(!listColumn.contains(j))
                        listColumn.add(j);
                }
            }
        }
        System.out.println("\nМаксимальный элемент: "+lMax);
        System.out.print("Строки, его содержащие:");
        for(int i = 0; i < listRow.size(); i++)
            System.out.print(listRow.get(i)+" ");
        System.out.println();
        System.out.print("Столбцы, его содержащие:");
        for(int i = 0; i < listColumn.size(); i++)
            System.out.print(listColumn.get(i)+" ");
        System.out.println();
        int[][] result = new int[n][n];
        System.out.println("\nМатрица результата: ");
        int k = 0;
        int m = 0;
        for(int i = 0; i < n; i++){
            if(!listRow.contains(i)){
                m = 0;
                for(int j = 0; j < n; j++){
                    if(!listColumn.contains(j)){
                        result[k][m] = matrix[i][j];
                        m++;
                    }
                }
                k++;
            }
        }
        for(int i = 0; i < n-listRow.size(); i++){
            for(int j = 0; j < n-listColumn.size(); j++)
                System.out.printf("%3s", result[i][j]);
            System.out.println();
        }
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task19(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        //времени мало, меняем 18 таск
        //проблема - каждый элемент будет пройден 2 раза
        ArrayList<Integer> listRow = new ArrayList<Integer>(n);
        ArrayList<Integer> listColumn = new ArrayList<Integer>(n);
        for(int i = 0; i < n; i++){
            int contentsByIRow = 0;
            int contentsByIColumn = 0;
            for(int j = 0; j < n; j++){
                    if(matrix[i][j] == 0) {
                        contentsByIRow++;
                    }
                    if(matrix[j][i] == 0) {
                        contentsByIColumn++;
                    }
                }
            if(contentsByIRow==n)
                listRow.add(i);
            if(contentsByIColumn==n)
                listColumn.add(i);
            }
        System.out.print("\nСтроки, содержащие только 0: ");
        for(int i = 0; i < listRow.size(); i++)
            System.out.print(listRow.get(i)+" ");
        System.out.print("\nСтолбцы, содержащие только 0: ");
        for(int i = 0; i < listColumn.size(); i++)
            System.out.print(listColumn.get(i)+" ");
        if(n-listRow.size() == 0 || n-listColumn.size() == 0){
            System.out.println("Размерность матрицы - результата равна 0");
        }
        else{
            int[][] result = new int[n-listRow.size()][n-listColumn.size()];
            System.out.println("\n\nМатрица результата: ");
            int k = 0;
            int m = 0;
            for(int i = 0; i < n; i++){
                if(!listRow.contains(i)){
                    m = 0;
                    for(int j = 0; j < n; j++){
                        if(!listColumn.contains(j)){
                            result[k][m] = matrix[i][j];
                            m++;
                        }
                    }
                    k++;
                }
            }
            for(int i = 0; i < n-listRow.size(); i++){
                for(int j = 0; j < n-listColumn.size(); j++)
                    System.out.printf("%3s", result[i][j]);
                System.out.println();
            }
        }
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task20(String args[]){
        //complete
        //странное задание, в целом.
        //ну ничего, попробуем.
        //считаем, что данные корректны;
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        int newRow = Integer.parseInt(args[1]); 	// строка для перемещаемого элемента
        int newColumn = Integer.parseInt(args[2]); 	// столбец для перемещаемого элемента
        newRow--; //ну мы же строки с единицы считаем, верно?
        newColumn--; //да и столбцы тоже
        int[][] matrix = new int[n][n];
        System.out.println("    Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        //минимальным считаем элемент, который встретили первым.
        int lMin = Integer.MAX_VALUE;
        int minRow = 0;
        int minColumn = 0;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < lMin) {
                    lMin = matrix[i][j];
                    minRow = i;
                    minColumn = j;
                }
            }
        }
        System.out.println("\n    Минимальный элемент: "+lMin+", в позиции "+minRow+","+minColumn);
        System.out.println("    Перемещается в позицию: "+newRow+","+newColumn);
        //перемещение
        boolean rowIdentity = false;
        if(newRow == minRow){ //начнем со строк
            System.out.println("    Введенная строка совпала со столбцом минмального элемента\n" +
                    "Перемещение строк не требуется.");
            rowIdentity = true;
        }
        else{
            //перемещение строк
            int temp = 0;
            for(int j = 0; j < n; j++){
                temp = matrix[minRow][j];
                matrix[minRow][j] = matrix[newRow][j];
                matrix[newRow][j] = temp;
            }
        }
        if(newColumn == minColumn){
            System.out.println("    Введенный столбец совпал со столбцом минмального элемента\n" +
                    "Перемещение столбцов не требуется.");
            if(rowIdentity){
                System.out.println("    Перемещение не требуется.");
            }
        }
        else{
            int temp = 0;
            for(int i = 0; i < n; i++){
                temp = matrix[i][minColumn];
                matrix[i][minColumn] = matrix[i][newColumn];
                matrix[i][newColumn] = temp;
            }
        }
        System.out.println("\n    Матрица результата: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                System.out.printf("%3s", matrix[i][j]);
            System.out.println();
        }
        System.out.println("    Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task21(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        int k = 0;
        int temp = 0;
        for(int i = 0; i < n; i++){
            k = n-1; //позиция, куда на этой строке можно вставить 0 (последний элемент)
            for(int j = 0; j < n; j++)
            {
                if(matrix[i][j] == 0){
                    while(k > 0 && (j < k)){ //элементы не меняются в пройденном направлении
                        if(matrix[i][k] != 0){ //сдвиг строки, помешаем ноль в конец (или в место перед концом, если там
                                                                                                                //нули)
                            for(int ex = j; ex < k; ex++){
                                temp = matrix[i][ex];
                                matrix[i][ex] = matrix[i][ex+1];
                                matrix[i][ex+1] = temp;
                            }
                            j--;
                            break;
                        }
                        else{ //смещаем К, пока он не будет равен 0;
                            k--;
                        }
                    }
                }
            }
        }
        System.out.println("\nМатрица результата: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                System.out.printf("%3s", matrix[i][j]);
            System.out.println();
        }
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task22(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        //ладно, сгенерируем double
        double[][] matrix = new double[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = -n + (2*n)*random.nextDouble();
                System.out.printf("%6.2f", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        int[][] result = new int[n][n];
        System.out.println("\nМатрица результата: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[i][j] = (int)Math.round(matrix[i][j]); //стандартное округление
                System.out.printf("%3s", result[i][j]);
            }
            System.out.println();
        }
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task23(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        //тут, собственно, фича: минимум/максимум - первый такой элемент в сроке
        int[] minRows = new int[n]; //величины всех минимумов, построчно с первой
        int[] maxColumns = new int[n]; //величины всех минимумов, по столбцам с первого
        int minMax = 0; 		//для организации проверки
        //построчные минимумы
        for (int i = 0; i < n; i++){
            minMax = matrix[i][0];
            for (int j = 1; j < n; j++)
                if (matrix[i][j] < minMax){
                    minMax= matrix[i][j];
                }
            minRows[i] = minMax;
        }
        //максимумы в столбцах
        for (int j = 0; j < n; j++){
            minMax = matrix[0][j];
            for (int i = 1; i < n; i++)
                if (matrix[i][j] > minMax){
                    minMax= matrix[i][j];
                }
            maxColumns[j] = minMax;
        }
        int count = 0; //число седловых точек
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++ ){
                if (matrix[i][j] == minRows[i] && matrix[i][j] == maxColumns[j]){
                    System.out.printf("\n[%s][%s]=%s", i, j, matrix[i][j]);
                    count++;
                }
            }
        }
        System.out.println("\nЧисло седловых точек: "+count);
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task24(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        //вспоминаем первый таск, например
        //сумму не вывожу, извините
        //да и в задании не указано ^^
        int[] summary = new int[n];
        for(int i = 0; i < n; i++){
            summary[i] = 0;
            for(int j = 0; j < n; j++){
                summary[i]+=matrix[i][j];
            }
        }
        for(int i = 1; i < n; i++){
            int currString[] = matrix[i].clone(); //впервые используем clone для матрицы
            int previos = i - 1;
            while(previos >= 0 && summary[previos] > summary[i]){
                matrix[previos+1] = matrix[previos]; //двигаемся
                matrix[previos] = currString.clone();
                previos--;
            }
        }
        System.out.println("\nМатрица результата: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task25(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        //исплючаем ошибки размерности
        //А ВДРУГ?!
        if(n == Integer.MAX_VALUE){
            System.out.println("Некорректное значение размера, выход");
            return;
        }
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        //логика
        //впишем массив в рамку :D
        int[][] result = new int[n+2][n+2];
        for(int i = 0; i < n+2; i++){
            for(int j = 0; j < n+2; j++){
                if(i == 0 || i == n+1 || j == 0 || j == n+1)
                    result[i][j] = Integer.MAX_VALUE;
                else
                    result[i][j] = matrix[i-1][j-1];
            }
        }
        //минимумы выводить не нужно, делал это с расчетом на следующее задание
        int count = 0; //число лок. мин
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < n+1; j++){
                if(result[i][j] < result[i-1][j-1] &&
                        result[i][j] < result[i][j-1] &&
                        result[i][j] < result[i-1][j] &&
                        result[i][j] < result[i+1][j] &&
                        result[i][j] < result[i][j+1] &&
                        result[i][j] < result[i+1][j+1] &&
                        result[i][j] < result[i+1][j-1] &&
                        result[i][j] < result[i-1][j+1]){
                    count++;
                    System.out.printf("Найден локальный минимум в координатах "
                            + "[%s][%s]=%s", i-1, j-1, result[i][j]+"\n");
                }
            }
        }
        System.out.println("Число локальных минимумов: "+count);
        System.out.println("Завершение работы программы.");
    }

    //public static void main(String args[]){
    void task26(String args[]){
        //complete
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        //исплючаем ошибки размерности
        if(n == Integer.MAX_VALUE){
            System.out.println("Некорректное значение размера, выход");
            return;
        }
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        //логика
        //впишем массив в рамку
        int[][] result = new int[n+2][n+2];
        for(int i = 0; i < n+2; i++){
            for(int j = 0; j < n+2; j++){
                if(i == 0 || i == n+1 || j == 0 || j == n+1)
                    result[i][j] = Integer.MAX_VALUE;
                else
                    result[i][j] = matrix[i-1][j-1];
            }
        }
        int count = 0; //число лок. мин
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < n+1; j++){
                if(result[i][j] > result[i-1][j-1] &&
                        result[i][j] > result[i][j-1] &&
                        result[i][j] > result[i-1][j] &&
                        result[i][j] > result[i+1][j] &&
                        result[i][j] > result[i][j+1] &&
                        result[i][j] > result[i+1][j+1] &&
                        result[i][j] > result[i+1][j-1] &&
                        result[i][j] > result[i-1][j+1]){
                    count++;
                    list.add(result[i][j]);
                    System.out.printf("Найден локальный максимум в координатах "
                            + "[%s][%s]=%s", i, j, result[i][j]+"\n");
                }
            }
        }
        int lMax = Integer.MIN_VALUE;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) > lMax)
                lMax = list.get(i);
        }
        System.out.println("Число локальных максимумов: "+count);
        if(list.size() != 0)
            System.out.println("Наибольший из них: "+lMax);
        System.out.println("Завершение работы программы.");
    }

    public static void main(String args[]){
    //void task27(String args[]){
        final Random random = new Random();
        int n = Integer.parseInt(args[0]); 	// считаем, что агрумент размера - верен.
        //а почему бы нет?
        int[][] matrix = new int[n][n];
        System.out.println("Исходная матрица: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = random.nextInt(2*n)-n;
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        //логика
        //меняем 24 таск
        int[] summary = new int[n];
        int result[][] = new int[n][n]; //новая матрица столбцы = строки
        for(int j = 0; j < n; j++){
            summary[j] = 0;
            for(int i = 0; i < n; i++){
                summary[j]+=Math.abs(matrix[i][j]);
                result[j][i] = matrix[i][j];
            }
        }
        System.out.println("\nХарактеристика (по столбцам соответственно): ");
        for(int i = 0; i < n; i++){
            System.out.printf("%3s", summary[i]);
        }
        System.out.println();
        for(int i = 1; i < n; i++){
            int currString[] = result[i].clone(); //впервые используем clone для матрицы
            int previos = i - 1;
            while(previos >= 0 && summary[previos] > summary[i]){
                result[previos+1] = result[previos]; //двигаемся
                result[previos] = currString.clone();
                previos--;
            }
        }
        //столбцы = строки, меняем обратно
        for(int j = 0; j < n; j++){
            for(int i = 0; i < n; i++){
                matrix[j][i] = result[i][j];
            }
        }
        System.out.println("\nМатрица результата: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("Завершение работы программы.");
    }
}
