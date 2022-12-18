package com.example.absolutelyfinalautootclickwork;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class SendOtcklick {
    static String token;
    static String vac_id;
    static String resume_id;
    static String message;
    public static void Send() {
        //Unirest.options(0, 0); вообще не понял что это за хрень и просто ее отключил вдруг когда нибудь пойму включу назад, типо какието опции зачемто
        HttpResponse<String> response = Unirest.post("https://api.hh.ru/negotiations")
                .header("Authorization", String.format("%s", token))
                .header("Cookie", "__ddg1_=9AfLrrZOzCvlrpgUznWk; hhtoken=0c7Y5Od35!S6pEkHd44avQYuF7UZ; hhuid=2J_g9ld0!fMOoWOXPtFMoA--")
                .multiPartContent()
                .field("vacancy_id", String.format("%s", vac_id))
                .field("resume_id", String.format("%s", resume_id))
                .field("message", String.format("%s", message))
                .asString();
    }
}
