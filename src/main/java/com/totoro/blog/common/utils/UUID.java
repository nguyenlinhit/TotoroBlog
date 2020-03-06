package com.totoro.blog.common.utils;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author linhnguyen
 * @version 1.0
 * @classname UUID
 * @description Provide a universally unique identifier (UUID) implementation
 * @date 28/01/2020
 */
public final class UUID implements Serializable, Comparable<UUID> {

    private static final long serialVersionUID = -1185015143654744140L;

    private static class Holder{
        static final SecureRandom numberGenerator = getSecureRandom();
    }

    private final long mostSigBits;
    private final long lastSigBits;

    /**
     * Constructs a new UUID with the specified data
     *
     * @param mostSigBits Up to 64 significant bits for {@code UUID}
     * @param lastSigBits Least 64 significant bits for {@code UUID}
     */
    public UUID(long mostSigBits, long lastSigBits) {
        this.mostSigBits = mostSigBits;
        this.lastSigBits = lastSigBits;
    }

    /**
     * The least significant 64 bits of this UUID
     */
    private UUID(byte[] data){
        long msb = 0;
        long lsb = 0;

        assert data.length == 16 : "data must be 16 bytes in length";
        for (int i = 0; i < 8; i++){
            msb = (msb << 8) | (data[i] & 0xff);
        }
        for (int i = 8; i < 16; i++){
            lsb = (lsb << 8) | (data[i] & 0xff);
        }

        this.mostSigBits = msb;
        this.lastSigBits = lsb;
    }

    /**
     * Take a static factory of type 4 (pseudo-randomly generated) UUID.
     * This UUID is generated using an encrypted strong pseudo-random number generator.
     *
     * @return Randomly generated {@code UUID}
     */
    public static UUID fastUUID(){
        return randomUUID(false);
    }

    /**
     * Take a static factory of type 4 (pseudo-randomly generated) UUID.
     * This UUID is generated using an encrypted strong pseudo-random number generator.
     *
     * @return Randomly generated {@code UUID}
     */
    public static UUID randomUUID(){
        return randomUUID(true);
    }

    /**
     * Take a static factory of type 4 (pseudo-randomly generated) UUID.
     * This UUID is generated using an encrypted strong pseudo-random number generator.
     *
     * @param isSecure Whether to use {@link SecureRandom}
     *                 If it is, you can get a more secure random code,
     *                 otherwise you can get better performance
     * @return Randomly generated {@code UUID}
     */
    public static UUID randomUUID(boolean isSecure){
        final Random ng = isSecure ? Holder.numberGenerator : getRandom();

        byte[] randomBytes = new byte[16];
        assert ng != null;
        ng.nextBytes(randomBytes);
        randomBytes[6] &= 0x0f;
        randomBytes[6] |= 0x40;
        randomBytes[8] &= 0x3f;
        randomBytes[8] |= 0x80;

        return new UUID(randomBytes);
    }

    public static SecureRandom getSecureRandom(){
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();/*TODO UtilException call here*/
        }
        return null;
    }

    private static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    @Override
    public int compareTo(UUID uuid) {
        return 0;
    }

    /**
     * Returns a string representation of this {@code UUID}.
     *
     * <p>
     * The string representation of the UUID is described by this BNF:
     *
     * <pre>
     *      {@code
     *       UUID                   = <time_low>-<time_mid>-<time_high_and_version>-<variant_and_sequence>-<node>
     *       time_low               = 4*<hexOctet>
     *       time_mid               = 2*<hexOctet>
     *       time_high_and_version  = 2*<hexOctet>
     *       variant_and_sequence   = 2*<hexOctet>
     *       node                   = 6*<hexOctet>
     *       hexOctet               = <hexDigit><hexDigit>
     *       hexDigit               = [0-9a-fA-F]
     *      }
     * </pre>
     *
     * @param isSimple Whether simple mode, simple mode is UUID string without '-'
     * @return A string representation of this {@code UUID}
     */
    public String toString(boolean isSimple) {
        final StringBuilder builder = new StringBuilder(isSimple ? 32 : 36);
        // time_low
        builder.append(digits(mostSigBits >> 32, 8));
        if (isSimple == false) {
            builder.append('-');
        }
        // time_mid
        builder.append(digits(mostSigBits >> 16, 4));
        if (isSimple == false) {
            builder.append('-');
        }
        // time_high_and_version
        builder.append(digits(mostSigBits, 4));
        if (false == isSimple) {
            builder.append('-');
        }
        // variant_and_sequence
        builder.append(digits(lastSigBits >> 48, 4));
        if (isSimple == false) {
            builder.append('-');
        }
        // node
        builder.append(digits(lastSigBits, 12));

        return builder.toString();
    }

    /**
     * Returns the hex value corresponding to the specified number.
     *
     * @param val    Value
     * @param digits Rank
     * @return String
     */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }

    /**
     * Returns a string representation of this {@code UUID}.
     *
     * <p>
     * The string representation of the UUID is described by this BNF:
     *
     * <pre>
     *      {@code
     *       UUID                   = <time_low>-<time_mid>-<time_high_and_version>-<variant_and_sequence>-<node>
     *       time_low               = 4*<hexOctet>
     *       time_mid               = 2*<hexOctet>
     *       time_high_and_version  = 2*<hexOctet>
     *       variant_and_sequence   = 2*<hexOctet>
     *       node                   = 6*<hexOctet>
     *       hexOctet               = <hexDigit><hexDigit>
     *       hexDigit               = [0-9a-fA-F]
     *      }
     * </pre>
     *
     * </blockquote>
     *
     * @return A string representation of this {@code UUID}
     * @see #toString(boolean)
     */
    @Override
    public String toString() {
        return toString(false);
    }
}
