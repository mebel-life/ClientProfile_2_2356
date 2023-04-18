package org.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;

public class ImageData {
    private byte[] imageData;

    public ImageData(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            imageData = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getImageData() {
        return imageData;
    }
}
