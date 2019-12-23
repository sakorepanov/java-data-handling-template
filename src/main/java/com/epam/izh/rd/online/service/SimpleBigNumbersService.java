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
        ArrayList<BigInteger> arrayOfPrimeNums = new ArrayList<>();
        int lengthOfArray = range;
        while (arrayOfPrimeNums.size() < range) {
            lengthOfArray = lengthOfArray * 2;
            arrayOfPrimeNums.clear();
            boolean[] isItPrime = new boolean[lengthOfArray + 1];
            Arrays.fill(isItPrime, true);
            isItPrime[0] = false;
            isItPrime[1] = false;
            for (int i = 2; i * i <= lengthOfArray; i++) {
                if (isItPrime[i]) {
                    for (int j = i * i; j <= lengthOfArray; j += i) {
                        isItPrime[j] = false;
                    }
                }
            }
            for (int i = 0; i <= lengthOfArray; i++) {
                if (isItPrime[i]) {
                    BigInteger bigInteger = BigInteger.valueOf(i);
                    arrayOfPrimeNums.add(bigInteger);
                }
            }
        }

        return arrayOfPrimeNums.get(range);
    }
}
