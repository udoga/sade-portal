class AssignmentApi extends RestApi {
    constructor() {
        super("/assignments/");
    }

    listDetailedByUserId = function (userId) {
        return this.list("detailed?userId=" + userId);
    }
}
