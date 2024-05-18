package com.hackaton.bagginscoffee.codes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {

    private static Document getPage() throws IOException {
        String url = "http://pogoda.spb.ru/";
        return (Document) Jsoup.parse(new URL(url), 3000);
    }

    private static String getDate(String stringDate) {

        String[] date = stringDate.split(" ");
        return date[1] + " " + date[0];
    }

    private static void printValue(Elements values) {
        Element valueLine = values.get(1);
        for (Element td : valueLine.select("td")) {
            System.out.print(td.text() + ' ');
        }
    }

    public static void main(String[] args) throws IOException {
        Document page = getPage();

        Element tableWeather = page.select("table[class=wt]").first();
        Elements names = tableWeather.select("tr[class=wth]");
        Elements values = tableWeather.select("tr[valign=top]");

        for (Element name : names) {
            String dates = name.select("th[id=dt]").text();
            String date = getDate(dates);
            System.out.print(date + ' ');
            printValue(values);
            System.out.println(' ');
        }
    }
}
