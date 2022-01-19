package by.training.epam.service;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {}

    public UserService getUserService() {
        return UserService.getInstance();
    }

    public PatientService getPatientService() {
        return PatientService.getInstance();
    }

    public AnalysisService getAnalysisService() {return AnalysisService.getInstance();}

    public DiagnosticService getDiagnosticService() {return DiagnosticService.getInstance();}

    public OperationService getOperationService() {return OperationService.getInstance();}

    public ConsultationService getConsultationService() {return ConsultationService.getInstance();}

}
