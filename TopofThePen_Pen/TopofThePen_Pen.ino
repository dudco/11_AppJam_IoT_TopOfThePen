#include <SoftwareSerial.h>
SoftwareSerial BTSerial(2,3);
int FSR_Pin = A0;
void setup(){
  Serial.begin(9600);
  BTSerial.begin(9600);
}

void loop(){
  int FSRReading = analogRead(FSR_Pin);
  if(FSRReading>=700){o
    BTSerial.print("1");
    Serial.print("1");
  }
  else{
  BTSerial.print("0");
  Serial.print("0");
  }
  delay(1000);
}
