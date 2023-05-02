package com.imatia.campusdual;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class EjercicioTest9 {

  // 9. Crea un BeforeAll que muestre las propiedades del sistema
  @BeforeAll
  public static void showSystemProperties() {
    System.out.println("System Properties:");
    Properties properties = System.getProperties();
    properties.forEach((key, value) -> System.out.println(key + " : " + value));
    System.out.println("\nEnvironment Variables:");
    Map<String, String> env = System.getenv();
    env.forEach((key, value) -> System.out.println(key + " : " + value));
  }
  // Crea un test para incrementar el balance de una cuenta luego prueba las diferentes opciones para habilitarlo y deshabilitarlo
  // Habilitalo para Windows y Linux
  @Test
  @DisplayName("Añadir balance a cuenta enabled windows/linux")
  @EnabledOnOs({OS.WINDOWS, OS.LINUX})
  void testAddBalanceAcount2(){
    Account a = new Account(new Branch());
    a.addBalance(new BigDecimal("100.00"));
    assertEquals(new BigDecimal("100.00"), a.getBalance());
  }

  // Deshabilitalo para Windows
  @Test
  @DisplayName("Añadir balance a cuenta disabled windows")
  @DisabledOnOs(OS.WINDOWS)
  void testAddBalanceAcount2DisabledWindows(){
    Account a = new Account(new Branch());
    a.addBalance(new BigDecimal("100.00"));
    assertEquals(new BigDecimal("100.00"), a.getBalance());
  }

  // Habilitalo para Java11
  @Test
  @EnabledOnJre(JRE.JAVA_11)
  void testActivarJRE() {
    Account cuenta1 = new Account(new Branch());
    cuenta1.addBalance(new BigDecimal("90.0"));
    assertEquals(new BigDecimal("90.0"), cuenta1.getBalance());
  }

  // Deshabilitalo para Java11
  @Test
  @DisabledOnJre(JRE.JAVA_11)
  void testDesactivarJRE() {
    Account cuenta1 = new Account(new Branch());
    cuenta1.addBalance(new BigDecimal("90.0"));
    assertEquals(new BigDecimal("90.0"), cuenta1.getBalance());
  }

  // Habilitalo si el idioma del sistema es español
  @Test
  @EnabledIfSystemProperty(named = "user.language", matches = "es")
  void testActivarLanguageEs() {
    Account cuenta1 = new Account(new Branch());
    cuenta1.addBalance(new BigDecimal("90.0"));
    assertEquals(new BigDecimal("90.0"), cuenta1.getBalance());
  }
  // Deshabilitalo si el idioma del sistema es ingles
  @Test
  @DisabledIfSystemProperty(named = "user.language", matches = "en")
  void testDesactivarLanguageEn() {
    Account cuenta1 = new Account(new Branch());
    cuenta1.addBalance(new BigDecimal("90.0"));
    assertEquals(new BigDecimal("90.0"), cuenta1.getBalance());
  }

  // Habilitalo si el sistema cuenta con 8 procesadores
  @Test
  @DisplayName("Add balance. Enabled if cores are 8")
  @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "8")
  void addBalanceEnabled8Cores() {
    Account account = new Account(new Branch());
    account.addBalance(new BigDecimal("3000"));
    System.out.println(account.getBalance());
  }
  // Deshabilitalo si el sistema cuenta con 2 procesadores
  @Test
  @DisplayName("Add balance. Disabled if cores are 2")
  @DisabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "2")
  void addBalanceDisable2Cores() {
    Account account = new Account(new Branch());
    account.addBalance(new BigDecimal("3000"));
    System.out.println(account.getBalance());
  }

}
