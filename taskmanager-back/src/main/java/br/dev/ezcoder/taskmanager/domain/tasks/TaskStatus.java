package br.dev.ezcoder.taskmanager.domain.tasks;

public enum TaskStatus {
    PENDING ("pendente"),
    COMPLETED ("concluida"),
    PROGRESS ("processo"),
    RESCHEDULED ("reagendada");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }
}
