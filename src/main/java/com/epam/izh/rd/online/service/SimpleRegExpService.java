package com.epam.izh.rd.online.service;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        //File file = new File("src/main/resources/sensitive_data.txt");
        String newString = "";
        try {
            FileReader fileReader = new FileReader("src/main/resources/sensitive_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Pattern pattern = Pattern.compile("([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s([0-9]+)");
            Matcher matcher = pattern.matcher(bufferedReader.readLine());
            if (matcher.find()) {
                newString = matcher.replaceAll("$1 **** **** $4");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newString;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        String newString = "";
        try {
            FileReader fileReader = new FileReader("src/main/resources/sensitive_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Pattern pattern = Pattern.compile("\\$\\{[a-z]+\\_[a-z]+\\}");
            Matcher matcher = pattern.matcher(bufferedReader.readLine());
            if (matcher.find()) {
                newString = matcher.replaceAll(String.valueOf((int)paymentAmount));
            }
            pattern = Pattern.compile("\\$\\{[a-z]+\\}");
            matcher = pattern.matcher(newString);
            if (matcher.find()) {
                newString = matcher.replaceAll(String.valueOf((int)balance));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newString;
    }
}
