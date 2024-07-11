class VacationApi extends RestApi {
    constructor() {
        super("/vacations/");
    }

    listDetailed = function () {
        return this.list("detailed");
    }
}
