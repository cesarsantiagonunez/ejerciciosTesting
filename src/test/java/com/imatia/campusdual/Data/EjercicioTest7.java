package com.imatia.campusdual.Data;

import static org.junit.jupiter.api.Assertions.*;

import com.imatia.campusdual.Account;
import com.imatia.campusdual.Branch;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EjercicioTest7 {

  //7.
  // Añade un BeforeEach para los tests del ejercicio 3 que cree las cuentas que se van a usar y un AfterEach que indique el final del test
  private Account a1, a2;
  private static int count = 1;
  @BeforeEach
  void createAccounts(){
    Branch b = new Branch();
    a1 = new Account(b);
    a2 = new Account(b);
  }
  @AfterEach
  void notifyEndOfTest(){
    System.out.println("Finalizado el test "+count);
    count++;
  }
  @Test
  @DisplayName("Comprobar distintos numeros en cuentas")
  void testAccountsDifferentNumber(){
    assertNotEquals(a1.getAccountNumber(), a2.getAccountNumber());
  }
  @Test
  @DisplayName("Comprobar añadir balance")
  void testAddBalanceAcount() {
    a1.addBalance(new BigDecimal("10.00"));
    a1.addBalance(new BigDecimal("100.00"));
    assertEquals(new BigDecimal("110.00"), a1.getBalance());
  }

}
