package com.tyut.book.util;

import java.io.IOException;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtil {

    public static boolean isEmpty(String target) {
        if (target == null || target.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String doWithNull(String target) {
        if (target.isEmpty()) {
            return "";
        }
        return target;
    }

    public static String[] split(String strSource, String strDiv) {
        int arynum = 0, intIdx = 0, intIdex = 0;
        int div_length = strDiv.length();
        if (strSource.compareTo("") != 0) {
            if (strSource.indexOf(strDiv) != -1) {
                intIdx = strSource.indexOf(strDiv);
                for (int intCount = 1;; intCount++) {
                    if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                        intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                        arynum = intCount;
                    } else {
                        arynum += 2;
                        break;
                    }
                }
            } else {
                arynum = 1;
            }
        } else {
            arynum = 0;
        }
        intIdx = 0;
        intIdex = 0;
        String[] returnStr = new String[arynum];
        if (strSource.compareTo("") != 0) {
            if (strSource.indexOf(strDiv) != -1) {
                intIdx = strSource.indexOf(strDiv);
                returnStr[0] = strSource.substring(0, intIdx);
                for (int intCount = 1;; intCount++) {
                    if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                        intIdex = strSource
                                .indexOf(strDiv, intIdx + div_length);
                        returnStr[intCount] = strSource.substring(intIdx
                                + div_length, intIdex);
                        intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                    } else {
                        returnStr[intCount] = strSource.substring(intIdx
                                + div_length, strSource.length());
                        break;
                    }
                }
            } else {
                returnStr[0] = strSource.substring(0, strSource.length());
                return returnStr;
            }
        } else {
            return returnStr;
        }
        return returnStr;
    }

    public static String replace(String str, String strSub, String strRpl) {
        String[] tmp = split(str, strSub);
        String returnstr = "";
        if (tmp.length != 0) {
            returnstr = tmp[0];
            for (int i = 0; i < tmp.length - 1; i++) {
                returnstr = doWithNull(returnstr) + strRpl + tmp[i + 1];
            }
        }
        return doWithNull(returnstr);
    }

    public static String htmlEncode(String txt) {
        if (txt != null) {
            txt = replace(txt, "&", "&amp;");
            txt = replace(txt, "&amp;amp;", "&amp;");
            txt = replace(txt, "&amp;quot;", "&quot;");
            txt = replace(txt, "\"", "&quot;");
            txt = replace(txt, "&amp;lt;", "&lt;");
            txt = replace(txt, "<", "&lt;");
            txt = replace(txt, "&amp;gt;", "&gt;");
            txt = replace(txt, ">", "&gt;");
            txt = replace(txt, "&amp;nbsp;", "&nbsp;");
        }
        return txt;
    }

    public static String toMD5String(String target) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };

        try {
            byte[] btInput = target.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] base64ToByteArray(String Base64String) {
        if (Base64String.startsWith("data:image/png;base64,")) {
            Base64String = Base64String.substring("data:image/png;base64,".length());
        }
        byte[] result = null;
        try {
            result = new BASE64Decoder().decodeBuffer(Base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String ByteArrayToImgBase4String(byte[] bytes) {
        return "data:image/png;base64," + new BASE64Encoder().encode(bytes);
    }
}
