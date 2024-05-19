package com.hackaton.bagginscoffee.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static String[] dateWth = new String[5];
    public static ArrayList<String> trash = new ArrayList<String>();

    private static Document getPage() throws IOException {
        String url = "http://pogoda.spb.ru/";
        return (Document) Jsoup.parse(new URL(url), 3000);
    }

    private static String getDate(String stringDate) {

        String[] date = stringDate.split(" ");
        return date[1] + " " + date[0];
    }

    private static int printValue(Elements values, int index) {
        int iterationCount = 4;
        Element valueLn = values.get(0);
        switch (index) {
            case 0:
                boolean isMorning = valueLn.text().contains("Утро");
                if (!isMorning) {
                    index = 1;
                }
                break;
            case 1:
                boolean isDay = valueLn.text().contains("День");
                if (!isDay) {
                    index = 2;
                }
                break;
            case 2:
                boolean isEvening = valueLn.text().contains("Вечер");
                if (!isEvening) {
                    index = 3;
                }
                break;
        }
        Element valueLine = values.get(index);
        for (Element td : valueLine.select("td")) {
            trash.add(td.text());
        }
        return iterationCount;
    }

    public static int toDigit(String str) {

        // Вывод максимального числа
        String[] parts = str.split("\\.\\.");
        int number = Integer.parseInt(parts[1]);

        // Вывод числа
        return number;
    }

    public static void fillData() throws IOException {
        Document page = getPage();

        Element tableWeather = page.select("table[class=wt]").first();
        Elements names = tableWeather.select("tr[class=wth]");
        Elements values = tableWeather.select("tr[valign=top]");

        int index = 0;
        int i = 0;
        for (Element name : names) {
            String dates = name.select("th[id=dt]").text();
            String date = getDate(dates);
            dateWth[i] = date;
            int iterationCount = printValue(values, index);
            System.out.println(' ');
            index += iterationCount;
            i++;
        }
    }

    public static void main(String[] args) throws IOException {
        fillData();

    }
}
