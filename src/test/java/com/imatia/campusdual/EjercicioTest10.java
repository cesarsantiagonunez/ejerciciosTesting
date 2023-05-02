package com.imatia.campusdual;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class EjercicioTest10 {

  // 10.
  // Modifica el test para incrementar el balance de una cuenta de forma que se repita 5 veces
  // Repite el mismo caso mostrando en cada repeticion el nombre del test, el numero de la repeticion y el total de repeticiones
    @DisplayName("Test incrementar el balance de una cuenta varias veces y comprobar que el valor final es correcto")
    @RepeatedTest(value = 5, name = "{displayName} repetition {currentRepetition} of {totalRepetitions}")
    public void Test10_1() {
//      System.out
//          .println(testInfo.getDisplayName() + " " + info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
      // Crear una cuenta
      Branch branch = new Branch("001", "002");
      Account account = new Account(branch);
      // Incrementar el balance varias veces
      BigDecimal increment = new BigDecimal("50");
        account.addBalance(increment);
      // Comprobar que el balance final es correcto
      assertEquals(new BigDecimal("50.0"), account.getBalance());
    }

}
