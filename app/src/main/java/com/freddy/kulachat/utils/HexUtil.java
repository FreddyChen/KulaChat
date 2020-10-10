package com.freddy.kulachat.utils;

/**
 * <p>@ProjectName:     BoChat</p>
 * <p>@ClassName:       HexUtil.java</p>
 * <p>@PackageName:     com.bochat.app.utils</p>
 * <b>
 * <p>@Description:     类描述</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/02/10 15:39</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class HexUtil {

    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch
                    + " at index " + index);
        }
        return digit;
    }

    public static byte[] decodeHex(char[] data) {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param
     * @return
     */
    public static String stringToHexString(String str) {
        return bytesToHexStr(str.getBytes());
    }

    public static String bytesToHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        String sTemp;
        for (byte b : bytes) {
            sTemp = Integer.toHexString(0xFF & b);
            if (sTemp.length() < 2)
                sb.append('0');
            sb.append(sTemp);
        }
        return sb.toString().toUpperCase();
    }
}
