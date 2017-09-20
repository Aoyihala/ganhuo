package com.p2p.dsad.ganhuo.utlis;

import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * 内存加载管理器
 * Created by dsad on 2017/9/16.
 */

public class MemaryUtils
{
    private LruCache<String,Bitmap> bitmapcache;
    public MemaryUtils()
    {
        long maxsize = Runtime.getRuntime().maxMemory();
        bitmapcache = new LruCache<String, Bitmap>((int) (maxsize/3))
        {
            //重写了后就会回首内存
            @Override
            protected int sizeOf(String key, Bitmap value)
            {
                return value.getByteCount();
            }
        };

    }
    public void load(String url,Bitmap bitmap)
    {
        bitmapcache.put(url,bitmap);
    }

    public Bitmap read(String url)
    {
       return bitmapcache.get(url);
    }
}
