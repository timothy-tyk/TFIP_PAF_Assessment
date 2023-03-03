package pafassessment.pafassessment.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transfer {

  @Size(min=10, max=10, message="Invalid Account Id (From), must be 10 characters long")
  private String fromId = "";

  @Size(min=10, max=10, message="Invalid Account Id (To), must be 10 characters long")
  private String toId = "";

  @DecimalMin(value = "10.00", message="Minimum transfer amount is $10")
  @Positive(message="Transfer amount must be positive number")
  @NotNull(message="Enter an amount!")
  private Float amount;

  private String comments;

  private String transactionId;

  private String date;

  public Transfer(){
    this.transactionId = generateTxId(8);
    this.date = LocalDateTime.now().toString();
  }

  // Methods
  public String generateTxId(Integer numChar){
    return UUID.randomUUID().toString().substring(0,numChar);
  }

  public static JsonObject toJson(Transfer trf){
    return Json.createObjectBuilder()
          .add("transactionId",trf.getTransactionId())
          .add("date", LocalDateTime.now().toString())
          .add("from_account", trf.getFromId())
          .add("to_account", trf.getToId())
          .add("amount", trf.getAmount())
          .build();
  }

  
 
  
}
