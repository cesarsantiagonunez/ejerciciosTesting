package com.imatia.campusdual;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EjercicioTest8 {

  // 8.
  // Repite el ejercicio anterior utilizando BeforeAll y AfterAll
    private static Account account1;
    private static Account account2;
    @BeforeAll
    public static void setup() {
      Branch branch = new Branch("001", "002");
      account1 = new Account(branch);
      account2 = new Account(branch);
    }
    @AfterAll
    public static void tearDown() {
      account1 = null;
      account2 = null;
      System.out.println("Test finalizado");
    }
    @Test
    @DisplayName("Test crear dos cuentas y comprobar que los números de cuenta son diferentes")
    public void Test8_1() {
      // Comprobar que los números de cuenta son diferentes
      assertNotEquals(account1.getAccountNumber(), account2.getAccountNumber());
    }
    @Test
    @DisplayName("Test incrementar el balance de una cuenta dos veces y comprobar que el valor final es correcto")
    public void Test8_2() {
      // Incrementar el balance dos veces
      BigDecimal increment1 = new BigDecimal("100");
      BigDecimal increment2 = new BigDecimal("50");
      account1.addBalance(increment1);
      account1.addBalance(increment2);
      // Comprobar que el balance final es correcto
      BigDecimal expectedBalance = new BigDecimal("150.0");
      assertEquals(expectedBalance, account1.getBalance());
    }

}
