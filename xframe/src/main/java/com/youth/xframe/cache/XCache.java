package com.youth.xframe.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.youth.xframe.XFrame;
import com.youth.xframe.utils.XBitmapUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class XCache {
    public static final int TIME_HOUR = 60 * 60;
    public static final int TIME_DAY = TIME_HOUR * 24;
    public static final int TIME_MONTH = TIME_DAY * 30;
    private static final int maxSize = 1000 * 1000 * 50; // 50 mb
    private static final int maxCount = Integer.MAX_VALUE; // 不限制存放数据的数量
    private static Map<String, XCache> mInstanceMap = new HashMap<String, XCache>();
    private XCacheManager mCache;

    public static XCache get(Context ctx) {
        return get(ctx, "XCache");
    }

    public static XCache get(Context ctx, String cacheName) {
        File f = new File(ctx.getCacheDir(), cacheName);
        return get(f, maxSize, maxCount);
    }

    public static XCache get(File cacheDir) {
        return get(cacheDir, maxSize, maxCount);
    }

    public static XCache get(Context ctx, long maxSize, int maxCount) {
        File f = new File(ctx.getCacheDir(), "XCache");
        return get(f, maxSize, maxCount);
    }

    public static XCache get(File cacheDir, long maxSize, int maxCount) {
        XCache manager = mInstanceMap.get(cacheDir.getAbsoluteFile() + myPid());
        if (manager == null) {
            manager = new XCache(cacheDir, maxSize, maxCount);
            mInstanceMap.put(cacheDir.getAbsolutePath() + myPid(), manager);
        }
        return manager;
    }

    private static String myPid() {
        return "_" + android.os.Process.myPid();
    }

    private XCache(File cacheDir, long maxSize, int maxCount) {
        if (!cacheDir.exists() && !cacheDir.mkdirs()) {
            throw new RuntimeException("can't make dirs in "
                    + cacheDir.getAbsolutePath());
        }
        mCache = new XCacheManager(cacheDir, maxSize, maxCount);
    }

    /**
     * 保存 String数据 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的String数据
     */
    public void put(String key, String value) {
        File file = mCache.newFile(key);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file), 1024);
            out.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mCache.put(file);
        }
    }

    /**
     * 保存 String数据 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的String数据
     * @param saveTime
     *            保存的时间，单位：秒
     */
    public void put(String key, String value, int saveTime) {
        put(key, XCacheUtils.newStringWithDateInfo(saveTime, value));
    }

    /**
     * 读取 String数据
     *
     * @param key
     * @return String 数据
     */
    public String getAsString(String key) {
        File file = mCache.get(key);
        if (!file.exists())
            return null;
        boolean removeFile = false;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String readString = "";
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                readString += currentLine;
            }
            if (!XCacheUtils.isDue(readString)) {
                return XCacheUtils.clearDateInfo(readString);
            } else {
                removeFile = true;
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (removeFile)
                remove(key);
        }
    }

    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的数据
     */
    public void put(String key, byte[] value) {
        File file = mCache.newFile(key);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mCache.put(file);
        }
    }

    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的数据
     * @param saveTime
     *            保存的时间，单位：秒
     */
    public void put(String key, byte[] value, int saveTime) {
        put(key, XCacheUtils.newByteArrayWithDateInfo(saveTime, value));
    }

    /**
     * 获取 byte 数据
     *
     * @param key
     * @return byte 数据
     */
    public byte[] getAsBinary(String key) {
        RandomAccessFile RAFile = null;
        boolean removeFile = false;
        try {
            File file = mCache.get(key);
            if (!file.exists())
                return null;
            RAFile = new RandomAccessFile(file, "r");
            byte[] byteArray = new byte[(int) RAFile.length()];
            RAFile.read(byteArray);
            if (!XCacheUtils.isDue(byteArray)) {
                return XCacheUtils.clearDateInfo(byteArray);
            } else {
                removeFile = true;
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (RAFile != null) {
                try {
                    RAFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (removeFile)
                remove(key);
        }
    }


    /**
     * 保存 Serializable数据 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的value
     */
    public void put(String key, Serializable value) {
        put(key, value, -1);
    }

    /**
     * 保存 Serializable数据到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的value
     * @param saveTime
     *            保存的时间，单位：秒
     */
    public void put(String key, Serializable value, int saveTime) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            byte[] data = baos.toByteArray();
            if (saveTime != -1) {
                put(key, data, saveTime);
            } else {
                put(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 读取 Serializable数据
     *
     * @param key
     * @return Serializable 数据
     */
    public Object getAsObject(String key) {
        byte[] data = getAsBinary(key);
        if (data != null) {
            ByteArrayInputStream bais = null;
            ObjectInputStream ois = null;
            try {
                bais = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bais);
                Object reObject = ois.readObject();
                return reObject;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (bais != null)
                        bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (ois != null)
                        ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    /**
     * 保存 bitmap 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的bitmap数据
     */
    public void put(String key, Bitmap value) {
        put(key, XBitmapUtils.getBytesFromBitmap(value));
    }

    /**
     * 保存 bitmap 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的 bitmap 数据
     * @param saveTime
     *            保存的时间，单位：秒
     */
    public void put(String key, Bitmap value, int saveTime) {
        put(key, XBitmapUtils.getBytesFromBitmap(value), saveTime);
    }

    /**
     * 读取 bitmap 数据
     *
     * @param key
     * @return bitmap 数据
     */
    public Bitmap getAsBitmap(String key) {
        if (getAsBinary(key) == null) {
            return null;
        }
        return XBitmapUtils.getBitmapFromBytes(getAsBinary(key));
    }

    /**
     * 保存 drawable 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的drawable数据
     */
    public void put(String key, Drawable value) {
        put(key, XBitmapUtils.getBitmapFromDrawable(value));
    }

    /**
     * 保存 drawable 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的 drawable 数据
     * @param saveTime
     *            保存的时间，单位：秒
     */
    public void put(String key, Drawable value, int saveTime) {
        put(key, XBitmapUtils.getBitmapFromDrawable(value), saveTime);
    }

    /**
     * 读取 Drawable 数据
     *
     * @param key
     * @return Drawable 数据
     */
    public Drawable getAsDrawable(String key) {
        if (getAsBinary(key) == null) {
            return null;
        }
        return XBitmapUtils.getDrawableFromBitmap(XBitmapUtils.getBitmapFromBytes(getAsBinary(key)));
    }

    /**
     * 获取缓存文件
     *
     * @param key
     * @return value 缓存的文件
     */
    public File file(String key) {
        File f = mCache.newFile(key);
        if (f.exists())
            return f;
        return null;
    }

    /**
     * 移除某个key
     *
     * @param key
     * @return 是否移除成功
     */
    public boolean remove(String key) {
        return mCache.remove(key);
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        mCache.clear();
    }
   
}
