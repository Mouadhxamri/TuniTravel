/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

/**
 *
 * @author HP
 */

import com.twilio.Twilio;
import com.twilio.http.HttpClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
   
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "AC96f91c4b9f2ff4773ea85721c2a412e4";
  public static final String AUTH_TOKEN = "6f52facf2920d1293b096b4d911fe9a0";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+21652716304"), new PhoneNumber("+19107202930"),"Winek hobi helmi aman twahachtek").create();

    System.out.println(message.getSid());
  }
}