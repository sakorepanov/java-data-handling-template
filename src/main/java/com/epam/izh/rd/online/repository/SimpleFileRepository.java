package com.epam.izh.rd.online.repository;

import java.io.*;
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

        return 0;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        return 0;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        return;
    }

    /**
     * Метод создает файл на диске с расширением txt
     *  @param path путь до нового файла
     * @param name имя файла
     * @return
     */
    @Override
    public boolean createFile(String path, String name) throws IOException {
       File file = new File(path + File.separator, name);
       file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        try {
            Scanner in = new Scanner(new FileReader(String.format("src/main/resources/%s", fileName)));
            StringBuilder stringBuilder = new StringBuilder();
            while (in.hasNext()) {
                stringBuilder.append(in.next());
            }
            in.close();
            String textFromFile = stringBuilder.toString();
            return textFromFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
