package by.training.epam.controller;

import by.training.epam.controller.impl.*;
import by.training.epam.controller.impl.gotocommand.*;
import by.training.epam.controller.util.CommandName;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {

        commands.put(CommandName.SHOW_DEPARTMENT_ANALYSIS, new ShowDepartmentAnalysisCommand());
        commands.put(CommandName.GO_TO_PROLONG_TREATMENT, new GoToProlongTreatmentCommand());
        commands.put(CommandName.PROLONG_TREATMENT, new ProlongTreatmentCommand());
        commands.put(CommandName.INTERRUPT_TREATMENT, new InterruptTreatmentCommand());
        commands.put(CommandName.SHOW_DRUG_THERAPY, new ShowDrugTherapyCommand());
        commands.put(CommandName.ADD_TREATMENT, new AddTreatmentCommand());
        commands.put(CommandName.CHOOSE_DRUG_GROUP, new ChooseDrugGroupCommand());
        commands.put(CommandName.GO_TO_ADD_TREATMENT, new GoToAddTreatmentCommand());
        commands.put(CommandName.SHOW_PATIENT_DIAGNOSTICS, new ShowPatientDiagnosticsCommand());
        commands.put(CommandName.UNBLOCK_USER, new UnblockUserCommand());
        commands.put(CommandName.BLOCK_USER, new BlockUserCommand());
        commands.put(CommandName.SHOW_ALL_USERS, new ShowAllUsersCommand());
        commands.put(CommandName.GO_TO_PERSONAL_INFO, new GoToPersonalInfoCommand());
        commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
        commands.put(CommandName.GO_TO_UPDATE_ANALYSIS,new GoToUpdateAnalysisCommand());
        commands.put(CommandName.UPDATE_ANALYSIS_COMMAND,new UpdateAnalysisCommand());
        commands.put(CommandName.SHOW_PATIENT_ANALYZES, new ShowPatientAnalysisCommand());
        commands.put(CommandName.LOG_OUT, new LogOutCommand());
        commands.put(CommandName.GO_TO_PATIENT_PAGE, new GoToPatientPageCommand());
        commands.put(CommandName.ADD_NEW_CONSULTATION, new AddConsultationCommand());
        commands.put(CommandName.ADD_NEW_OPERATION, new AddOperationCommand());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandName.GO_TO_ADD_OPERATION_PAGE, new GoToAddOperationPageCommand());
        commands.put(CommandName.GO_TO_CONSULTATION_PAGE, new GoToAddConsultationPage());
        commands.put(CommandName.GO_TO_DISCHARGING_PAGE, new GoToDischargePageCommand());
        commands.put(CommandName.GO_TO_INITIAL_PAGE, new GoToInitialPageCommand());
        commands.put(CommandName.LOGINATION, new LogInCommand());
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
        commands.put(CommandName.SET_LOCALE, new SetLocaleCommand());
    }

    private final Map<String, Command> commands = new HashMap<>();

    public Command getCommand(String key) {
        return commands.get(key);
    }
}
