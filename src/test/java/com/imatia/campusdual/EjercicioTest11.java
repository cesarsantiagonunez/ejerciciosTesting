package com.imatia.campusdual;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class EjercicioTest11 {

  // 11.
  // Crea una nueva cuenta con balance = 1000 y comprueba que puedes ejecutar el metodo withradrawalBalance con los
  // valores {250,500,750,1000} con las diferentes anotaciones
  // @ValueSource
  @ParameterizedTest
  @ValueSource(ints = {250, 500, 750, 1000})
  void setNewWithdrawalValue(int newWithdrawal) {
    Account acc3 = new Account(new Branch());
    acc3.addBalance(new BigDecimal("1000"));

    assertDoesNotThrow(() -> acc3.withdrawalBalance(new BigDecimal(String.valueOf(newWithdrawal))));
//    try {
//      acc3.withdrawalBalance(new BigDecimal(String.valueOf(newWithdrawal)));
//      assertTrue((acc3.getBalance().compareTo(BigDecimal.ZERO)) > -1);
//    } catch (InsufficientBalanceException e) {
//      fail();
//    }
  }

  @ParameterizedTest
  @ValueSource(ints = {250, 500, 750, 1000})
  void testWithdrawalBalanceWithValueSource(int withdrawalAmount) throws InsufficientBalanceException {
    Account account = new Account(new Branch());
    account.addBalance(new BigDecimal("1000.0"));
    account.withdrawalBalance(BigDecimal.valueOf(withdrawalAmount));
    BigDecimal expectedBalance = new BigDecimal("1000.0").subtract(new BigDecimal(withdrawalAmount));
    assertEquals(expectedBalance, account.getBalance());
  }

  // @CsvSource
  @ParameterizedTest
  @CsvSource({"250.00", "500.00", "750.00", "1000.00"})
  void testWithdrawalBalance(String value){
    Account account = new Account(new Branch());
    account.addBalance(new BigDecimal("1000.00"));
    assertDoesNotThrow(() -> account.withdrawalBalance(new BigDecimal(value)));
  }

  // @CsvFileSource - testDataCSV.csv
  @ParameterizedTest
  @DisplayName("Values from csv file")
  @CsvFileSource(resources = "/testDataCSV.csv")
  void setNewWithdrawalCsvFileSource(int newWithdrawal) {
    Account acc3 = new Account(new Branch());
    acc3.addBalance(new BigDecimal("1000"));
    assertDoesNotThrow(() ->  acc3.withdrawalBalance(new BigDecimal(String.valueOf(newWithdrawal))));
  }

  // @MethodSource - crea un metodo que devuelva una lista de 4 valores aleatorios entre 250 y 1000
  @ParameterizedTest
  @DisplayName("test retirado fondos vorios valores")
  @MethodSource("generarNumeros")
  void Test11_4(int valor) {
    System.out.println(valor);
    Account account = new Account(new Branch(), "11");
    account.addBalance(new BigDecimal("1000.0"));
    assertDoesNotThrow(()-> account.withdrawalBalance(new BigDecimal(valor)));
//    try {
//      account.withdrawalBalance(new BigDecimal(valor));
//    } catch (InsufficientBalanceException e) {
//      e.printStackTrace();
//      fail();
//    }
  }
  public static Stream<Arguments> generarNumeros() {
    int[] numeros = IntStream.generate(() -> (int) (Math.random() * (1000 - 250 + 1) + 250)).limit(4).toArray();
    return Stream.of(
        Arguments.of(numeros[0]),
        Arguments.of(numeros[1]),
        Arguments.of(numeros[2]),
        Arguments.of(numeros[3])
    );
  }

  // @CsvSource - en este caso indica que el test recibira dos parametros, la cantidad inicial de la cuenta y
  // la cantidad a retirar, siendo esta segunda siempre menor a la primera
  @ParameterizedTest
  @CsvSource({"400,250","600,300","1000,750"})
  void testWithrawalBalanceWithCsvSourceMultipleParametersNotThrows(String amount, String withrawal){
    Account account = new Account(new Branch());
    account.addBalance(new BigDecimal(amount));
    assertDoesNotThrow(() -> account.withdrawalBalance(new BigDecimal(withrawal)));
  }

}
