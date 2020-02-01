package com.epam.izh.rd.online.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return localDate.format(dateFormat);
        //return localDate.getDayOfMonth()+"-"+localDate.getMonthValue()+"-"+localDate.getYear();
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(string,dateTimeFormatter);
    }

    /**
     * Метод конвертирует дату в строку с заданным форматом
     *
     * @param localDate исходная дата
     * @param formatter формат даты
     * @return полученная строка
     */
    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        LocalDateTime date = LocalDateTime.now();
        boolean leapYear = false;
        while (leapYear){
            if ((date.getYear() % 400 == 0) || ((date.getYear() % 4 == 0) && (date.getYear() % 100 != 0))) {
                leapYear = true;
            } else {
                date.plusYears(1);
            }
        }
        return date.getYear();
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        Calendar calendar = new GregorianCalendar(year+1,0,1);
        Calendar calendar1 = new GregorianCalendar(year,0,1);

        return ((calendar.getTime()).getTime()- (calendar1.getTime()).getTime())/1000;
    }


}
