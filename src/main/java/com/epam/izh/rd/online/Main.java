package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleTextService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static boolean isBelowZeroCelsius(double kelvin) {
        return (kelvin < 273 ? true : false);
    }

    public static void main(String[] args) {

    }
}
