package org.zdd.bookstore.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 各种id生成策略
 * <p>Title: IDUtils</p>
 * <p>Description: </p>
 *
 */
public class IDUtils {

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * 商品id生成
     */
    public static long genItemId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

    /**
     * 订单号生成
     *
     * @return
     */
    public static String genOrderId() {
        //取当前时间的长整形值包含毫秒
        //long millis = System.currentTimeMillis();
        long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        return str;
    }


    public static String genShortUUID() {
        String[] split = UUID.randomUUID().toString().split("-");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(split[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
		/*System.out.println(genItemId());
		for(int i=0;i<10;i++)
			System.out.println(genOrderId());*/
        System.out.println(genShortUUID());
    }
}
