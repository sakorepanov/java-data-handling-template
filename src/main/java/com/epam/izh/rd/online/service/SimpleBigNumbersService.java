package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;


public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     *
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        return new BigDecimal(a).divide(new BigDecimal(b), range, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        ArrayList<BigInteger> itIsPrime = new ArrayList<>();
        int lenOfArray = range;
        while (itIsPrime.size() < range) {
            lenOfArray = lenOfArray * 2;
            itIsPrime.clear();
            boolean[] prime = new boolean[lenOfArray + 1];
            Arrays.fill(prime, true);
            prime[0] = false;
            prime[1] = false;
            for (int i = 2; i * i <= lenOfArray; i++) {
                if (prime[i]) {
                    for (int j = i * i; j <= lenOfArray; j += i) {
                        prime[j] = false;
                    }
                }
            }
            for (int i = 0; i <= lenOfArray; i++) {
                if (prime[i]) {
                    BigInteger bigInteger = BigInteger.valueOf(i);
                    itIsPrime.add(bigInteger);
                }
            }
        }

        return itIsPrime.get(range);
    }
}
