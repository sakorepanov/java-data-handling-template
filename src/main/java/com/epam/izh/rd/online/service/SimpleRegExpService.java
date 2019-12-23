package com.epam.izh.rd.online.service;

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
        String text = "";
        try {
            FileReader fileReader = new FileReader("src/main/resources/sensitive_data.txt");
            char[] buffer = new char[169];
            fileReader.read(buffer);
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("(\\d{4}\\s){4}");
        Matcher matcher = pattern.matcher(text);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String maskedCard = matcher.group().substring(0, 5) + "**** **** " + matcher.group().substring(15, 20);
            matcher.appendReplacement(stringBuffer, maskedCard);
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String text = "";
        try {
            FileReader fileReader = new FileReader("src/main/resources/sensitive_data.txt");
            char[] buffer = new char[169];
            fileReader.read(buffer);
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("\\$\\{(payment_amount)\\}");
        Matcher matcher = pattern.matcher(text);
        StringBuffer stringBufferForPaymentAmount = new StringBuffer();
        while (matcher.find()) {
            String replace = Integer.toString((int)paymentAmount);
            matcher.appendReplacement(stringBufferForPaymentAmount, replace);
        }
        matcher.appendTail(stringBufferForPaymentAmount);

        Pattern pattern1 = Pattern.compile("\\$\\{(balance)\\}");
        Matcher matcher1 = pattern1.matcher(stringBufferForPaymentAmount.toString());
        StringBuffer stringBufferForBalance = new StringBuffer();
        while (matcher1.find()) {
            String replace1 = Integer.toString((int)balance);
            matcher1.appendReplacement(stringBufferForBalance, replace1);

        }
        matcher1.appendTail(stringBufferForBalance);
        return stringBufferForBalance.toString();
    }
}
