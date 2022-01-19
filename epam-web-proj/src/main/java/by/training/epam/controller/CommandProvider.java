package by.training.epam.controller;

import by.training.epam.controller.gotocommand.*;
import by.training.epam.controller.util.CommandName;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {

        commands.put(CommandName.GO_TO_PATIENT_PAGE, new GoToPatientPageCommand());
        commands.put(CommandName.ADD_NEW_CONSULTATION, new AddConsultationCommand());
        commands.put(CommandName.ADD_NEW_OPERATION, new AddOperationCommand());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandName.GO_TO_ADD_OPERATION_PAGE, new GoToAddOperationPageCommand());
        commands.put(CommandName.GO_TO_CONSULTATION_PAGE, new GoToAddConsultationPage());
        commands.put(CommandName.GO_TO_DISCHARGING_PAGE, new GoToDischargePageCommand());
        commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPageCommand());
        commands.put(CommandName.LOGINATION, new LoginationCommand());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.REGISTRATION_DETAILS, new RegistrationDetailsCommand());
        commands.put(CommandName.SEARCH_PATIENT_BY_SURNAME, new SearchPatientBySurnameCommand());
        commands.put(CommandName.GO_TO_ADD_PATIENT_PAGE, new GoToAddPatientPageCommand());
        commands.put(CommandName.ADD_PATIENT, new AddPatientCommand());
        commands.put(CommandName.DISCHARGE_PATIENT, new DischargePatientCommand());
        commands.put(CommandName.ADD_NEW_ANALYSIS, new AddAnalysisCommand());
        commands.put(CommandName.ADD_NEW_DIAGNOSTIC, new AddDiagnosticCommand());
        commands.put(CommandName.GO_TO_ADD_ANALYSIS_PAGE, new GoToAddAnalysisPageCommand());
        commands.put(CommandName.GO_TO_ADD_DIAGNOSTIC_PAGE, new GoToAddDiagnosticPageCommand());
        commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
    }

    public Command getCommand(String key) {
        return commands.get(key);
    }
}
