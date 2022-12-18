package com.example.absolutelyfinalautootclickwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AutoOtclickController {
    @FXML
    private Label idSearchResult;

    @FXML
    private Label sendOtclickResult;

    @FXML
    private TextField getVacancyName;

    @FXML
    private TextField vacanciesFromOneSearch;

    @FXML
    private TextField searchIteration;

    @FXML
    private TextField yourToken;

    @FXML
    private TextField yourResumeId;

    @FXML
    private TextArea yourMessage;



    @FXML
    protected void onStartSearchButtonClick(ActionEvent event) {
        String vacancy_name_1 = getVacancyName.getText();
        String vacancies_From_One_search_1 = vacanciesFromOneSearch.getText();
        int search_Iteration = Integer.parseInt(searchIteration.getText());
        HTTPRequestHHVacancies getjob = new HTTPRequestHHVacancies();
        for (int i = 0; i < search_Iteration; i++) {

            getjob.vacancy_name = vacancy_name_1;
            getjob.per_page = vacancies_From_One_search_1;
            getjob.page = Integer.toString(i);
            getjob.GetJobsID();
            // System.out.println(getjob.id_vacancies); проверяем функцию добавления ID вакансий
        }
        System.out.println(getjob.id_vacancies); // проверяем что в лист добавляются разные id
        System.out.println(getjob.id_vacancies.size()); // проверяем длинну массива чтобы быть увереным что вакансии никуда не исчезли
        idSearchResult.setText("Список id вакансий получен");
        // System.out.println(vacancy_name_1); проверял смогу ли передать в переменную введенные данные вакансии
    }
    @FXML
    protected void onClickSendAllOtclick(ActionEvent event) {
        String your_Token_1 = yourToken.getText();
        String your_Resume_Id = yourResumeId.getText();
        String messageX =  yourMessage.getText();
        // System.out.println(your_Token_1 + your_Resume_Id + message); проверял что текстовые поля передают данные
        HTTPRequestHHVacancies findjob = new HTTPRequestHHVacancies();
        //System.out.println(findjob.id_vacancies); проверил что передал массив во второй метод
        SendOtcklick sendSend = new SendOtcklick();
        sendSend.token = your_Token_1;
        sendSend.resume_id = your_Resume_Id;
        sendSend.message = messageX;
        for (Integer b : findjob.id_vacancies) {
            sendSend.vac_id = Integer.toString(b);
            sendSend.Send();
        }
        sendOtclickResult.setText("Отклики отправлены");

    }
}