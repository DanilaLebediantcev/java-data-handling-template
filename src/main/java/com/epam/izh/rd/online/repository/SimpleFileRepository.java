package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long countFilesInCatalogue = 0;
        File file = new File("src/main/resources/" + path);
        if (file.isDirectory()) {
            for (File bufferFile : file.listFiles()) {
                countFilesInCatalogue += countFilesInDirectory(path + "/" + bufferFile.getName());
            }
        } else {
            countFilesInCatalogue++;
        }
        return countFilesInCatalogue;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long countDirectoryInCatalogue = 0;
        File file = new File("src/main/resources/" + path);
        if (file.isDirectory()) {
            for (File bufferFile : file.listFiles()) {
                countDirectoryInCatalogue += countDirsInDirectory(path + "/" + bufferFile.getName());
            }
            countDirectoryInCatalogue++;
        } else {
        }
        return countDirectoryInCatalogue;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) throws IOException {
        try {
            File dir = new File(from);
            for(File file: dir.listFiles()) {
                Files.copy(file.toPath(), new File(to).toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {

        //Файл создается, но тест не проходит. Объясните пожалуйста почему

        File fileDir = new File("src/main/resources/" + path);
        File newFile = new File("src/main/resources/" + path, name);
        try {
            if (fileDir.exists() && fileDir.isDirectory()) {
                return newFile.createNewFile();
            } else {
                fileDir.mkdir();
                return createFile(fileDir.getName(), name);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        StringBuilder array = new StringBuilder();

        try {
            FileReader file = new FileReader("src/main/resources/" + fileName);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                array.append(scan.nextLine());
            }
            return array.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
