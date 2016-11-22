package com.shan.mypubliclibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO utils
 * Created by chenjunshan on 2016/7/31.
 */

public class IOUtils {

    private IOUtils() {
        throw new AssertionError();
    }


    /**
     * 关闭closeable
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            }
        }
    }

    /**
     * 关闭closeable
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Ignored
            }
        }
    }

}
