class VacationFormComponent extends FormComponent {
    vacation = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "vacation");
    }

    saveVacation = function () {
        this.vacation.status = null;
        this.save();
    }
}
