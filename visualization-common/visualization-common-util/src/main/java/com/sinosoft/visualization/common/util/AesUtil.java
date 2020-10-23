package com.sinosoft.visualization.common.util;

import lombok.extern.slf4j.Slf4j;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import static com.sinosoft.visualization.common.basic.constants.CommonConstants.DECRYPTERROR;
import static com.sinosoft.visualization.common.basic.constants.CommonConstants.ENCRYPTERROR;

/**
 * AesUtil
 *
 * @author wangyl
 * Description:
 * Created in: 2020/8/20
 * Modified by:
 */
@Slf4j
public class AesUtil {

    /**
     * 加密
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String key = "eadb485ef2a806b6"; // picc2.5
        String content = "需要加密的内容";
        byte[] encrypt = encrypt(content, key);
        String hexStr = ParseSystemUtil.parseByte2HexStr(encrypt);
        System.out.println("加密后的16进制密文:" + hexStr);
        byte[] byte2 = ParseSystemUtil.parseHexStr2Byte(hexStr);
        byte[] decrypt = decrypt(byte2, key);
        System.out.println("解密后的内容：" + new String(decrypt,"utf-8"));
    }

    public static String decryptByKey(String databasekey, String aesKey) throws UnsupportedEncodingException {
        byte[] byte2 = ParseSystemUtil.parseHexStr2Byte(databasekey);
        byte[] decrypt = decrypt(byte2, aesKey);
        return new String(decrypt,"utf-8");
    }

    /**
     * 解密
     * @param content
     * @param pskey
     * @return
     */
    public static byte[] decrypt(byte[] content, String pskey) {
        try {
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG") ;
            secureRandom.setSeed(pskey.getBytes());
            kgen.init(128, secureRandom);
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            // 明文
            return result;
        } catch (Exception e) {
            log.info(DECRYPTERROR, e);
        }
        return null;
    }

    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param pskey
     *            加密需要的密码
     * @return 密文
     */
    public static byte[] encrypt(String content, String pskey) {
        try {
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 利用用户密码作为随机数初始化出
            kgen.init(128, new SecureRandom(pskey.getBytes()));
            // 加密没关系，SecureRandom是生成安全随机数序列，pskey.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有pskey就行
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (Exception e) {
            log.info(ENCRYPTERROR, e);
        }
        return null;
    }

}
