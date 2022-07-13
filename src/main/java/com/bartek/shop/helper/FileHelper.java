package com.bartek.shop.helper;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileHelper {

    public void saveFile (InputStream in , Path target) throws IOException {
        Files.copy(in, target);
    }

    public void deleteFile(Path target) throws IOException {
        Files.delete(target);
    }
}
