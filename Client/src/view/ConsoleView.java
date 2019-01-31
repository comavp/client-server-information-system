package view;

import java.util.*;

public class ConsoleView extends Observable implements ModelView {

    public void execute() {
        Scanner in = new Scanner(System.in);
        String operationNumber;
        boolean exitTime = false;
        boolean visibleMenu = false;
        String pathToFile;
        String request = "";

        while(!exitTime) {
            if (visibleMenu)  {
                System.out.println("0 - Скрыть меню");
                showMenu();
            } else {
                System.out.println("0 - Показать меню");
            }
            System.out.println("Введите номер команды: ");

            operationNumber = parseUserInput(1, in);

            switch (operationNumber) {
                case "0":
                    visibleMenu = !visibleMenu;
                    break;
                case "1":
                    request = operationNumber.toString() + "|" + parseUserInput(2, in);
                    break;
                case "2":
                    request = operationNumber.toString() + "|" + parseUserInput(2, in);
                    break;
                case "3":
                    System.out.println("Поиск");
                    break;
                case "4":
                    request = operationNumber.toString() + "|";
                    System.out.println("Введите новые названия жанра и данные о треке\n(Если поле не требует изменения введите \"-\"," +
                            " в случае продолжительности трека -  \"0\")");
                    request += parseUserInput(2, in) + "|";
                    System.out.println("Введите название жанра и трека, которые хотите изменить");
                    System.out.println("Название жанра: ");
                    request += in.nextLine() + "|";
                    System.out.println("Номер трека");
                    request += parseUserInput(1, in);
                    break;
                case "5":
                    request = operationNumber.toString();
                    break;
                case "6":
                    System.out.println("Введите путь к файлу:");
                    pathToFile = in.nextLine();
                    request = operationNumber.toString() + "|" + pathToFile;
                    break;
                case "7":
                    System.out.println("Работа с программой завершена");
                    exitTime = true;
                    break;
                default:
                    System.out.println("Введён неверный номер, повторите!");
            }
            if(!exitTime && !request.equals("")) {
                setChanged();
                notifyObservers(request);
                clearChanged();
                request = "";
            }
        }
    }

    public void receiveResult(String result) {
        System.out.println(result);
    }

    public void showMenu () {
        System.out.println("1 - Добавить элемент\n2 - Удалить элемент\n3 - Найти элемент\n4 - Изменить элемент\n5 - Вывести все данные\n" +
                "6 - Добавить данные из файла\n7 - Выход");
    }

    public String parseUserInput(Integer chOption, Scanner in) {
        try {
            String request;
            String readString;

            if (chOption == 1) {
                request = ((Integer)(in.nextInt())).toString();
                if (Integer.parseInt(request) < 0) {
                    request = "-1";
                }
                in.nextLine();
            } else {
                System.out.println("Введите название жанра");
                readString = in.nextLine();
                request = readString;
                System.out.println("Введите информацию о треке");
                System.out.println("Название:");
                readString = in.nextLine();
                request += "|" + readString;
                System.out.println("Исполнитель: ");
                readString = in.nextLine();
                request += "|" + readString;
                System.out.println("Альбом: ");
                readString = in.nextLine();
                request += "|" + readString;
                System.out.println("Продолжительность: ");
                readString = parseUserInput(1, in);
                while (readString == "-1") {
                    System.out.println("Введено некорректное значение, попробуйте ещё раз!");
                    readString = parseUserInput(1, in);
                }
                request += "|" + readString;
            }
           return request;
        }
        catch (InputMismatchException e) {
            String x = in.nextLine();
            return "-1";
        }
    }
}


