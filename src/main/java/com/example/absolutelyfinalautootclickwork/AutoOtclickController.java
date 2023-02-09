package com.example.absolutelyfinalautootclickwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

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
    private TextField pauseTime;






    @FXML
    protected void onStartSearchButtonClick(ActionEvent event) {
        String vacancy_name_1 = getVacancyName.getText().replaceAll(" ", "+");
        String vacancies_From_One_search_1 = vacanciesFromOneSearch.getText();
        int search_Iteration = parseInt(searchIteration.getText());
        HTTPRequestHHVacancies getjob = new HTTPRequestHHVacancies();
        for (int i = 0; i < getjob.id_vacancies.size(); i++) {
            getjob.id_vacancies.remove(i);
            i--;
        }
        for (int i = 0; i < search_Iteration; i++) {

            getjob.vacancy_name = vacancy_name_1;
            getjob.per_page = vacancies_From_One_search_1;
            getjob.page = Integer.toString(i);
            getjob.GetJobsID();
        }
        System.out.println("Найденные id вакансий " + getjob.id_vacancies); // проверяем что в лист добавляются разные id
        System.out.println("Общее количество найденных id " + getjob.id_vacancies.size()); // проверяем длинну массива чтобы быть увереным что вакансии никуда не исчезли
        idSearchResult.setText("Список id вакансий получен");
    }
    @FXML
    protected void onClickSendAllOtclick(ActionEvent event) {
        String your_Token_1 = yourToken.getText();
        String your_Resume_Id = yourResumeId.getText();
        String messageX =  yourMessage.getText();
        HTTPRequestHHVacancies findjob = new HTTPRequestHHVacancies();
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

    @FXML
    protected void infinityOtcklick(ActionEvent event) {
        infinitySend();
    }

    public void infinitySend() {
        int count = 1;
        int pauseTimer = parseInt(pauseTime.getText());
        HTTPRequestHHVacancies getjob = new HTTPRequestHHVacancies();
        String vacancy_name_1 = getVacancyName.getText();
        String vacancies_From_One_search_1 = vacanciesFromOneSearch.getText();
        int search_Iteration = parseInt(searchIteration.getText());
        String your_Token_1 = yourToken.getText();
        String your_Resume_Id = yourResumeId.getText();
        String messageX =  yourMessage.getText();
        SendOtcklick sendSend = new SendOtcklick();
        sendSend.token = your_Token_1;
        sendSend.resume_id = your_Resume_Id;
        sendSend.message = messageX;

        while (true) {
            System.out.println("Очищаем массив с id вакансий предыдущего поиска");
            for (int i = 0; i < getjob.id_vacancies.size(); i++) {
                getjob.id_vacancies.remove(i);
                i--;
            }
            System.out.println(String.format("Начинаю поиск по названию вакансии %s, количество найденных вакансии за один цикл %s, количество циклов %d", vacancy_name_1, vacancies_From_One_search_1, search_Iteration));
            for (int i = 0; i < search_Iteration; i++) {
                getjob.vacancy_name = vacancy_name_1;
                getjob.per_page = vacancies_From_One_search_1;
                getjob.page = Integer.toString(i);
                getjob.GetJobsID();
            }
            System.out.println("Найденные id вакансий " + getjob.id_vacancies); // проверяем что в лист добавляются разные id
            System.out.println("Общее количество найденных id " + getjob.id_vacancies.size()); // проверяем длину массива, чтобы быть уверенным, что вакансии никуда не исчезли
            System.out.println("Начинаем отправку на найденные id");
            for (Integer b : getjob.id_vacancies) {
                sendSend.vac_id = Integer.toString(b);
                sendSend.Send();
            }

            System.out.println("Завершен " + count + " цикл отправки, следующая отправка состоится через " + pauseTimer + " минут.");


            count++;
            try {
                TimeUnit.MINUTES.sleep(pauseTimer);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}