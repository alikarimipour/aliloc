/**
 * 12/22/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.util;

import java.util.Random;

public class VoucherCodes {

    private static final Random RND = new Random(System.currentTimeMillis());

    public static String generate(VoucherCodeConfig config) {
        StringBuilder sb = new StringBuilder();
        char[] chars = config.getCharset().toCharArray();
        char[] pattern = config.getPattern().toCharArray();

        if (config.getPrefix() != null) {
            sb.append(config.getPrefix());
        }

        for (char aPattern : pattern) {
            if (aPattern == VoucherCodeConfig.PATTERN_PLACEHOLDER) {
                sb.append(chars[RND.nextInt(chars.length)]);
            } else {
                sb.append(aPattern);
            }
        }

        if (config.getPostfix() != null) {
            sb.append(config.getPostfix());
        }

        return sb.toString();
    }
}
