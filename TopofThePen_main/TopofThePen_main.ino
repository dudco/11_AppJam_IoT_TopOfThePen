#include <SPI.h>
#include <MFRC522.h>
#include <Timer.h>
Timer ts;
int samplingT = 5000;
int checkCNT = 0;
#define RST_PIN         5
#define SS_PIN          53
String result="00";
unsigned long time;
char tmp1;
MFRC522 mfrc522(SS_PIN, RST_PIN);

void setup() {
  ts.every(samplingT, doTest);
  Serial.begin(9600);
  Serial1.begin(9600);//폰
  Serial2.begin(9600);//펜
  while (!Serial);
  SPI.begin();
  mfrc522.PCD_Init();
  mfrc522.PCD_DumpVersionToSerial();
}

void loop() {
  ts.update();

  if (Serial2.available()) {
        tmp1 = Serial2.read();
        
        //Serial.println(tmp1);
  }
  
  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    return;
  }
  if ( ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }
  

  String rfidUid = "";
  
  for (byte i = 0; i < mfrc522.uid.size; i++) {
    rfidUid += String(mfrc522.uid.uidByte[i] < 0x10 ? "0" : "");
    rfidUid += String(mfrc522.uid.uidByte[i], HEX);
  }

  result = rfidUid;
}

void doTest() {
//  Serial.println("timer");
  
  time = millis();

  Serial1.print(tmp1);
  Serial1.println(result);

  result = "00000000";
  
}

