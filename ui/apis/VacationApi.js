class VacationApi extends RestApi {
    constructor() {
        super("/vacations/");
    }

    listWithDetails = function () {
        return this.list("with-details");
    }
}
