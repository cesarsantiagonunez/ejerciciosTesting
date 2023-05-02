package com.imatia.campusdual;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.math.BigDecimal;
import java.util.Calendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EjerciciosTest {
  // 1.
  // Testea la creacion de una nueva Branch utilizando el constructor vacio
  @Test
  @DisplayName("Testing constructor vacio branch")
  public void testConstructorVacio() {
    Branch branch = new Branch();
    assertNotNull(branch);
    assertNotNull(branch.getEntityID());
    assertNotNull(branch.getBranchID());
  }

  // Testea la creacion de una nueva Branch con el constructor parametrizado, comprueba que los datos se han introducido correctamente
  @Test
  @DisplayName("Testing constructor parametrizado branch")
  void testNewCustomBranch(){
    Branch b = new Branch("entity1", "branch1");
    assertEquals("branch1", b.getBranchID());
    assertEquals("entity1",b.getEntityID());
    assertNotEquals("branch2", b.getBranchID());
    assertNotEquals("entity2", b.getEntityID());
  }

// 2.
  // Crea dos branches con el mismo entityId y comprueba que tienen el mismo dato

  @Test
  @DisplayName("Testing branches mismo entity")
  void testSameEntityId() {
    Branch branch1 = new Branch("1234");
    Branch branch2 = new Branch("1234");
    assertEquals(branch1.getEntityID(), branch2.getEntityID());
  }

  // Crea dos branches y comprueba que NO tienen el mismo entityId

  @Test
  @DisplayName("Testing branches distinto entity")
  void testDifferentEntityId() {
    Branch branch1 = new Branch();
    Branch branch2 = new Branch();
    assertNotEquals(branch1.getEntityID(), branch2.getEntityID());
  }

// 3.
  // Crea dos cuentas y comprueba que NO tienen el mismo AccountNumber

  @Test
  @DisplayName("Testing accounts con diferente accountNumber")
  public void testDosCuentasDifAcountNumber() {
    Branch branch1 = new Branch();
    Branch branch2 = new Branch();
    Account account1 = new Account(branch1);
    Account account2 = new Account(branch2);
    assertNotEquals(account1.getAccountNumber(), account2.getAccountNumber());
  }

  // Incrementa el balance de una cuenta dos veces y comprueba que el valor final sea correcto

  @Test
  @DisplayName("Testing incremento")
  public void when_incrementing_balance_twice_then_balance_is_correct() {
    final Branch branch = new Branch();
    final Account account = new Account(branch);
    final BigDecimal increment = new BigDecimal("10.0");
    account.addBalance(increment);
    account.addBalance(increment);
    assertEquals(increment.add(increment), account.getBalance());
  }

// 4.
  // Intenta retirar mas fondos de los que dispone una cuenta y comprueba el error que desencadena

  @Test
  @DisplayName("Test correct withdrawal behaviour")
  void correctWithdrawalBehaviour() {
    Account a = new Account(new Branch(), "0");
    try {
      a.withdrawalBalance(new BigDecimal("1000"));
      fail();
    } catch (InsufficientBalanceException e) {
      assertTrue(true);
    }
  }

  @Test
  @DisplayName("Testing lanza excepcion")
  void testExceptionAccount(){
    Account a = new Account(new Branch());
    assertThrows(InsufficientBalanceException.class, () ->{a.withdrawalBalance(new BigDecimal("2.7"));});
  }

  @Test
  @DisplayName("Testing exception message")
  void testUnsuccessfulWithdrawBalance(){
    Account a1 = new Account(new Branch(),"0011223344");
    a1.addBalance(new BigDecimal("20.14"));
    InsufficientBalanceException ex = assertThrows(InsufficientBalanceException.class, () -> {
      a1.withdrawalBalance(new BigDecimal("100"));
    });
    assertEquals(ex.getMessage(), "M_INSUFFICIENT_BALANCE");
  }


  // Retira fondos de una cuenta y comprueba que el balance final sea correcto

  @Test
  @DisplayName("Testing retirada correcta")
  public void Test4_2() throws Exception{
    Branch branch = new Branch();
    Account account = new Account(branch, "98798");
    account.addBalance(new BigDecimal("50.0"));
    account.withdrawalBalance(new BigDecimal("25.0"));
    assertEquals(account.getBalance(), new BigDecimal("25.0"));
  }

// 5.
  // Crea una branch y una cuenta asociada y comprueba que dicha cuenta pertenece a esa branch

  @Test
  @DisplayName("5.1")
  void branchycuenta() {
    Branch b1 = new Branch("8795", "1982");
    Account ac1 = new Account(b1, "1");
    assertEquals(ac1.getBranchID(), b1.getBranchID());
    assertEquals(ac1.getEntityID(),b1.getEntityID());
  }


  // Crea una branch y varias cuentas asociadas, realiza las comprobaciones con diferentes assert y luego con un assertAll.
  // Adicional: Comprueba que el test falla si la branch de alguna cuenta no se corresponde con la creada anteriormente

  @Test
  @DisplayName("Branch in accounts")
  void testBranchInAccounts(){
    Branch b = new Branch();
    Account a1 = new Account(b);
    Account a2 = new Account(b);
    Account a3 = new Account(b);
    assertEquals(b.getBranchID(), a1.getBranchID());
    assertEquals(b.getBranchID(), a2.getBranchID());
    assertEquals(b.getBranchID(), a3.getBranchID());
    assertAll("Account",
        () -> assertEquals(b.getBranchID(), a1.getBranchID()),
        () -> assertEquals(b.getBranchID(), a2.getBranchID()),
        () -> assertEquals(b.getBranchID(), a3.getBranchID())
    );
  }

  @Test
  void failTestBranchInAccounts(){
    Branch b = new Branch();
    Branch b2 = new Branch();
    Account a1 = new Account(b);
    Account a2 = new Account(b2);
    Account a3 = new Account(b);

        assertAll("Account",
                () -> assertEquals(b.getBranchID(), a1.getBranchID()),
                () -> assertNotEquals(b.getBranchID(), a2.getBranchID()),
                () -> assertEquals(b.getBranchID(), a3.getBranchID())
        );
  }

  @Test
  @DisplayName("test error pertenencias varias accounts a branch usando assertAll")
  void Test5_4() {
    Branch branch1 = new Branch();
    Branch branch2 = new Branch();
    Account account1 = new Account(branch1);
    Account account2 = new Account(branch2);
    Account account3 = new Account(branch1);
    assertThrows(Error.class,
        () -> assertAll(
            () -> assertEquals(branch1.getBranchID(), account1.getBranchID()),
            () -> assertEquals(branch1.getBranchID(), account2.getBranchID()),
            () -> assertEquals(branch1.getBranchID(), account3.getBranchID())
        ));
  }

// 6.
  // Añade una condicion al test del ejercicio 3 para incrementar el balance de forma que ejecute una parte del codigo
  // si el minuto en que se ejecuta el test es par o no
  // Sigue los siguientes criterios para crear diferentes tests
  // Incrementa el valor dos veces comprueba el resultado y si se da la condicion vuelve a incrementarlo dos veces mas y compruebalo

  @Test
  @DisplayName("Test correct balance after add balance")
  void correctBalance_v1() {
    boolean par = Calendar.getInstance().get(Calendar.MINUTE) % 2 == 0;
    Account a = new Account(new Branch("134"), "0");
    a.addBalance(new BigDecimal("500"));
    a.addBalance(new BigDecimal("250"));
    BigDecimal result = new BigDecimal("750.0");
    assertEquals(result, a.getBalance());
    assumeTrue(par);
    a.addBalance(new BigDecimal("1000"));
    a.addBalance(new BigDecimal("2000"));
    BigDecimal result2 = new BigDecimal("3750.0");
    assertEquals(result2, a.getBalance());
  }

  @Test
  @DisplayName("Test correct balance after add balance")
  void correctBalance() {
    boolean par = Calendar.getInstance().get(Calendar.MINUTE) % 2 == 0;
    Account a = new Account(new Branch("134"), "0");
    a.addBalance(new BigDecimal("500"));
    a.addBalance(new BigDecimal("250"));
    BigDecimal result = new BigDecimal("750.0");
    assertEquals(result, a.getBalance());
    assumingThat(par, () -> {
      a.addBalance(new BigDecimal("1000"));
      a.addBalance(new BigDecimal("2000"));
      BigDecimal result2 = new BigDecimal("3750.0");
      assertEquals(result2, a.getBalance());
    });
    assumingThat(!par, () -> {
      a.addBalance(new BigDecimal("3000"));
      a.addBalance(new BigDecimal("4000"));
      BigDecimal result3 = new BigDecimal("7750.0");
      assertEquals(result3, a.getBalance());
    });
  }

  // Dependiendo de la condicion se deben usar valores diferentes en los incrementos

}
